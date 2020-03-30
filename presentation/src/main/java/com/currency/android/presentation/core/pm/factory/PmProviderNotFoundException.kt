package com.currency.android.presentation.core.pm.factory

/**
 * Exception to inform about missing pm provider.
 */
class PmProviderNotFoundException(clazz: Class<*>) :
    NoSuchElementException("There is no provider for ${clazz.simpleName}. " +
        "Maybe you forgot bindTo this view model at PmModule.")