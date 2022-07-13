package com.my.employees_domain.specialties

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
internal class ResetSpecialtyUseCaseTest {

    @Test
    fun `test reset specialty`() = runBlocking {
        val repository = SpecialtiesRepositoryMock()
        val resetSpecialtyUseCase = ResetSpecialtyUseCase(repository)

        resetSpecialtyUseCase.invoke()

        repository.fetchSpecialties().forEach { specialty ->
            Assert.assertEquals(false, specialty.isActive)
        }
    }
}