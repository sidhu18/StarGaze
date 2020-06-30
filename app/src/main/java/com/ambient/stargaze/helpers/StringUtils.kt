package com.ambient.stargaze.helpers

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object StringUtils {

    @JvmStatic
    fun formatDateToString(date: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return dateFormat.format(date)
    }

    @JvmStatic
    fun changeDateFormat(dateString: String?): String? {
        if (dateString == "" || dateString == null) return ""
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(dateString)
        val sdf: SimpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
//        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return sdf.format(date)
    }

    @JvmStatic
    fun getYoutubeVideoId(url: String?): String? {
        val pattern = Pattern.compile(
            "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher = pattern.matcher(url)
        if (matcher.find()) {
            return (matcher.group(1))
        }
        return ""
    }
}
