@file:Suppress("MethodOverloading")

package com.currency.common.utils

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

fun <E, T : Collection<E>> Observable<T>.logAll(
    tag: String,
    prefix: String? = null,
    transform: ((E) -> String)? = null
): Observable<T> =
    this.doOnNext { logAll(it, tag, prefix, transform) }

fun <E, T : Collection<E>> Flowable<T>.logAll(
    tag: String,
    prefix: String? = null,
    transform: ((E) -> String)? = null
): Flowable<T> =
    this.doOnNext { logAll(it, tag, prefix, transform) }

fun <E, T : Collection<E>> Maybe<T>.logAll(
    tag: String,
    prefix: String? = null,
    transform: ((E) -> String)? = null
): Maybe<T> =
    this.doOnSuccess { logAll(it, tag, prefix, transform) }

fun <T> Observable<T>.log(
    tag: String,
    prefix: String? = null,
    transform: ((T) -> String)? = null
): Observable<T> =
    this.doOnNext { log(it, tag, prefix, transform) }

fun <T> Flowable<T>.log(
    tag: String,
    prefix: String? = null,
    transform: ((T) -> String)? = null
): Flowable<T> =
    this.doOnNext { log(it, tag, prefix, transform) }

fun <T> Maybe<T>.log(
    tag: String,
    prefix: String? = null,
    transform: ((T) -> String)? = null
): Maybe<T> =
    this.doOnSuccess { log(it, tag, prefix, transform) }

fun Completable.log(
    tag: String,
    prefix: String? = null,
    transform: (() -> String)? = null
): Completable =
    this.doOnSubscribe { log(tag, prefix, transform) }

fun <T> log(
    element: T? = null,
    tag: String,
    prefix: String? = null,
    transform: ((T) -> String)? = null
) {
    val sb = StringBuilder().apply {
        prefix?.let { append("$prefix: ") }
        element?.let { append(transform?.invoke(element) ?: element.toString()) }
    }
    Log.d(tag, sb.toString())
}

fun <E, T : Collection<E>> logAll(
    iterable: T,
    tag: String,
    prefix: String? = null,
    transform: ((E) -> String)? = null
) {
    val sb = StringBuilder().apply {
        prefix?.let { append("$prefix: ") }
        append(iterable.joinToString { element ->
            transform?.invoke(element) ?: element.toString()
        })
    }
    Log.d(tag, sb.toString())
}