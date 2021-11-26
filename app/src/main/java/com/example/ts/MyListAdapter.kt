package com.example.ts

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ts.db.LibraryData

class MyListAdapter (private val context: Context, private val arrayList: List<LibraryData>) : BaseAdapter() {


    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): LibraryData {
        return arrayList[position]
    }
    override fun getItemId(position: Int): Long {
        return arrayList[position].id.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val rowView = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false)

        val scaleUp = AnimationUtils.loadAnimation(context, R.anim.list_anim)
        rowView.startAnimation(scaleUp)

        //val rowView = convertView.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.txtTitle) as TextView
        //val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.txtSubtitle) as TextView
        val dateText = rowView.findViewById(R.id.txtDate) as TextView
        //val hourText = rowView.findViewById(R.id.txtHour) as TextView

        titleText.text = arrayList[position].title
        subtitleText.text = arrayList[position].subtitle
        dateText.text = arrayList[position].date
        //hourText.text = arrayList[position].date?.split(" ")?.get(1) ?: arrayList[position].date

        return rowView
    }
}