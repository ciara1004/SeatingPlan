package ie.wit.seatingplan.signUp_Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import ie.wit.seatingplan.R
import kotlinx.android.synthetic.main.register.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class RegisterActivity: AppCompatActivity(), AnkoLogger {

    val planAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val registerBtn = findViewById<View>(R.id.register_button)

        registerBtn.setOnClickListener(View.OnClickListener { view ->
            signUp()
        })

        loginBtn.setOnClickListener {
            startActivityForResult(intentFor<LoginActivity>(), 0)
        }
    }

    private fun signUp() {

        val emailText = findViewById<View>(R.id.emailText) as EditText
        val passwordText = findViewById<View>(R.id.passwordText) as EditText

        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        fun passwordCheck(): Boolean {
            var exp = ".*[0-9].*"
            var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
            var matcher = pattern.matcher(password)
            if (password.length < 8 && !matcher.matches()) {
                return false
            }

            exp = ".*[A-Z].*"
            pattern = Pattern.compile(exp)
            matcher = pattern.matcher(password)
            if (!matcher.matches()) {
                return false
            }

            // Password should contain at least one small letter
            exp = ".*[a-z].*"
            pattern = Pattern.compile(exp)
            matcher = pattern.matcher(password)
            if (!matcher.matches()) {
                return false
            }
            return true
        }

        if (email.isNotEmpty() && passwordCheck()) {
            planAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val user = planAuth.currentUser
                        val user_id = user!!.uid

                        startActivity(Intent(this, LoginActivity::class.java))
                        Toast.makeText(this, "You have successfully signed up", Toast.LENGTH_LONG)
                            .show()

                        user.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    toast("Verification Email sent")
                                }
                            }
                    }
                    else {
                        Toast.makeText(this, "Error. Please try again", Toast.LENGTH_LONG).show()
                    }
                })

        } else {
            toast("Please complete all fields correctly")
        }

    }
}