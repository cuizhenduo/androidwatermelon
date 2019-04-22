package mj.czd.cn.watermelon

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        submit_btn.setOnClickListener {
            submit_btn.setEnabled(false)

            val username = username.text
            val passwd = passwd.text

            var requestBody: RequestBody = FormBody.Builder().add("username", username.toString()).add("password", passwd.toString()).build()
            postdata(requestBody,"http://10.0.2.2:8000/al/",username.toString())
        }

    }
    fun postdata(requestBody: RequestBody,ourl:String,username:String) {

        Thread(Runnable {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(ourl).post(requestBody).build()
                val response = client.newCall(request).execute()
                val respdata = response.body().string()
                showResponse(respdata,username)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }
    fun showResponse(response: String,username: String) {
        runOnUiThread {

            if (response == "ok"){
                var editor: SharedPreferences.Editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
                editor.putString("username",username)
                editor.apply()
                //toast("登录成功")
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                val sintent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(sintent)


            }

            if (response == "noeq"){
                //toast("用户名或密码错误")
                Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                submit_btn.setEnabled(true)
            }
        }
    }
}
