package android.fheiland.com.mpsample.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.fheiland.com.mpsample.payment.issuers.interactor.CardIssuerListUseCase
import android.fheiland.com.mpsample.payment.issuers.viewmodel.CardIssuerViewModel
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
import org.junit.Assert

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
class CardIssuerViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var  useCase: CardIssuerListUseCase
    @Mock
    private lateinit var paymentMethod: PaymentMethod

    private var viewModel: CardIssuerViewModel? = null

    @Before
    fun initViewModel() {
        MockitoAnnotations.initMocks(this)
        viewModel = CardIssuerViewModel(useCase)
    }

    @Test
    fun viewModelNotNull() {
        Assert.assertTrue(viewModel != null)
    }

    @Test
    fun selectedMethodShouldBeNull() {
        Assert.assertTrue(viewModel?.selectedPaymentMethod == null)
    }

    @Test
    fun testSelectedMethod() {
        viewModel?.fetchIssuersList(paymentMethod)
        Assert.assertTrue(viewModel?.selectedPaymentMethod != null)
    }
}