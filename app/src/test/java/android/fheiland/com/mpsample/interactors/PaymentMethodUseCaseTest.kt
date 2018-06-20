package android.fheiland.com.mpsample.interactors

import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.methods.interactor.PaymentMethodListUseCase
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations


/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
class PaymentMethodUseCaseTest {

    private lateinit var paymentMethodUseCase: PaymentMethodListUseCase
    @Mock
    private lateinit var  mockUserRepository: PaymentRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        paymentMethodUseCase = PaymentMethodListUseCase(mockUserRepository)
    }

    @Test
    fun testUseCaseObservableHappyCase() {
        paymentMethodUseCase.buildUseCaseObservable(null)
        verify<PaymentRepository>(mockUserRepository).getPaymentMethods(BuildConfig.PUBLIC_KEY)
        verifyNoMoreInteractions(mockUserRepository)
    }
}