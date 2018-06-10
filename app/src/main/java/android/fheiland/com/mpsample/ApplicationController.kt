package android.fheiland.com.mpsample

import android.app.Activity
import android.app.Application
import android.fheiland.com.mpsample.core.di.component.DaggerApplicationComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
class ApplicationController : Application(), HasActivityInjector {

    @javax.inject.Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        appControllerInstance = this

        // Init Dagger
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    fun get(): ApplicationController? {
        return appControllerInstance
    }

    companion object {
        var appControllerInstance: ApplicationController? = null
    }
}
