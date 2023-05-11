package layout

import android.content.Context
import com.example.mp.R
import com.example.mp.USER


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(var context:Context, var data:ArrayList<USER>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtName:TextView
        var mTxtEmail:TextView
        var mTxtAge:TextView
        init {
            this.mTxtName = row?.findViewById(R.id.mTvName) as TextView
            this.mTxtEmail = row?.findViewById(R.id.mTvEmail) as TextView
            this.mTxtAge = row?.findViewById(R.id.mTvAge) as TextView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.myitem_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:USER = getItem(position) as USER
        viewHolder.mTxtName.text = item.names
        viewHolder.mTxtEmail.text = item.email
        viewHolder.mTxtAge.text = item.age
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}