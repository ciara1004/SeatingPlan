package ie.wit.seatingplan.models

import android.util.Log

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlanMemStore : PlanStore {

    var plans = ArrayList<PlanModel>()

    override fun findAll(): List<PlanModel> {
        return plans
    }

    override fun findById(id:Long) : PlanModel? {
        val foundPlan: PlanModel? = plans.find { it.id == id }
        return foundPlan
    }

    override fun create(plan: PlanModel) {
        plan.id = getId()
        plans.add(plan)
        logAll()
    }

    fun logAll() {
        Log.v("Plan","** List of Plans **")
        plans.forEach { Log.v("Plan","${it}") }
    }

    override fun delete(plan:PlanModel)
    {
        plans.remove(plan)
    }

}