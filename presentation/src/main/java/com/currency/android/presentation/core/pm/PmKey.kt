package com.currency.android.presentation.core.pm

import dagger.MapKey
import me.dmdev.rxpm.PresentationModel
import kotlin.reflect.KClass

/**
 * Annotation to indicate key for pm.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class PmKey(val value: KClass<out PresentationModel>)