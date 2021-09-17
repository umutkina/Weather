package com.lemonz.weatherapp.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindingAdapterEx {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: ImageView, imageUrl: String?) {
        if (imageUrl.isNullOrBlank())
            return
        if (imageView.context.isDoomed().not()) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .fitCenter()
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }


}
