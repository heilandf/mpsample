package android.fheiland.com.mpsample.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.fheiland.com.mpsample.payment.amount.viewmodel.AmountViewModel
import org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
class AmountViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private var amountViewModel: AmountViewModel? = null
    @Before
    fun initViewModel() {
        amountViewModel = AmountViewModel()
    }

    @Test
    fun viewModelNotNull() {
        assertTrue(amountViewModel != null)
    }

    @Test
    fun buttonIsNotEnabled() {
        val value = amountViewModel?.moveForwardButtonEnabled?.value
        assertTrue(!value!!)
    }

    @Test
    fun buttonIsEnabledAfterInput() {
        amountViewModel?.saveInputAmount("123")
        val value = amountViewModel?.moveForwardButtonEnabled?.value
        assertTrue(value!!)
    }

    @Test
    fun testSizeValues() {
        amountViewModel?.saveInputAmount("123")
        assertTrue(amountViewModel?.maxSizeText?.value!!)

        amountViewModel?.saveInputAmount("1234567891")
        assertTrue(amountViewModel?.midSizeText?.value!!)

        amountViewModel?.saveInputAmount("1231234567891")
        assertTrue(amountViewModel?.normalSizeText?.value!!)
    }

    @Test
    fun moveForwardChangedAfterAbleToMoveOn() {
        amountViewModel?.moveForward(true)
        assertTrue(amountViewModel?.moveForward?.value!!)
    }

}