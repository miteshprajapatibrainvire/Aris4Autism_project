package com.example.aris4autism_project.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open abstract class ResponseWrapper<T> {

    @SerializedName("data")
    @Expose
    var data: T? = null

    var jsonArray: JsonArray = JsonArray()
    var jsonObject: JsonObject = JsonObject()


    @SerializedName(value = "error", alternate = ["errors"])
    @Expose
    var jsonError: JsonObject? = null

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null

    /*  override fun toString(): String {
          return "ResponseWrapper{" +
                  "data=" +
                  when (data) {
                      null -> {
                          JsonObject()
                      }
                      is JsonArray -> {
                          data
                      }
                      else -> {
                          data
                      }
                  } +
                  ", jsonError=" + jsonError +
                  ", meta=" + meta +
                  '}'.toString()
      }*/

    inner class Meta {
        @SerializedName("status_code")
        @Expose
        var code: Int? = null
        @SerializedName("status")
        @Expose
        var status: String? = null
        @SerializedName("message")
        @Expose
        var message: String? = null
        @SerializedName("message_code")
        @Expose
        var message_code: String? = null
    }
}
