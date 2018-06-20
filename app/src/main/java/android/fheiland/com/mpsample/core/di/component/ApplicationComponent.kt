package android.fheiland.com.mpsample.core.di.component

import android.fheiland.com.mpsample.base.ApplicationController
import android.fheiland.com.mpsample.core.di.module.ApplicationModule
import android.fheiland.com.mpsample.core.di.module.BuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (ApplicationModule::class), (BuildersModule::class)])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ApplicationController): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: ApplicationController)
}