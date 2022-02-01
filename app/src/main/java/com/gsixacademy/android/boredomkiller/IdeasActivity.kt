package com.gsixacademy.android.boredomkiller

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gsixacademy.android.boredomkiller.api.BoredApi
import com.gsixacademy.android.boredomkiller.api.ServiceBuilder
import com.gsixacademy.android.boredomkiller.models.ActivityResponse
import kotlinx.android.synthetic.main.activity_idea.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IdeasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea)

        val request = ServiceBuilder.buildService(BoredApi::class.java)
        val call = request.getRandomActivity()
        call.enqueue(object : Callback<ActivityResponse>{
            override fun onResponse(
                call: Call<ActivityResponse>,
                response: Response<ActivityResponse>
            ) {
                if (response.isSuccessful){
                    val activityList = response.body()
                    if (activityList != null){
                        text_accessibility.text = activityList.accessibility.toString()
                        text_activity.text = activityList.activity
                        text_participants.text = activityList.participants.toString()
                        text_price.text = activityList.price.toString()
                        text_type.text = activityList.type
                        if (!activityList.link.isNullOrEmpty()) {
                            tvLink.visibility = View.VISIBLE
                            text_link.visibility = View.VISIBLE
                            text_link.text = activityList.link
                        } else {
                            tvLink.visibility = View.GONE
                            text_link.visibility = View.GONE
                        }
                            text_link.setOnClickListener {
                                val realUrl = if (activityList.link!!.startsWith("http"))activityList.link else "https://${activityList.link}"
                                val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(realUrl))
                                startActivity(urlIntent)


                            }
                        }

                    btnNext.setOnClickListener {
                        call.clone().enqueue(object : Callback<ActivityResponse>{
                            override fun onResponse(
                                call: Call<ActivityResponse>,
                                response: Response<ActivityResponse>
                            ) {
                                if(response.isSuccessful){
                                    val newActivity = response.body()
                                    if (newActivity != null){
                                        text_type.text = newActivity.type
                                        text_price.text = newActivity.price.toString()
                                        text_participants.text = newActivity.participants.toString()
                                        text_activity.text = newActivity.activity
                                        text_accessibility.text = newActivity.accessibility.toString()
                                        if (!newActivity.link.isNullOrEmpty()){
                                            tvLink.visibility = View.VISIBLE
                                            text_link.visibility = View.VISIBLE
                                            text_link.text = newActivity.link
                                        } else {
                                            tvLink.visibility = View.GONE
                                            text_link.visibility = View.GONE
                                        }
                                        text_link.setOnClickListener {
                                            val realUrl = if (newActivity.link!!.startsWith("http"))newActivity.link else "https://${newActivity.link}"
                                            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(realUrl))
                                            startActivity(urlIntent)
                                        }
                                    }
                                }
                            }

                            override fun onFailure(call: Call<ActivityResponse>, t: Throwable) {
                                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                    }
                }


            override fun onFailure(call: Call<ActivityResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error!!", Toast.LENGTH_SHORT).show()
            }

        })
    }


}