package com.appname.structure.network.Client

import com.example.aris4autism_project.api.Utils
import com.example.aris4autism_project.api.Validation
import com.google.gson.JsonObject

/**
 * Created by rajanbhavsar on 20/11/19, 2:02 PM
 * Package Name : com.appname.structure.network.Client
 * Project Name : BrainvireStructure
 */

object HttpCommonMethod {

    /**
     * check whether API response is success or not
     */
    fun isSuccessResponse(responseCode: Int): Boolean {

        return responseCode in 200..300
    }

    /**
     * check Error Message
     */
    fun getErrorMessage(error: JsonObject?): String {
        var value = ""
        if (error != null) {
            val obj = error.asJsonObject //since you know it's a JsonObject
            try {
                if (obj != null) {
                    val entries = obj.entrySet()//will return members of your object
                    for ((key, value1) in entries) {
                        println(key)
                        if (Validation.isNotNull(value)) {
                            value =
                                value + "," + Utils.removeArrayBrace(value1.asJsonArray.toString())
                        } else {
                            value = Utils.removeArrayBrace(value1.asJsonArray.toString())
                        }
                    }
                }
            } catch (e: Exception) {
                //DebugLog.print(e)
                value = ""
            }

        }
        return value
    }

 /**
 * get Authorized token
 */
  //  fun getToken(): String {
//        return MyPreference.getValueString(
//            PrefKey.TOKEN_TYPE,
//            ""
//        ) + " " + MyPreference.getValueString(
//            PrefKey.ACCESS_TOKEN,
//            ""
//        )
   // }

    /**
     * get Language code
     */
//    fun getLanguageCode(): String {
//        return MyPreference.getValueString(
//            PrefKey.SELECTED_LANGUAGE,
//            "en"
//        ).toString()
//    }


    /**
     * get UUID of User
     */
    /*fun getUUID(mContext: Context): String? {
        return MyPreference.getValueString(mContext, PrefKey.USER_UUID, "")
    }*/


}
