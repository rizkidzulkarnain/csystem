package co.ejjv.ccms_mobile.module.menu_list_pl.listpl

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ejjv.ccms_mobile.R
import co.ejjv.ccms_mobile.model.response.gson.PL
import kotlinx.android.synthetic.main.item_pl.view.*

class ListPLAdapter() : RecyclerView.Adapter<ListPLAdapter.myViewHolder>() {
    private lateinit var mContext: Context
    private lateinit var mListPL: List<PL>
    private lateinit var mPLView : ListPLContract.View

    constructor(context: Context, listPL: List<PL>, PLView : ListPLContract.View) : this() {
        this.mListPL = listPL
        this.mContext = context
        this.mPLView = PLView
    }

    override fun onCreateViewHolder(iparent: ViewGroup, iviewType: Int): myViewHolder {
        val view: View = LayoutInflater.from(iparent.context).inflate(R.layout.item_pl, iparent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, iposition: Int) {
        val PL = mListPL[iposition]

        holder.tvNoPL.text = PL.getPunchNo()
        holder.tvSubNoPL.text = (PL.getCategory()?:"-") + "/" + (PL.getPhase()?:"-")+ "/" + (PL.getDiscipline()?:"-")
        holder.tvDesc.text = PL.getDetails()
        holder.cvPL.setOnClickListener {
            mPLView.goToListPLDetActivity(PL.getID()!!)
        }
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvNoPL: TextView = itemView.tvNoPL
        internal var tvSubNoPL: TextView = itemView.tvSubNoPL
        internal var tvDesc: TextView = itemView.tvDesc
        internal var cvPL = itemView.cvPL
    }

    override fun getItemCount() = mListPL.size
}