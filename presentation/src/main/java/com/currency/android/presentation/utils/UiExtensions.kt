package com.currency.android.presentation.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.nullgr.core.adapter.items
import com.nullgr.core.adapter.items.ListItem

inline fun <reified T : ListItem> RecyclerView.ViewHolder.withAdapterPosition(
    block: (items: List<ListItem>, item: T, position: Int) -> Unit
) {
    with(adapterPosition) {
        if (this != RecyclerView.NO_POSITION) {
            val items = items()
            if (items != null && this >= 0 && this < items.size) {
                block.invoke(items, items[this] as T, this)
            }
        }
    }
}

fun ImageView.loadImage(
    @DrawableRes icon: Int,
    @DrawableRes loadingPlaceholder: Int,
    @DrawableRes errorPlaceholder: Int,
    onSuccess: (() -> Unit)? = null,
    onError: (() -> Unit)? = null,
    onComplete: (() -> Unit)? = null
) {
    Glide.with(context)
        .load(icon)
        .apply(
            RequestOptions.circleCropTransform()
                .placeholder(loadingPlaceholder)
                .error(errorPlaceholder)
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onError?.invoke()
                onComplete?.invoke()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onSuccess?.invoke()
                onComplete?.invoke()
                return false
            }
        })
        .into(this)
}