package com.example.happyleds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.slider.Slider
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.sql.DriverManager
import java.util.*

class Colors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)

        val increaseBtn = findViewById<ImageView>(R.id.increaseBrightness)
        val decreaseBtn = findViewById<ImageView>(R.id.decreaseBrightness)

        //val url = "https://happyleds.000webhostapp.com/index.php#"
        val url = "https://karlito.website/index.php"
        //val url = "http://192.168.149.181/index.php"
        val queue = Volley.newRequestQueue(this)

        val powerSwitch = findViewById<Switch>(R.id.powerSwitch)
        val slider = findViewById<Slider>(R.id.sliderBrightness)

        var brightness: Int = 0
        val brightnessStep:Int = 20
        var power = 0

        // check_LED_status
        val requestBody = "check_LED_status"
        val stringReq : StringRequest =
            object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    var strResp = response.toString()
                    var allData = strResp.split("\n".toRegex()).toTypedArray()
                    var x = allData.elementAt(0).split("\\s".toRegex()).toTypedArray()
                    var status = x.elementAt(0)
                    var br = x.elementAt(4)

                    brightness = br.toInt()
                    slider.value = brightness.toFloat()
                    if(status.toInt() == 1){
                        powerSwitch.setChecked(true)
                    } else{
                        powerSwitch.setChecked(false)
                    }
                },
                Response.ErrorListener{}
            ){
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
        queue.add(stringReq)


        increaseBtn.setOnClickListener{
            brightness += brightnessStep
            if (brightness > 255){
                brightness = 255
            }
            slider.value = brightness.toFloat()
        }

        decreaseBtn.setOnClickListener{
            brightness -= brightnessStep
            if(brightness < 0){
                brightness = 0
            }
            slider.value = brightness.toFloat()
        }

        //power switch
        powerSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                power = 1
                val requestBody = "power=${power}"
                val stringReq : StringRequest =
                    object : StringRequest(Method.POST, url,
                        Response.Listener {},
                        Response.ErrorListener{}
                    ){
                        override fun getBody(): ByteArray {
                            return requestBody.toByteArray(Charset.defaultCharset())
                        }
                    }
                queue.add(stringReq)
            }
            else{
                power = 0
                val requestBody = "power=${power}"
                val stringReq : StringRequest =
                    object : StringRequest(Method.POST, url,
                        Response.Listener {},
                        Response.ErrorListener {}
                    ){
                        override fun getBody(): ByteArray {
                            return requestBody.toByteArray(Charset.defaultCharset())
                        }
                    }
                queue.add(stringReq)
            }
        }
/*
        slider.addOnChangeListener { slider, value, fromUser ->
            Toast.makeText(baseContext, value.toString(), Toast.LENGTH_LONG).show()
        }*/

        //Slider for brightness
        slider.addOnChangeListener{slider, value, formUser->
            brightness = slider.value.toInt()
            val requestBody = "brightness=${brightness}"
            val stringReq : StringRequest =
                object : StringRequest(Method.POST, url,
                    Response.Listener {},
                    Response.ErrorListener {}
                ){
                    override fun getBody(): ByteArray {
                        return requestBody.toByteArray(Charset.defaultCharset())
                    }
                }
            queue.add(stringReq)
        }

        val colorsBtn = findViewById<Button>(R.id.colors)
        colorsBtn.setOnClickListener{
            val colorsFragment = ColorsFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, colorsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            //Toast.makeText(this, gori, Toast.LENGTH_SHORT).show()
        }

        val effectsBtn = findViewById<Button>(R.id.effects)
        effectsBtn.setOnClickListener{
            val effectsFragment = EffectsFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()

            transaction.replace(R.id.fragmentContainerView, effectsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}