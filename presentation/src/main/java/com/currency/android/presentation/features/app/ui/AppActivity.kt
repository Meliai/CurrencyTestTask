package com.currency.android.presentation.features.app.ui

import android.os.Bundle
import com.currency.android.presentation.R
import com.currency.android.presentation.features.app.pm.AppPm
import com.currency.android.presentation.core.ui.activity.BaseActivity
import me.dmdev.rxpm.passTo

class AppActivity : BaseActivity<AppPm>() {

    override val screenLayout: Int = R.layout.layout_container
    override val classToken: Class<AppPm> = AppPm::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        Unit.passTo(presentationModel.coldStartAction)
    }
}