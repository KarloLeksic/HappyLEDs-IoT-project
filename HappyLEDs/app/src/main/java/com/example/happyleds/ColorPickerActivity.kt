package com.example.happyleds

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.slider.Slider
import java.nio.charset.Charset

class ColorPickerActivity : AppCompatActivity() {

    private lateinit var bitmap:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //val url = "https://happyleds.000webhostapp.com/index.php#"
        val url = "https://karlito.website/index.php"
        //val url = "http://192.168.149.181/index.php"
        val queue = Volley.newRequestQueue(this)

        var r: Int = 100
        var g: Int = 255
        var b: Int = 50

        val colorView = findViewById<View>(R.id.colorView)
        val colorCode = findViewById<TextView>(R.id.colorCode)

        val sliderR = findViewById<Slider>(R.id.sliderR)
        val sliderG = findViewById<Slider>(R.id.sliderSpeed)
        val sliderB = findViewById<Slider>(R.id.sliderB)

        // check_LED_status
        val requestBody = "check_LED_status"
        val stringReq : StringRequest =
            object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    var strResp = response.toString()
                    var allData = strResp.split("\n".toRegex()).toTypedArray()
                    var x = allData.elementAt(0).split("\\s".toRegex()).toTypedArray()
                    r = x.elementAt(1).toInt()
                    g= x.elementAt(2).toInt()
                    b= x.elementAt(3).toInt()
                    colorView.setBackgroundColor(Color.rgb(r, g, b))
                    colorCode.text = "RGB: $r, $g, $b"

                    sliderR.value = r.toFloat()
                    sliderG.value = g.toFloat()
                    sliderB.value = b.toFloat()
                },
                Response.ErrorListener{}
            ){
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
        queue.add(stringReq)







        colorView.setBackgroundColor(Color.rgb(r, g, b))
        colorCode.text = "RGB: $r, $g, $b"

        val colorPicker = findViewById<ImageView>(R.id.colorPicker)
        colorPicker.isDrawingCacheEnabled = true
        colorPicker.buildDrawingCache(true)


        colorPicker.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                bitmap = colorPicker.drawingCache
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                r = Color.red(pixel)
                g = Color.green(pixel)
                b = Color.blue(pixel)

                colorView.setBackgroundColor(Color.rgb(r, g, b))
                colorCode.text = "RGB: $r, $g, $b"

                //da se slajderi pomicu kak se mjenja
                sliderR.value = r.toFloat()
                sliderG.value = g.toFloat()
                sliderB.value = b.toFloat()
            }
            true
        }

        sliderR.value = r.toFloat()
        sliderR.addOnChangeListener{slider, value, formUser->
            r = sliderR.getValue().toInt()
            colorView.setBackgroundColor(Color.rgb(r, g, b))
            colorCode.text = "RGB: $r, $g, $b"
        }

        sliderG.value = g.toFloat()
        sliderG.addOnChangeListener{slider, value, formUser->
            g = sliderG.getValue().toInt()
            colorView.setBackgroundColor(Color.rgb(r, g, b))
            colorCode.text = "RGB: $r, $g, $b"
        }

        sliderB.value = b.toFloat()
        sliderB.addOnChangeListener{slider, value, formUser->
            b = sliderB.getValue().toInt()
            colorView.setBackgroundColor(Color.rgb(r, g, b))
            colorCode.text = "RGB: $r, $g, $b"
        }

        val applyBtn = findViewById<Button>(R.id.applyBtn)
        applyBtn.setOnClickListener{
            r = sliderR.getValue().toInt()
            g = sliderG.getValue().toInt()
            b = sliderB.getValue().toInt()

            var Rstr = r.toString()
            var Gstr = g.toString()
            var Bstr = b.toString()

            var send = Rstr + " " + Gstr + " " + Bstr + " " + '0'

            val requestBody = "colors=${send}"
            val stringReq : StringRequest =
                object : StringRequest(
                    Method.POST, url,
                    Response.Listener {},
                    Response.ErrorListener {}
                ){
                    override fun getBody(): ByteArray {
                        return requestBody.toByteArray(Charset.defaultCharset())
                    }
                }
            queue.add(stringReq)

            val Intent = Intent(this, Colors::class.java)
            startActivity(Intent)
        }

    }
}