package com.mahostudios.ntc

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TableAdapter(private var context: Context, private var timeList : List<String>, private var nameList : List<String>, private var speedList : List<String>) :
    RecyclerView.Adapter<TableAdapter.ViewHolder>()  {

    inner class ViewHolder(item : View):RecyclerView.ViewHolder(item){

        val itemname : TextView = itemView.findViewById(R.id.c_name)
        val itemspeed : TextView = itemView.findViewById(R.id.c_speed)
        val itemtime : TextView = itemView.findViewById(R.id.c_time)

        init {
            itemView.setOnClickListener{ v : View ->
                val pos : Int = adapterPosition
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.itemlist_layout, parent, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return 23
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemname.text = nameList.get(position)
        holder.itemspeed.text = speedList.get(position)
        holder.itemtime.text = timeList.get(position)
    }
}