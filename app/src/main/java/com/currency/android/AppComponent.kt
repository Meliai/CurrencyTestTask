package com.currency.android

import android.content.Context
import com.currency.android.data.di.ApiConstantsModule
import com.currency.android.data.di.ApiModule
import com.currency.android.data.di.DataSourceModule
import com.currency.android.data.di.InterceptorModule
import com.currency.android.data.di.MappersModule
import com.currency.android.data.di.NetworkModule
import com.currency.android.data.di.RepoModule
import com.currency.android.presentation.di.ActivityBuilder
import com.currency.android.presentation.di.FragmentBuilder
import com.currency.android.presentation.di.NavigationModule
import com.currency.android.presentation.di.PmModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    // common
    AndroidSupportInjectionModule::class,
    AppModule::class,
    // data
    ApiModule::class,
    ApiConstantsModule::class,
    NetworkModule::class,
    DataSourceModule::class,
    MappersModule::class,
    // domain
    RepoModule::class,
    // presentation
    PmModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    // navigation
    NavigationModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun appModule(module: AppModule): Builder

        fun apiConstantsModule(module: ApiConstantsModule): Builder

        fun interceptorModule(module: InterceptorModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)

    fun context(): Context
}