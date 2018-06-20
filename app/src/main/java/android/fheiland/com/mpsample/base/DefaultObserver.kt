package android.fheiland.com.mpsample.base
import io.reactivex.observers.DisposableObserver

/**
 * Created by Federico Heiland - Quadion Technologies
 * 06/06/2018
 */
/**
 * Default DisposableObserver base class to be used whenever you want default error handling.
 */
open class DefaultObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {}
    override fun onComplete() {}
    override fun onError(exception: Throwable) {}
}