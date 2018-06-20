package android.fheiland.com.mpsample.payment.installments.interactor

import android.fheiland.com.mpsample.base.BaseUseCase
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.fheiland.com.mpsample.payment.repository.StorageApi
import dagger.internal.Preconditions
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
open class OutputStorageUseCase
@Inject
constructor(private val storageApi: StorageApi) : BaseUseCase<Boolean, OutputStorageUseCase.Params?>() {
    override fun buildUseCaseObservable(params: Params?): Observable<Boolean> {
        Preconditions.checkNotNull(params)
        return storageApi.storeOutputEntity(params?.output)
    }

    class Params private constructor(var output: OutputEntity) {
        companion object {
            fun withOutput(entity: OutputEntity) : Params = Params(entity)
        }
    }
}