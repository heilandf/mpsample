package android.fheiland.com.mpsample.interactors

import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.issuers.interactor.CardIssuerListUseCase
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
class CardIssuerListUseCaseTest {
    @Mock
    private lateinit var  mockUserRepository: PaymentRepository
    @Rule
    @JvmField
    var expectedException: ExpectedException = ExpectedException.none()
    private val mockMethodId = "123"
    private lateinit var useCase: CardIssuerListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = CardIssuerListUseCase(mockUserRepository)
    }

    @Test
    fun testShouldFailWhenNoOrEmptyParameters() {
        expectedException.expect(NullPointerException::class.java)
        useCase.buildUseCaseObservable(null)
    }

    @Test
    fun testUseCaseObservableHappyCase() {
        useCase.buildUseCaseObservable(CardIssuerListUseCase.Params.forMethod(mockMethodId))
        Mockito.verify<PaymentRepository>(mockUserRepository).getCardIssuersByMethod(BuildConfig.PUBLIC_KEY, mockMethodId)
        Mockito.verifyNoMoreInteractions(mockUserRepository)
    }
}