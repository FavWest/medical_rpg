package com.favwest.medicalrpg

import org.junit.Assert.assertEquals
import org.junit.Test

class SelectPatientViewModelTest{
    private val viewModel = SelectPatientViewModel()

    @Test
    fun `given 59, returns 1859`(){
        assertEquals("1859", viewModel.buildTimeString(59))
    }

    @Test
    fun `given 359, returns 2359`(){
        assertEquals("2359", viewModel.buildTimeString(359))
    }

    @Test
    fun `given 360, returns 100`(){
        assertEquals("100", viewModel.buildTimeString(360))
    }

    @Test
    fun `given 375, returns 115`(){
        assertEquals("115", viewModel.buildTimeString(375))
    }
}