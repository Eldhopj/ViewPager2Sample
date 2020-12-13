package com.mobiotics.viewpager2sample

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**NOTE: always assign values when using Parcelable*/
@Parcelize
data class ModelClass(
    val id: Int = 0
) : Parcelable

