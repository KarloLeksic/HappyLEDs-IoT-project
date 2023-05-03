package com.example.happyleds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.nio.charset.Charset

class EffectDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_effect_detail)

        val effect = intent.getParcelableExtra<Effect>("effect")

       if(effect != null){
           val textView :TextView = findViewById(R.id.effectName)

           textView.text = effect.text

           val btn = findViewById<Button>(R.id.setEffectBtn)
           btn.setOnClickListener {
               //val url = "https://happyleds.000webhostapp.com/index.php#"
               val url = "https://karlito.website/index.php"
               val queue = Volley.newRequestQueue(this)
               val send = effect.text.length

               val requestBody = "effect=${send}"
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

               startActivity(Intent(this, Colors::class.java))
           }
        }
    }
}