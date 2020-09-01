package ie.wit.seatingplan.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanModel(var id: Long = 0,
                      var tableNo: Int = 0,
                      var guests: String = " "
                      ): Parcelable
