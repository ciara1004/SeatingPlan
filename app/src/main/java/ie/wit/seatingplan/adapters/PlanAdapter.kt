package ie.wit.seatingplan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.seatingplan.R
import ie.wit.seatingplan.models.PlanModel
import kotlinx.android.synthetic.main.card_tables.view.*
import kotlinx.android.synthetic.main.fragment_table_layout.view.*

interface DeleteListener
{
    fun onDeleteClick(plan:PlanModel)
}

interface TableListener
{
    fun onTableListener(plan:PlanModel)
}

class PlanAdapter constructor(private var plans: List<PlanModel>,
                               private val deleteListener: DeleteListener,
                               private val tableListener: TableListener
)
    : RecyclerView.Adapter<PlanAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_tables,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val plan = plans[holder.adapterPosition]
        holder.bind(plan, deleteListener, tableListener)
    }

    override fun getItemCount(): Int = plans.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            plan: PlanModel,
            deleteListener: DeleteListener,
            TableListener: TableListener
        ) {
            itemView.guests.text = plan.guests
            itemView.deleteBtn.setOnClickListener{deleteListener.onDeleteClick(plan)}
            itemView.setOnClickListener{TableListener.onTableListener(plan)}
            itemView.imageIcon.setImageResource(R.mipmap.ic_launcher_logo)

        }
    }
}