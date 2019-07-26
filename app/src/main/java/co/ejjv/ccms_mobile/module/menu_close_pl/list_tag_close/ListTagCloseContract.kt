package co.ejjv.ccms_mobile.module.menu_close_pl.list_tag_close

import android.support.v7.widget.SearchView
import co.ejjv.ccms_mobile.model.param.ParamClosePL
import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.gson.Tag
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.util._MainContract

interface ListTagCloseContract {
    interface View : _MainContract.MainView {
        fun showLoading()
        fun hideLoading()
        fun hideRefresh()
        fun showAlertDialog(imsg: String, itipe: Int)
        fun showToast(imsg: String?)

        //fun goToRegisterPLActivity(tag : String)
        fun setListTag(listTag : ArrayList<PL>)
        fun refreshAdapter()
        fun getTagAdapter(): ListTagCloseAdapter
        fun setPagination(page : Int, totalData : Int)
        fun goToMenuClosePLActivity()
        fun showAlertDialogWithOptions(imsg : String, tag : String)
    }

    interface Presenter {
        fun getListTag(qrcode : String)
        fun saveListClosePL(listSelectList : List<PL>)
        fun onRefreshSource()
        fun onFilterDataSource(searchView: SearchView)
        fun onNextPage()
        fun onPrevPage()
    }

    interface Model {
        interface OnFinishedListener {
            fun onSuccess(mainResp: _MainResp<ArrayList<PL>>, tag : String)
            fun onSuccessSave(mainResp: _MainResp<Int>)
            fun onFailure(t: Throwable)
        }
        fun getListTagAll(onFinishedListener : OnFinishedListener, paramMain : ParamMain)
        fun saveClosePL(onFinishedListener : OnFinishedListener, listClosePL : List<ParamClosePL>)
    }
}