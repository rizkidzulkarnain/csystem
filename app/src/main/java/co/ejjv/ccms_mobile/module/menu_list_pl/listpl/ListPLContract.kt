package co.ejjv.ccms_mobile.module.menu_list_pl.listpl

import android.support.v7.widget.SearchView
import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.main._MainResp

interface ListPLContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun hideRefresh()
        fun showAlertDialog(imsg: String, itipe: Int)
        fun showToast(imsg: String?)

        fun goToListPLDetActivity(id : Int)
        fun setListPL(listPL : ArrayList<PL>)
        fun refreshAdapter()
        fun getPLAdapter(): ListPLAdapter
        fun setPagination(page : Int, totalData : Int)
    }

    interface Presenter {
        fun getListPL()
        fun onRefreshSource()
        fun onFilterDataSource(searchView: SearchView)
        fun onNextPage()
        fun onPrevPage()
    }

    interface Model {
        interface OnFinishedListener {
            fun onSuccess(mainResp: _MainResp<ArrayList<PL>>)
            fun onFailure(t: Throwable)
        }
        fun getListPLAll(onFinishedListener : OnFinishedListener, paramMain : ParamMain)
    }
}