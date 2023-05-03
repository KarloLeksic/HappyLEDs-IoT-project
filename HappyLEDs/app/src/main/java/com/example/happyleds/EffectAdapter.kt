package com.example.happyleds

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EffectAdapter(private val effectList:ArrayList<Effect>)
    : RecyclerView.Adapter<EffectAdapter.EffectViewHolder>(){

    var onItemClick : ((Effect) -> Unit)? = null

    class EffectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val textView : TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EffectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return EffectViewHolder(view)
    }

    override fun onBindViewHolder(holder: EffectViewHolder, position: Int) {
        val effect = effectList[position]
        holder.imageView.setImageResource(effect.image)
        holder.textView.text = effect.text

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(effect)
        }
    }

    override fun getItemCount(): Int {
        return effectList.size
    }
}