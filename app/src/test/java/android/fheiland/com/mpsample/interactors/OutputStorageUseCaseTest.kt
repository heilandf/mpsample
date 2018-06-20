package android.fheiland.com.mpsample.interactors

import android.fheiland.com.mpsample.payment.installments.interactor.OutputStorageUseCase
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.fheiland.com.mpsample.payment.repository.StorageRepository
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
class OutputStorageUseCaseTest {

    @Rule
    @JvmField
    var expectedException: ExpectedException = ExpectedException.none()
    @Mock
    private lateinit var storageRepository: StorageRepository
    @Mock
    private lateinit var mockOutputEntity: OutputEntity

    private lateinit var useCase: OutputStorageUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = OutputStorageUseCase(storageRepository)
    }

    @Test
    fun testShouldFailWhenNoOrEmptyParameters() {
        expectedException.expect(NullPointerException::class.java)
        useCase.buildUseCaseObservable(null)
    }

    @Test
    fun testUseCaseObservableHappyCase() {
        useCase.buildUseCaseObservable(OutputStorageUseCase.Params.withOutput(mockOutputEntity))
        Mockito.verify<StorageRepository>(storageRepository).storeOutputEntity(mockOutputEntity)
        Mockito.verifyNoMoreInteractions(storageRepository)
    }


}