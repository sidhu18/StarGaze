package com.ambient.stargaze.helpers

import org.junit.Test

import org.junit.Assert.*

class StringUtilsTest {

    @Test
    fun formatDateToString() {
        val input = "2020-01-09"
        val expected = "09 Jan 2020"
        assertEquals(expected, StringUtils.changeDateFormat(input))
    }

    @Test
    fun changeDateFormat() {
    }

    @Test
    fun getYoutubeVideoId() {
        val s = "https://www.youtube.com/embed/M6-iC_aYcug?rel=0"
        val r = "M6-iC_aYcug"
        assertEquals(r, StringUtils.getYoutubeVideoId(s))
    }
}