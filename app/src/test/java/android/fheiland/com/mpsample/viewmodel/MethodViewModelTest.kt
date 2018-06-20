package android.fheiland.com.mpsample.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.fheiland.com.mpsample.payment.methods.interactor.PaymentMethodListUseCase
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
import android.fheiland.com.mpsample.payment.methods.viewmodel.MethodViewModel
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
class MethodViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var  useCase: PaymentMethodListUseCase
    @Mock var selectedMethod = PaymentMethod()
    private var viewModel: MethodViewModel? = null


    @Before
    fun initViewModel() {
        MockitoAnnotations.initMocks(this)
        viewModel = MethodViewModel(useCase)
    }

    @Test
    fun viewModelNotNull() {
        Assert.assertTrue(viewModel != null)
    }

    @Test
    fun selectedMethodShouldBeNull() {
        Assert.assertTrue(viewModel?.selectedPaymentMethod?.value == null)
    }

    @Test
    fun testSelectedMethod() {
        viewModel?.selectedMethod(selectedMethod)
        Assert.assertTrue(viewModel?.selectedPaymentMethod?.value != null)
    }
}