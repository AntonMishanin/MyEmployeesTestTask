package com.my.employees_domain.specialties

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
internal class ChooseSpecialtyUseCaseTest {

    @Test
    fun `test choose specialties`() = runBlocking {
        val repository = SpecialtiesRepositoryMock()
        val chooseSpecialtyUseCase = ChooseSpecialtyUseCase(repository)
        val specialties = repository.fetchSpecialties()
        val firstId = specialties[0].id

        chooseSpecialtyUseCase.invoke(firstId)

        var expected = true
        var actual = repository.fetchSpecialties()[0].isActive
        Assert.assertEquals(expected, actual)

        val middleId = specialties[specialties.size/2].id

        chooseSpecialtyUseCase.invoke(middleId)

        expected = true
        actual = repository.fetchSpecialties()[specialties.size/2].isActive
        Assert.assertEquals(expected, actual)
    }
}