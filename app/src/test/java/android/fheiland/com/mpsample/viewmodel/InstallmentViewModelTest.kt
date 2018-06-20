package android.fheiland.com.mpsample.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.fheiland.com.mpsample.payment.installments.interactor.InstallmentsUseCase
import android.fheiland.com.mpsample.payment.installments.interactor.OutputStorageUseCase
import android.fheiland.com.mpsample.payment.installments.viewmodel.InstallmentsViewModel
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
class InstallmentViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var  useCase: InstallmentsUseCase
    @Mock
    private lateinit var storageUseCase: OutputStorageUseCase

    private var viewModel: InstallmentsViewModel? = null

    @Before
    fun initViewModel() {
        MockitoAnnotations.initMocks(this)
        viewModel = InstallmentsViewModel(useCase, storageUseCase)
    }

    @Test
    fun viewModelNotNull() {
        Assert.assertTrue(viewModel != null)
    }

}