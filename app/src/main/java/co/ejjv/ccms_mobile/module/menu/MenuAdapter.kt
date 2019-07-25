package co.ejjv.ccms_mobile.module.menu

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.ejjv.ccms_mobile.model.response.main.Menu
import com.bumptech.glide.Glide
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.item_menu.view.*
import android.app.Activity
import android.content.Intent
import co.ejjv.ccms_mobile.module.menu_close_pl.ClosePLActivity
import co.ejjv.ccms_mobile.module.menu_list_pl.listpl.ListPLActivity
import co.ejjv.ccms_mobile.module.menu_register_pl.RegisterPLActivity


class MenuAdapter() : RecyclerView.Adapter<MenuAdapter.myViewHolder>() {
    private lateinit var mContext: Context
    private lateinit var mMenuList: List<Menu>

    constructor(context: Context, menuList: List<Menu>) : this() {
        this.mMenuList = menuList
        this.mContext = context
    }

    override fun onCreateViewHolder(iparent: ViewGroup, iviewType: Int): MenuAdapter.myViewHolder {
        val view: View = LayoutInflater.from(iparent.context).inflate(R.layout.item_menu, iparent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, iposition: Int) {
        val menu = mMenuList[iposition]

        val id = menu.id
        Glide.with(mContext).load(menu.iconMenu).into(holder.ivIcon);
        holder.tvLabel.text = menu.title

        holder.ivIcon.setOnClickListener(View.OnClickListener { v ->
            val classActivity = getMenuActivity(id)
            val aintent = Intent(v.context, classActivity)
            (v.context as Activity).startActivityForResult(aintent, 1)
        })
        holder.cvMenu.setOnClickListener(View.OnClickListener { v ->
            val classActivity = getMenuActivity(id)
            val aintent = Intent(v.context, classActivity)
            (v.context as Activity).startActivityForResult(aintent, 1)
        })
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ivIcon: ImageView = itemView.ivIcon
        internal var tvLabel: TextView = itemView.tvLabel
        internal var cvMenu = itemView.cvMenu
    }

    override fun getItemCount() = mMenuList.size

    private fun getMenuActivity(id: Int): Class<*>? {
        var aclass: Class<*>? = null
        when (id) {
            1 -> aclass = RegisterPLActivity::class.java
            2 -> aclass = ListPLActivity::class.java
            3 -> aclass = ClosePLActivity::class.java
            4 -> aclass = MenuActivity::class.java
            5 -> aclass = MenuActivity::class.java
            6 -> aclass = MenuActivity::class.java
            7 -> aclass = MenuActivity::class.java
            8 -> aclass = MenuActivity::class.java
            9 -> aclass = MenuActivity::class.java
            10 -> aclass = MenuActivity::class.java
        }
        return aclass
    }
}