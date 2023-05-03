package com.example.happyleds

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.nio.charset.Charset

class ColorsFragment : Fragment() {

    fun setColor(r:Int, g:Int, b:Int){
        val Rstr = r.toString()
        val Gstr = g.toString()
        val Bstr = b.toString()

        val send = Rstr + " " + Gstr + " " + Bstr + " " + '0'

        //val url = "http://192.168.149.181/index.php"
        //val url = "https://happyleds.000webhostapp.com/index.php#"
        val url = "https://karlito.website/index.php"
        val queue = Volley.newRequestQueue(activity)

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_colors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorBtn1 = view.findViewById<Button>(R.id.button1)
        val colorBtn2 = view.findViewById<Button>(R.id.button2)
        val colorBtn3 = view.findViewById<Button>(R.id.button3)
        val colorBtn4 = view.findViewById<Button>(R.id.button4)
        val colorBtn5 = view.findViewById<Button>(R.id.button5)
        val colorBtn6 = view.findViewById<Button>(R.id.button6)
        val colorBtn7 = view.findViewById<Button>(R.id.button7)
        val colorBtn8 = view.findViewById<Button>(R.id.button8)
        val colorBtn9 = view.findViewById<Button>(R.id.button9)
        val colorBtn10 = view.findViewById<Button>(R.id.button10)
        val colorBtn11 = view.findViewById<Button>(R.id.button11)
        val colorBtn12 = view.findViewById<Button>(R.id.button12)
        val colorBtn13 = view.findViewById<Button>(R.id.button13)
        val colorBtn14 = view.findViewById<Button>(R.id.button14)
        val colorBtn15 = view.findViewById<Button>(R.id.button15)
        val colorBtn16 = view.findViewById<Button>(R.id.button16)

        colorBtn1.setOnClickListener{
            setColor(244, 67, 54)
        }

        colorBtn2.setOnClickListener{
            setColor(233, 30, 99)
        }

        colorBtn3.setOnClickListener{
            setColor(156, 39, 176)
        }

        colorBtn4.setOnClickListener{
            setColor(103, 58, 183)
        }

        colorBtn5.setOnClickListener{
            setColor(63, 81, 181)
        }

        colorBtn6.setOnClickListener{
            setColor(33, 150, 243)
        }

        colorBtn7.setOnClickListener{
            setColor(3, 169, 244)
        }

        colorBtn8.setOnClickListener{
            setColor(0, 188, 121)
        }

        colorBtn9.setOnClickListener{
            setColor(0, 150, 136)
        }

        colorBtn10.setOnClickListener{
            setColor(73, 175, 80)
        }

        colorBtn11.setOnClickListener{
            setColor(139, 195, 74)
        }

        colorBtn12.setOnClickListener{
            setColor(205, 220, 57)
        }

        colorBtn13.setOnClickListener{
            setColor(255, 235, 59)
        }

        colorBtn14.setOnClickListener{
            setColor(255, 193, 7)
        }

        colorBtn15.setOnClickListener{
            setColor(255, 152, 0)
        }

        colorBtn16.setOnClickListener{
            setColor(255, 87, 34)
        }

        val moreColorsBtn = view.findViewById<Button>(R.id.moreColorsBtn)
        moreColorsBtn.setOnClickListener{
            val Intent = Intent(activity, ColorPickerActivity::class.java)
            startActivity(Intent)
        }
    }
}