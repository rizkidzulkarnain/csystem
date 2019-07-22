package co.ejjv.ccms_mobile.module.menu_list_pl.listpldet

import co.ejjv.ccms_mobile.model.response.gson.PLDet
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.network.NetworkGenerator
import co.ejjv.ccms_mobile.network.NetworkService
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class ListPLDetDataInteractor : ListPLDetContract.Model {
    override fun getListPLDet(onFinishedListener : ListPLDetContract.Model.OnFinishedListener, id : Int) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        val call = networkService.getPLDet(id)

        call.enqueue(object : Callback<_MainResp<PLDet>> {
            override fun onResponse(call: Call<_MainResp<PLDet>>, response: Response<_MainResp<PLDet>>) {
                onFinishedListener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<_MainResp<PLDet>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}