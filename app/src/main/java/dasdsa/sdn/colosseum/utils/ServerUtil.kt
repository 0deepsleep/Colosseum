package dasdsa.sdn.colosseum.utils

import android.content.Context
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {

        private val BASE_URL = "http://ec2-15-165-177-142.ap-northeast-2.compute.amazonaws.com/"

        fun postRequestLogin(
            context: Context,
            id: String,
            pw: String,
            handler: JsonResponseHandler?
        ) {

            val client = OkHttpClient()
            val urlStr = "${BASE_URL}/user"

            val formBody = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

            val request = Request.Builder()
                .url(urlStr)
                .post(formBody)
//                .header()  => API가 헤더를 요구하면 추가해야함.
                .build()

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {

                    val body = response.body!!.string()
                    val json = JSONObject(body)

                    handler?.onResponse(json)


                }

            })


        }

        fun getRequestMyInfo(context: Context, token:String, handler: JsonResponseHandler?) {

            val client = OkHttpClient()
            val urlBuilder = "${BASE_URL}/my_info".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("device_token", "임시기기토큰")
            urlBuilder.addEncodedQueryParameter("os", "Android")

            val urlStr = urlBuilder.build().toString()

//            Log.d("완성된주소", urlStr)

            val request = Request.Builder()
                .url(urlStr)
                .header("X-Http-Token", token)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)
                }
            })
        }

        fun getRequestUserCategory(context: Context, handler: JsonResponseHandler?) {

            val client = OkHttpClient()
            val urlBuilder = "${BASE_URL}/system/user_category".toHttpUrlOrNull()!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter("device_token", "임시기기토큰")
//            urlBuilder.addEncodedQueryParameter("os", "Android")

            val urlStr = urlBuilder.build().toString()

//            Log.d("완성된주소", urlStr)

            val request = Request.Builder()
                .url(urlStr)
//                .header("X-Http-Token", token)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)
                }
            })
        }

    }


}