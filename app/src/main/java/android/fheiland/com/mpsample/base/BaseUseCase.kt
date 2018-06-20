package android.fheiland.com.mpsample.base

/**
 * Created by Federico Heiland - Quadion Technologies
 * 06/06/2018
 */

import com.google.android.gms.common.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * This interface represents a execution unit for different use cases
 * Each BaseUseCase implementation will return the result using a DisposableObserver
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class BaseUseCase<T, Params> {
    private val disposables: CompositeDisposable = CompositeDisposable()

     // Observable that will be used when executing the current use case.
    internal abstract fun buildUseCaseObservable(params: Params?): Observable<T>

    /**
     * Executes the current use case.
     *
     * @param observer DisposableObserver which will be listening to the observable built
     * @param params Parameters used to build/execute this use case.
     */
    open fun execute(observer: DisposableObserver<T>, params: Params?) {
        Preconditions.checkNotNull(observer)
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        addDisposable(observable.subscribeWith(observer))
    }

    open fun execute(observer: DisposableObserver<T>, params: Params?, s: Scheduler, p: Scheduler ) {
        Preconditions.checkNotNull(observer)
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(s)
                .observeOn(p)

        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current CompositeDisposable.
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Add disposables to CompositeDisposable so we can dispose them when they are no longer needed
     */
    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }
}
