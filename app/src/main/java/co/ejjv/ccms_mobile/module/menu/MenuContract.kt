package co.ejjv.ccms_mobile.module.menu

import co.ejjv.ccms_mobile.model.response.main.Menu

interface MenuContract {
    interface View {
        fun refreshAdapter()
    }

    interface Presenter {
        fun prepareMenu(menuList: ArrayList<Menu>)
    }

    interface Model {

    }
}