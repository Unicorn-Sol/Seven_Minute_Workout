package com.sevenminuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusAdapter(val context: Context, val items : ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exercise_status, parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }




    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val tvItem = view.tvItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvItem.text = item.getId().toString()

        if (item.getIsSelected()){
            holder.tvItem.setBackgroundResource(R.drawable.item_circular_thin_color_accent_border)
     }

    }
}