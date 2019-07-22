package co.ejjv.ccms_mobile.module.menu_register_pl

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.item_file.view.*

class RegisterPLFileAdapter() : RecyclerView.Adapter<RegisterPLFileAdapter.myViewHolder>() {
    private lateinit var mContext: Context
    lateinit var mListFile: List<String>

    constructor(context: Context, listTag: List<String>) : this() {
        this.mListFile = listTag
        this.mContext = context
    }

    override fun onCreateViewHolder(iparent: ViewGroup, iviewType: Int): myViewHolder {
        val view: View = LayoutInflater.from(iparent.context).inflate(R.layout.item_file, iparent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val file = mListFile[position]

        holder.tvFilePath.text = file
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvFilePath: TextView = itemView.tvFilePath
    }

    override fun getItemCount() = mListFile.size
}