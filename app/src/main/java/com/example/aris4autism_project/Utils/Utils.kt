package com.example.aris4autism_project.Utils

import android.graphics.Typeface
import com.google.gson.Gson
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object Utils {

    fun dobToAge(dateOfBirth:String): String
    {
        return if (!Utils.checkDateFormat(dateOfBirth, CalenderFormat.MM_DD_YYYY_D.type)) {
            val formatter: DateFormat =
                SimpleDateFormat(CalenderFormat.YYYY_MM_DD.type, Locale.ROOT)
            val formatter2: DateFormat =
                SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
            val date = formatter.parse(dateOfBirth) as Date
            val date2 = formatter2.format(date)
            Utils.calculateAge(date2)
        } else {
            Utils.calculateAge(dateOfBirth)
        }
    }


    fun checkDateFormat(dates: String, format: String): Boolean {
        var date: Date? = null
        try {
            val sdf = SimpleDateFormat(format, Locale.ROOT)
            date = sdf.parse(dates)
            if (dates != sdf.format(date)) {
                date = null
            }
        } catch (ex: ParseException) {
            ex.printStackTrace()
        }
        return date != null
    }


    fun calculateAge(birthDate: String): String {

        var years = 0
        var months = 0
        var days = 0

        //create calendar object for birth day
        val birthDay = Calendar.getInstance()
        val formatter: DateFormat = SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
        val date = formatter.parse(birthDate) as Date
        birthDay.timeInMillis = date.time

        //create calendar object for current day
        val currentTime = System.currentTimeMillis()

        //Get difference between months
        val mon = monthsBetween(date, Date(currentTime)) //currMonth - birthMonth
        years = mon / 12
        months = mon - (years * 12)

        val monthStr = if (months <= 1) {
            "$months month"
        } else if (months > 1) {
            "$months Months"
        } else {
            ""
        }

        val yearStr = if (years == 1) {
            "$years Year"
        } else if (years > 1) {
            "$years Years"
        } else {
            ""
        }

        return "$yearStr $monthStr"
    }

    private fun monthsBetween(a: Date, b: Date?): Int {
        var b = b
        val cal = Calendar.getInstance()
        if (a.before(b)) {
            cal.time = a
        } else {
            cal.time = b
            b = a
        }
        var c = 0
        while (cal.time.before(b)) {
            cal.add(Calendar.MONTH, 1)
            c++
        }
        return c - 1
    }



}