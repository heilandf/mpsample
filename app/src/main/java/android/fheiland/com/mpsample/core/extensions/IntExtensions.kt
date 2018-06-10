package android.fheiland.com.mpsample.core.extensions

import kotlin.math.max

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
fun Int.between(minValue: Int, maxValue: Int) : Boolean = this in minValue .. maxValue
