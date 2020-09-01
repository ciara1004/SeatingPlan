package ie.wit.seatingplan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ie.wit.seatingplan.R
import ie.wit.seatingplan.main.MainActivity
import kotlinx.android.synthetic.main.fragment_table_layout.view.*


class TableLayoutFragment : Fragment(){

    lateinit var app: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app=activity?.application as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val tableLayoutFragment = inflater.inflate(R.layout.fragment_table_layout, container, false)
        activity?.title = getString(R.string.action_tablelayout)
        setButtonListener(tableLayoutFragment)
        return tableLayoutFragment
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            TableLayoutFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun setButtonListener(layout: View) {
        layout.savebutton.setOnClickListener {
            val tableno = ("Number")
            val name = ("Name")
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.homeFrame, TableFragment())
            fr?.commit()
        }
    }

}