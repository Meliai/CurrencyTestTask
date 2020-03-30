package com.currency.android.presentation.core.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.replaceContainer(containers: Map<String, Fragment>, tag: String) {
    beginTransaction().apply {
        containers.forEach {
            when (tag) {
                it.key -> attach(it.value)
                else -> detach(it.value)
            }
        }
    }.commitNow()
}

fun FragmentManager.initializeContainers(
    creator: FragmentCreator,
    containers: MutableMap<String, Fragment>,
    containerId: Int,
    tags: Array<String>
) {
    tags.forEach { tag ->
        containers[tag] = initializeSingleContainer(creator, containerId, tag)
    }
}

fun FragmentManager.initializeSingleContainer(
    creator: FragmentCreator,
    containerId: Int,
    tag: String
): Fragment =
    findFragmentByTag(tag) ?: creator.create(tag).apply {
        beginTransaction()
            .add(containerId, this, tag)
            .detach(this)
            .commitNow()
    }