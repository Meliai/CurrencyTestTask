package com.currency.android.presentation.core.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nullgr.core.adapter.DynamicAdapter
import com.nullgr.core.adapter.bindTo
import com.currency.android.presentation.R
import com.currency.android.presentation.core.pm.BaseListPm
import javax.inject.Inject

/**
 * Base class for all list fragments.
 */
abstract class BaseListFragment<T : BaseListPm> : BaseFragment<T>() {

    @Inject
    lateinit var adapter: DynamicAdapter

    protected var itemsView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState).also { view ->
            itemsView = view.findViewById(R.id.itemsView)
            itemsView?.layoutManager = provideLayoutManager(activity)
            itemsView?.adapter = adapter
        }
    }

    override fun onBindPresentationModel(pm: T) {
        super.onBindPresentationModel(pm)
        pm.items.observable.bindTo(adapter, compositeUnbind)
    }

    protected open fun provideLayoutManager(context: Context?): RecyclerView.LayoutManager =
        LinearLayoutManager(context)
}