package scooters.coup.com.coup.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

const val IMAGE_URL = "imageUrl"

@BindingAdapter(IMAGE_URL)
fun ImageView._bindImageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(this)
    }
}