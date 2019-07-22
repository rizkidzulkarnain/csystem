package co.ejjv.ccms_mobile.module.menu_list_pl.listpldet

import co.ejjv.ccms_mobile.model.response.gson.ListPLImage
import co.ejjv.ccms_mobile.model.response.gson.PLDet
import co.ejjv.ccms_mobile.model.response.main._MainResp

interface ListPLDetContract {
    interface View {
        fun showLoading()
        fun hideLoading()

        fun setListPLDet(listPLDet : ArrayList<HashMap<String,String>>, listPLDetImage: ArrayList<ListPLImage>)
    }

    interface Presenter {
        fun getListPLDet(id : Int)
    }

    interface Model {
        interface OnFinishedListener {
            fun onSuccess(mainResp: _MainResp<PLDet>)
            fun onFailure(t: Throwable)
        }
        fun getListPLDet(onFinishedListener : OnFinishedListener, id : Int)
    }
}