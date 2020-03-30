package com.currency.android.presentation.core.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.view.visibility
import com.currency.android.presentation.R
import com.currency.android.presentation.core.navigation.AppNavigator
import com.currency.android.presentation.core.navigation.BackHandler
import com.currency.android.presentation.core.navigation.FlowRouter
import com.currency.android.presentation.core.navigation.RouterProvider
import com.currency.android.presentation.core.pm.BasePm
import com.currency.android.presentation.core.pm.factory.PmFactory
import com.currency.android.presentation.core.pm.widgets.bindTo
import com.currency.android.presentation.core.ui.fragment.BaseFragment
import com.currency.android.presentation.core.ui.state_view.StateView
import com.currency.android.presentation.core.ui.utils.showSnackBar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.dmdev.rxpm.base.PmActivity
import me.dmdev.rxpm.bindTo
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

/**
 * Base class for all activities.
 */
@Suppress("TooManyFunctions")
@SuppressLint("MissingSuperCall")
abstract class BaseActivity<T : BasePm> : PmActivity<T>(),
    HasSupportFragmentInjector,
    BackHandler,
    RouterProvider {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var factory: PmFactory

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: FlowRouter

    protected abstract val screenLayout: Int

    protected abstract val classToken: Class<T>

    protected open val navigator: Navigator = AppNavigator(this)

    private val currentFragment: BaseFragment<*>?
        get() = supportFragmentManager.findFragmentById(R.id.containerView) as? BaseFragment<*>

    private var errorStateView: StateView? = null
    private var emptyStateView: StateView? = null
    private var progressView: View? = null
    private var homeButtonView: View? = null
    private var activityContentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(screenLayout)

        errorStateView = findViewById<View>(R.id.errorStateView) as? StateView
        emptyStateView = findViewById<View>(R.id.emptyStateView) as? StateView
        progressView = findViewById(R.id.progressView)
        homeButtonView = findViewById(R.id.homeButtonView)
        activityContentView = findViewById(android.R.id.content)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        if (currentFragment != null) {
            currentFragment?.handleBack()
        } else {
            handleBack()
        }
    }

    override fun onBindPresentationModel(pm: T) {
        errorStateView?.let { stateView -> pm.errorControl.bindTo(stateView) }
        emptyStateView?.let { stateView -> pm.emptyControl.bindTo(stateView) }
        progressView?.let { view -> pm.progressState.bindTo(view.visibility()) }
        homeButtonView?.let { view -> view.setOnClickListener { onBackPressed() } }
        pm.showSnackBarCommand.bindTo(activityContentView.showSnackBar())
    }

    override fun providePresentationModel(): T {
        val pm = factory.createViewModel(classToken)
        pm.router = router
        return pm
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleBack() {
        router.exit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var fragment = supportFragmentManager.findFragmentById(R.id.containerView)
        do {
            fragment?.onActivityResult(requestCode, resultCode, data)
            fragment = fragment?.childFragmentManager?.findFragmentById(R.id.containerView)
        } while (fragment != null)
    }
}