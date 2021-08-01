package com.favwest.medicalrpg

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeCalcTest{
    private val tc = TimeCalc()

    @Test
    fun `given 59, returns 1859`(){
        assertEquals("1859", tc.buildTimeString(59))
    }

    @Test
    fun `given 359, returns 2359`(){
        assertEquals("2359", tc.buildTimeString(359))
    }

    @Test
    fun `given 360, returns 100`(){
        assertEquals("100", tc.buildTimeString(360))
    }

    @Test
    fun `given 375, returns 115`(){
        assertEquals("115", tc.buildTimeString(375))
    }
}