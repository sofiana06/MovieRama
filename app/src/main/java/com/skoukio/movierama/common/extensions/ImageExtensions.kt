package com.skoukio.movierama.common.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.skoukio.movierama.models.common.ImageModel
import com.squareup.picasso.Picasso

fun AppCompatImageView.loadImage(
    imageModel: ImageModel?,
    errorImageModel: ImageModel = ImageModel()
) {
    if (imageModel == null) {
        Picasso.get().load(errorImageModel.resource).into(this)
        return
    }
    if (imageModel.url.isNullOrEmpty()) {
        Picasso.get().load(imageModel.resource).into(this)
        return
    }
    if (imageModel.path != null && imageModel.path.exists()) {
        Picasso.get().load(imageModel.path).into(this)
        return
    }
    if (imageModel.url.contains("noImage")) {
        return
    }
    Picasso.get().load(imageModel.url).into(this)
}