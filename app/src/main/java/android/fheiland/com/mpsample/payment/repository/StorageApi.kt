package android.fheiland.com.mpsample.payment.repository

import android.fheiland.com.mpsample.payment.output.OutputEntity
import io.reactivex.Observable

/**
 * Created by Federico Heiland - Quadion Technologies
 * 16/06/2018
 */
interface StorageApi {
    fun storeOutputEntity(output: OutputEntity?) : Observable<Boolean>
    fun fetchEntities() : Observable<List<OutputEntity>>
}