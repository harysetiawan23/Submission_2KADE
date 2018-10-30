package com.example.harry.submission_2kade.Helper

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    /** Converting from String to Date **/
   public fun String.getDateWithServerTimeStamp(): Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
        try {
            return dateFormat.parse(this)
        } catch (e: ParseException) {
            return null
        }
    }

    /** Converting from Date to String**/
    public fun Date.getStringTimeStampWithDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormat.format(this)
    }


}
