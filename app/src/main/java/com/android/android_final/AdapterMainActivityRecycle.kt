package com.android.android_final

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.contact_view.view.*


class AdapterMainActivityRecycle(var persons:List<ContactModel>):RecyclerView.Adapter<AdapterMainActivityRecycle.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = persons[position].name
        holder.group.text = persons[position].id_group



        when(persons[position].profile_image){
            "1" ->  holder.image.setImageResource(R.drawable.category1)
            "2" ->  holder.image.setImageResource(R.drawable.category2)
            "3" ->  holder.image.setImageResource(R.drawable.category3)
        }
    }

    fun getId(position: Int): String {
        return persons[position].id_contact
    }

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(holder.context)
        val view = inflater.inflate(R.layout.contact_view, holder, false)
        return ViewHolder(view)
    }
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.contact_view_name
        var group: TextView = itemView.contact_view_group
        var image: ImageView = itemView.contact_view_image

    }
}