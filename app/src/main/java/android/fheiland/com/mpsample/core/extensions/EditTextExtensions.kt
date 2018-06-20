package android.fheiland.com.mpsample.core.extensions

import android.widget.EditText

/**
 * Created by Federico Heiland - Quadion Technologies
 * 17/06/2018
 */
fun EditText.avoidZeroInput(text: String) {
    if (text.length == 1 && text == "0")
        this.setText("")
}