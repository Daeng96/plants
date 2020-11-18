package com.plant.pojo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class DetailTomato(
    var author: String?,
    var bibliography: String?,
    var common_name: String?,
    var family: String?,
    var family_common_name: String?,
    var genus: String?,
    var genus_id: Int?,
    var id: Int?,
    var image_url: String?,
    var rank: String?,
    var scientific_name: String?,
    var slug: String?,
    var status: String?,
    var synonyms: List<String>,
    var year: Int?
) : Parcelable