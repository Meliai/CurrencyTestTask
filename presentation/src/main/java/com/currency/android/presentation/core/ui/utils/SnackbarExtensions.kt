package com.currency.android.presentation.core.ui.utils

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import com.google.android.material.snackbar.Snackbar
import com.currency.android.presentation.core.ui.snack_bar_view.SnackBarData
import io.reactivex.functions.Consumer

fun Snackbar.applyTextAppearance(@StyleRes style: Int): Snackbar {
    with(view) {
        val textView = findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView?.let {
            TextViewCompat.setTextAppearance(it, style)
            it.maxLines = Int.MAX_VALUE
        }
    }
    return this
}

fun Snackbar.applySnackbarHeight(): Snackbar {
    with(view) {
        // TODO: set snackbar height from project dimens
        // layoutParams?.height = view.resources.getDimensionPixelSize(android.R.dimen.dialog_fixed_height_major)
    }
    return this
}

fun Snackbar.applyTextDrawable(@DrawableRes drawable: Int?): Snackbar {
    drawable?.let { nonNullDrawable ->
        with(view) {
            val textView = findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView?.let {
                it.compoundDrawablePadding = paddingLeft
                it.setPadding(0, 0, 0, 0)
                it.setCompoundDrawablesWithIntrinsicBounds(nonNullDrawable, 0, 0, 0)
            }
        }
    }
    return this
}

fun Snackbar.applyBackgroundColor(@ColorRes color: Int): Snackbar {
    with(view) {
        setBackgroundResource(color)
    }
    return this
}

fun makeSnackBar(view: View, data: SnackBarData): Snackbar =
    Snackbar.make(view, data.message, Snackbar.LENGTH_SHORT)
        .applySnackbarHeight()
        .applyBackgroundColor(android.R.color.black)
        .applyTextAppearance(android.R.style.TextAppearance)
        .applyTextDrawable(data.icon)

fun View?.showSnackBar(): Consumer<in SnackBarData> = Consumer { data ->
    this?.let { view -> makeSnackBar(view, data).show() }
}