package co.ejjv.ccms_mobile.module.menu

import android.content.Context
import co.ejjv.ccms_mobile.model.response.main.Menu
import com.ejjv.ccms_mobile.R


class MenuImpl : MenuContract.Presenter{
    var mMenuView : MenuContract.View
    var mContext : Context

    constructor(menuView : MenuContract.View, context : Context){
        this.mMenuView = menuView
        this.mContext = context
    }

    override fun prepareMenu(menuList: ArrayList<Menu>) {
        val menus = intArrayOf(R.drawable.ic_menu_barcode, R.drawable.ic_list, R.drawable.ic_build, R.drawable.ic_build)

        var a = Menu(1, mContext.getResources().getString(R.string.menu1), menus[0])
        menuList.add(a)

        a = Menu(2, mContext.getResources().getString(R.string.menu2), menus[1])
        menuList.add(a)

        a = Menu(3, mContext.getResources().getString(R.string.menu3), menus[2])
        menuList.add(a)

        a = Menu(4, mContext.getResources().getString(R.string.menu4), menus[3])
        menuList.add(a)

        /*a = Menu(5, mContext.getResources().getString(R.string.menu4), menus[3])
        menuList.add(a)

        a = Menu(6, mContext.getResources().getString(R.string.menu4), menus[3])
        menuList.add(a)

        a = Menu(7, mContext.getResources().getString(R.string.menu4), menus[3])
        menuList.add(a)

        a = Menu(8, mContext.getResources().getString(R.string.menu4), menus[3])
        menuList.add(a)

        a = Menu(9, mContext.getResources().getString(R.string.menu4), menus[3])
        menuList.add(a)

        a = Menu(
            10,
            mContext.getResources().getString(R.string.menu4),
            menus[3]
        )*/
        menuList.add(a)

        mMenuView.refreshAdapter()
    }
}