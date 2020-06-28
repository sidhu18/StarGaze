package com.ambient.stargaze.helpers

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object StringUtils{
    fun formatDateToString(date: Date) : String{
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return dateFormat.format(date)
    }

    fun changeDateFormat(dateString : String) : String{
        if(dateString == "") return ""
        val date = SimpleDateFormat("yyyy-MM-dd",Locale.US).parse(dateString)
        val sdf: SimpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return sdf.format(date)
    }
}
