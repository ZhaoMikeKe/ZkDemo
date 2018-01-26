package com.example.lenovo.mydemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.lenovo.mydemo.bean.RWBeanHS
import com.example.lenovo.mydemo.http.APIService
import kotlinx.android.synthetic.main.activity_kotlin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class KotlinActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        button.text = "hulun"
        button.setOnClickListener {
            Snackbar.make(button, "出现", Snackbar.LENGTH_INDEFINITE)
                    .setAction("确定", {
                        getRenWu()
                    }).setActionTextColor(resources.getColor(R.color.blueviolet)).show()
        }
    }

    private fun getRenWu() {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/tools/mockapi/2194/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val renWu = retrofit.create(APIService.renwu::class.java)
        val params = HashMap<String, String>()
        params.put("available", "1")
        params.put("delFlag", "0")
        val call = renWu.Getrenwu(params)

        call.enqueue(object : Callback<List<RWBeanHS>> {
            override fun onResponse(call: Call<List<RWBeanHS>>, response: Response<List<RWBeanHS>>) {
                val renWuBean = response.body()
                textView.text = renWuBean.get(0).toString()
            }

            override fun onFailure(call: Call<List<RWBeanHS>>, t: Throwable) {
                //textView.text = t.message
            }
        })

    }
}
