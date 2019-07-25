package co.ejjv.ccms_mobile.module.menu_close_pl.list_tag_close

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ejjv.ccms_mobile.R
import co.ejjv.ccms_mobile.model.response.gson.PL
import kotlinx.android.synthetic.main.item_tag.view.*

class ListTagCloseAdapter() : RecyclerView.Adapter<ListTagCloseAdapter.myViewHolder>() {
    private lateinit var mContext: Context
    lateinit var mPunchList: List<PL>
    lateinit var mPunchList_selected: List<PL>
    private lateinit var mTagView : ListTagCloseContract.View

    constructor(context: Context, listTag: List<PL>, listTagSelect: List<PL>, TagView : ListTagCloseContract.View) : this() {
        this.mPunchList = listTag
        this.mPunchList_selected = listTagSelect
        this.mContext = context
        this.mTagView = TagView
    }

    override fun onCreateViewHolder(iparent: ViewGroup, iviewType: Int): myViewHolder {
        val view: View = LayoutInflater.from(iparent.context).inflate(R.layout.item_tag, iparent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val tag = mPunchList[position]

        holder.tvNoTag.text = tag.getPunchNo()
        holder.tvDescTag.text = tag.getDetails()
        /* holder.cvTag.setOnClickListener {
            mTagView.goToListTagDetActivity(tag.getID()!!)
        }*/

        if (mPunchList_selected.contains(mPunchList[position])) {
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

    override fun getItemCount() = mPunchList.size
}