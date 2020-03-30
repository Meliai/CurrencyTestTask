@file:Suppress("NOTHING_TO_INLINE")

package com.currency.android.presentation.core.ui.progress

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.currency.android.presentation.R
import io.reactivex.functions.Consumer

class ProgressDialog : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setCancelable(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_progress, container, false)
    }

    companion object {
        const val PROGRESS_TAG = "PROGRESS_TAG"
        fun newInstance() = ProgressDialog()
    }
}

inline fun ProgressDialog.visibility(fragmentManager: FragmentManager): Consumer<in Boolean> = Consumer {
    val fragment = fragmentManager.findFragmentByTag(ProgressDialog.PROGRESS_TAG)
    if (fragment != null && !it) {
        (fragment as ProgressDialog).dismissAllowingStateLoss()
        fragmentManager.executePendingTransactions()
    } else if (fragment == null && it) {
        show(fragmentManager, ProgressDialog.PROGRESS_TAG)
        fragmentManager.executePendingTransactions()
    }
}