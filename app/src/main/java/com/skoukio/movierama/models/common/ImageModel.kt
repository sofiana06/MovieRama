package com.skoukio.movierama.models.common

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class ImageModel(
    val url: String? = null,
    @DrawableRes val resource: Int = -1,
    val path: File? = null
) : Parcelable

fun String?.nonNullImage(@DrawableRes defaultValue: Int = -1): ImageModel {
    if (this == null) {
        return ImageModel(resource = defaultValue)
    }
    return ImageModel(url = this)
}

fun ImageModel.deepClone(): ImageModel {
    return ImageModel(
        url = this.url,
        resource = this.resource,
        path = if (this.path != null) File(this.path.path) else null
    )
}