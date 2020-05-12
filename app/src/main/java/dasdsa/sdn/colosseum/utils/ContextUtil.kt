package dasdsa.sdn.colosseum.utils

import android.content.Context

class ContextUtil {
    companion object{

        private val prefName = "colosseumPreference"

        private val USER_TOKEN = "USER_TOKEN"

        fun setUserToken(context: Context, token:String){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(USER_TOKEN, token).apply()
        }

        fun getUserToken(context:Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!
        }

    }
}