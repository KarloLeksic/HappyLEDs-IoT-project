package com.example.happyleds

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.nio.charset.Charset

class EffectsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var effectList: ArrayList<Effect>
    private lateinit var effectAdapter: EffectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_effects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        effectList = ArrayList()

        effectList.add(Effect(R.drawable.ic_brightness24, "Rainbow"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Party"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Glitters"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Fade"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 5"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 6"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 7"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 8"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 9"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 10"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 11"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 12"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 13"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 14"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 15"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 16"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 17"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 18"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 19"))
        effectList.add(Effect(R.drawable.ic_brightness24, "Efekt 20"))

        effectAdapter = EffectAdapter(effectList)
        recyclerView.adapter = effectAdapter

        effectAdapter.onItemClick = {



            val intent = Intent(activity, EffectDetailActivity::class.java)
            intent.putExtra("effect", it)
            startActivity(intent)
        }

    }
}