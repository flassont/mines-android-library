package mines.flassont.library.activities.shared

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * DataBinding extension for Picasso lazy load
 */
@BindingAdapter("bind:src")
fun fetchCover(view: ImageView?, src: String?): Unit {
    if (view != null && src != null) {
        Picasso.with(view.context)
                .load(src)
                .fit()
                .centerInside()
                .into(view)
    }
}
