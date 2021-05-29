package com.jarvis.tab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.tab.model.ListItemLocal

class LokalAdapter(var context: Context, val  mListItem :  MutableList<ListItemLocal>) : RecyclerView.Adapter<LokalAdapter.ViewHolder>() {
    lateinit var view : View
    lateinit var localItem : ListItemLocal

    private var mOnItemClickListener : OnItemCLickListener? = null

    interface OnItemCLickListener{
        fun onItemSelected(localItem : ListItemLocal)
    }



    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder {

        context = vg.context
        view = LayoutInflater.from(context).inflate(R.layout.lokal_item_list, vg , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int= mListItem.size

    internal  fun addItemListLocal(item : List<ListItemLocal>){
        this.mListItem.clear()
        this.mListItem.addAll(item)
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    internal fun setOnItemClickListener(listener : OnItemCLickListener){
        mOnItemClickListener = listener
    }


   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val  mTitle : TextView? = itemView.findViewById(R.id.tv_name)
        val  mOrigin : TextView? = itemView.findViewById(R.id.tv_origin)
        val  mDesc : TextView? = itemView.findViewById(R.id.tv_desc)
        val  mImage : ImageView? = itemView.findViewById(R.id.img)


        fun onBind(position: Int) {
            val (imgResource,
             nameItem,
             originLocation,
             descItem) =  mListItem[position]


            inflateData(
                imgResource,
                nameItem,
                originLocation,
                descItem
            )
        }

       private fun inflateData(imgResource: Int, nameItem: String, originLocation: String, descItem: String) {
           if (mTitle != null) {
               mTitle.text = nameItem
           }
           itemView.setOnClickListener(this)
       }


       override fun onClick(p0: View?) {
           mOnItemClickListener?.onItemSelected( mListItem[absoluteAdapterPosition])
        }


    }


}