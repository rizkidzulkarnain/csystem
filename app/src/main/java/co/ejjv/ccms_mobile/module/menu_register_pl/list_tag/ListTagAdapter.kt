package co.ejjv.ccms_mobile.module.menu_register_pl.list_tag

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ejjv.ccms_mobile.R
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.gson.Tag
import kotlinx.android.synthetic.main.item_pl.view.*
import kotlinx.android.synthetic.main.item_pl.view.cvPL
import kotlinx.android.synthetic.main.item_tag.view.*

class ListTagAdapter() : RecyclerView.Adapter<ListTagAdapter.myViewHolder>() {
    private lateinit var mContext: Context
    lateinit var mListTag: List<Tag>
    lateinit var mListTag_selected: List<Tag>
    private lateinit var mTagView : ListTagContract.View

    constructor(context: Context, listTag: List<Tag>, listTagSelect: List<Tag>, TagView : ListTagContract.View) : this() {
        this.mListTag = listTag
        this.mListTag_selected = listTagSelect
        this.mContext = context
        this.mTagView = TagView
    }

    override fun onCreateViewHolder(iparent: ViewGroup, iviewType: Int): myViewHolder {
        val view: View = LayoutInflater.from(iparent.context).inflate(R.layout.item_tag, iparent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val tag = mListTag[position]

        holder.tvNoTag.text = tag.getItemKey()
        holder.tvDescTag.text = tag.getItemDesc()
        /* holder.cvTag.setOnClickListener {
            mTagView.goToListTagDetActivity(tag.getID()!!)
        }*/

        if (mListTag_selected.contains(mListTag[position])) {
            holder.cvTag.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_selected_state))
            holder.layoutCheck.visibility = View.VISIBLE
        } else {
            holder.cvTag.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_normal_state))
            holder.layoutCheck.visibility = View.GONE
        }
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvNoTag: TextView = itemView.tvNoTag
        internal var tvDescTag: TextView = itemView.tvDescTag
        internal var cvTag = itemView.cvTag
        internal var layoutCheck = itemView.layoutCheck
    }

    override fun getItemCount() = mListTag.size
}