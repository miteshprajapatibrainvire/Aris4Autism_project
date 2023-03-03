package com.example.aris4autism_project.Utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.aris4autism_project.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    @SuppressLint("InflateParams")
    fun InternetNotAvailableToast(context:Context)
    {
        val toastData= Toast(context)
        val inflaterLayout=LayoutInflater.from(context)
        toastData.view=inflaterLayout.inflate(R.layout.layout_item_toast_bottom,null)
        toastData.setGravity(0,10,1002)
        toastData.show()
    }


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
            cal.time = b!!
            b = a
        }
        var c = 0
        while (cal.time.before(b)) {
            cal.add(Calendar.MONTH, 1)
            c++
        }
        return c - 1
    }


     fun clickDatePicker(context:Context):String
    {
        var strData:String=""
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                val monthData = m + 1
                 strData= d.toString() + "/" + monthData.toString() + "/" + y.toString()

            },
            year,
            month,
            day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()

        return strData
    }



}