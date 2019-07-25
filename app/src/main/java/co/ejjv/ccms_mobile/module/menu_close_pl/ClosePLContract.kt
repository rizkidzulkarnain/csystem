package co.ejjv.ccms_mobile.module.menu_close_pl

import android.content.Context

interface ClosePLContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String?)
        fun showScanner(tag : String)
        fun getContext(): Context
        fun goToMenuActivity()
    }

    interface Presenter {
        fun setScanner(tag : String)
    }
}