package scooters.coup.com.coup.databinding

import android.databinding.BindingAdapter
import android.view.View

const val VIEW_VISIBILITY = "isVisible"

@BindingAdapter(VIEW_VISIBILITY)
fun View._bindVisibility(isVisible: Boolean?) {
    isVisible?.let { hideOrShow(it)}
}

private fun View.hideOrShow(isVisible: Boolean) {
    if (isVisible) visibility = View.VISIBLE else visibility = View.GONE
}