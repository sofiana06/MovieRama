package com.skoukio.movierama.models.common

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.skoukio.movierama.models.common.Model.Companion.INVALID_INT
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class ImageModel(
    val url: String? = null,
    @DrawableRes val resource: Int = INVALID_INT,
    val path: File? = null
) : Parcelable, Model

fun String?.nonNullImage(@DrawableRes defaultValue: Int = INVALID_INT): ImageModel {
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