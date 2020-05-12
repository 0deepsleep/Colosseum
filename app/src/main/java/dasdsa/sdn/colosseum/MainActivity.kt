package dasdsa.sdn.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import dasdsa.sdn.colosseum.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val email = emailEdt.text.toString()
            val pw = pwEdt.text.toString()

            ServerUtil.postRequestLogin(mContext,email,pw,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    //Log.d("로그인응답",json.toString())

                    val code = json.getInt("code")

                    if (code ==200){



                    }
                    else{
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext,message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            })
        }

    }

    override fun setValues() {

    }

}

