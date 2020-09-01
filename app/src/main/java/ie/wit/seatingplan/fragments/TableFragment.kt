package ie.wit.seatingplan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.seatingplan.R
import ie.wit.seatingplan.adapters.DeleteListener
import ie.wit.seatingplan.adapters.PlanAdapter
import ie.wit.seatingplan.adapters.TableListener
import ie.wit.seatingplan.main.MainActivity
import ie.wit.seatingplan.models.PlanModel
import kotlinx.android.synthetic.main.fragment_table.view.*
import org.jetbrains.anko.AnkoLogger

class TableFragment : Fragment(), DeleteListener, TableListener, AnkoLogger {

    lateinit var app: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val tableFragment = inflater.inflate(R.layout.fragment_table, container, false)
        tableFragment.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        tableFragment.recyclerView.adapter = PlanAdapter(app.planStore.findAll(), this, this)
        return tableFragment
    }

    override fun onDeleteClick(order: PlanModel) {
        app.planStore.delete(order)
        app.planStore.findAll()
        var fr = getFragmentManager()?.beginTransaction()
        fr?.replace(R.id.homeFrame, TableFragment())
        fr?.commit()
    }

    override fun onTableListener(order: PlanModel)
    {
        var fr = getFragmentManager()?.beginTransaction()
        fr?.replace(R.id.homeFrame, TableFragment())
        fr?.commit()

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TableFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}