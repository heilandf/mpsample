package android.fheiland.com.mpsample.core.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
fun View.visibility(flag: Boolean) {
    this.visibility = when (flag) {
        true -> VISIBLE
        else -> GONE
    }
}