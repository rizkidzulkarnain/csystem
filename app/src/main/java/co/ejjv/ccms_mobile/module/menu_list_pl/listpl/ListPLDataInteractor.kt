package co.ejjv.ccms_mobile.module.menu_list_pl.listpl

import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.network.NetworkGenerator
import co.ejjv.ccms_mobile.network.NetworkService
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class ListPLDataInteractor : ListPLContract.Model {
    override fun getListPLAll(onFinishedListener : ListPLContract.Model.OnFinishedListener, paramMain: ParamMain) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        val call = networkService.getPLAll(paramMain)

        call.enqueue(object : Callback<_MainResp<ArrayList<PL>>> {
            override fun onResponse(call: Call<_MainResp<ArrayList<PL>>>, response: Response<_MainResp<ArrayList<PL>>>) {
                onFinishedListener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<_MainResp<ArrayList<PL>>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}