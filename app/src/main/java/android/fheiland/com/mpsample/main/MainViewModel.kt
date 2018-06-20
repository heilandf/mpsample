package android.fheiland.com.mpsample.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.fheiland.com.mpsample.payment.repository.StorageRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Federico Heiland - Quadion Technologies
 * 16/06/2018
 */
class MainViewModel(var storageRepository: StorageRepository) : ViewModel() {
    val entities = MutableLiveData<List<OutputEntity>>()
    private var compositeDisposable = CompositeDisposable()

    init {
        val useCaseObservable = buildUseCaseObservable()
        compositeDisposable.add(useCaseObservable.subscribe({ entities.postValue(it) }))
    }

    private fun buildUseCaseObservable(): Observable<List<OutputEntity>> {
        return storageRepository.fetchEntities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun disposeObservables() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}