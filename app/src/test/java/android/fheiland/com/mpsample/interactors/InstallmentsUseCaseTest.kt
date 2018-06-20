package android.fheiland.com.mpsample.interactors

import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.installments.interactor.InstallmentsUseCase
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
class InstallmentsUseCaseTest {
    @Mock
    private lateinit var  mockUserRepository: PaymentRepository
    @Rule
    @JvmField
    var expectedException: ExpectedException = ExpectedException.none()
    private val mockMethodId = "visa"
    private val mockAmount = "1234"
    private val mockedIssuer = "icbc"
    private lateinit var useCase: InstallmentsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = InstallmentsUseCase(mockUserRepository)
    }

    @Test
    fun testShouldFailWhenNoOrEmptyParameters() {
        expectedException.expect(NullPointerException::class.java)
        useCase.buildUseCaseObservable(null)
    }

    @Test
    fun testUseCaseObservableHappyCase() {
        useCase.buildUseCaseObservable(InstallmentsUseCase.Params(mockAmount, mockMethodId, mockedIssuer))
        Mockito.verify<PaymentRepository>(mockUserRepository).getInstallmentsByCreditCard(BuildConfig.PUBLIC_KEY, mockAmount, mockMethodId, mockedIssuer)
        Mockito.verifyNoMoreInteractions(mockUserRepository)
    }

}