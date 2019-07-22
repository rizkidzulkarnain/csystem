package co.ejjv.ccms_mobile.module.menu_register_pl.list_tag

import android.support.v7.widget.SearchView
import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.gson.Tag
import co.ejjv.ccms_mobile.model.response.main._MainResp

interface ListTagContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun hideRefresh()
        fun showAlertDialog(imsg: String, itipe: Int)
        fun showToast(imsg: String?)

        fun goToRegisterPLActivity(tag : String)
        fun setListTag(listTag : ArrayList<Tag>)
        fun refreshAdapter()
        fun getTagAdapter(): ListTagAdapter
        fun setPagination(page : Int, totalData : Int)
    }

    interface Presenter {
        fun getListTag()
        fun onRefreshSource()
        fun onFilterDataSource(searchView: SearchView)
        fun onNextPage()
        fun onPrevPage()
    }

    interface Model {
        interface OnFinishedListener {
            fun onSuccess(mainResp: _MainResp<ArrayList<Tag>>)
            fun onFailure(t: Throwable)
        }
        fun getListTagAll(onFinishedListener : OnFinishedListener, paramMain : ParamMain)
    }
}