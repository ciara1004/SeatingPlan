package ie.wit.seatingplan.main

import android.app.Application
import android.util.Log
import ie.wit.seatingplan.models.PlanMemStore
import ie.wit.seatingplan.models.PlanStore
import org.jetbrains.anko.AnkoLogger


class MainActivity : Application(), AnkoLogger {

  lateinit var planStore: PlanStore

  override fun onCreate() {
    super.onCreate()
    planStore = PlanMemStore()
    Log.v("Plan","Seating Plan App started")

  }
}