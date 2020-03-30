package com.currency.common.bus

import com.nullgr.core.rx.RxBus
import io.reactivex.Observable

/**
 * Interface to indicate events (apart from clicks) that will be dispatched via RxBus.
 */
interface Event

inline fun <reified T : Event> RxBus.events(): Observable<T> =
    observable(Event::class.java)
        .filter { it is Event && it is T }
        .map { it as T }

inline fun RxBus.allEvents(): Observable<Event> =
    observable(Event::class.java)
        .filter { it is Event }
        .map { it as Event }

inline fun <T : Event> RxBus.event(event: T) {
    post(Event::class.java, event)
}