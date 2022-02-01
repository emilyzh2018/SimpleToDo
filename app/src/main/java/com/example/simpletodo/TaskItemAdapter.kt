package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter(val listOfItems: List<String>,
                      val longClickListener: OnlongClickListener) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {
    interface OnlongClickListener {
        fun onItemLongClicked(position: Int)
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // Get the data model based on position
//        val contact: Contact = mContacts.get(position)
//        // Set item views based on your views and data model
//        val textView = viewHolder.nameTextView
//        textView.setText(contact.name)
//        val button = viewHolder.messageButton
//        button.text = if (contact.isOnline) "Message" else "Offline"
//        button.isEnabled = contact.isOnline
        //get the data model based on position
        val item = listOfItems.get(position)
        holder.textView.text = item
    }

    override fun getItemCount(): Int {

        return listOfItems.size
    }
    //provide direct ref to each of the views within a data item
    // used to cache the views within the item layout for fast access

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        //stores refs to items in our layout view
        val textView: TextView

        init {
            textView= itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }
}