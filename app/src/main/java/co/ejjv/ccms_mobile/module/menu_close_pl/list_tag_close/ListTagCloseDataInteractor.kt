package co.ejjv.ccms_mobile.module.menu_register_pl.list_tag

import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.response.gson.Tag
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.network.NetworkGenerator
import co.ejjv.ccms_mobile.network.NetworkService
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class ListTagDataInteractor : ListTagContract.Model {
    override fun getListTagAll(onFinishedListener : ListTagContract.Model.OnFinishedListener, paramMain: ParamMain) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        val call = networkService.getTagAll(paramMain)

        call.enqueue(object : Callback<_MainResp<ArrayList<Tag>>> {
            override fun onResponse(call: Call<_MainResp<ArrayList<Tag>>>, response: Response<_MainResp<ArrayList<Tag>>>) {
                onFinishedListener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<_MainResp<ArrayList<Tag>>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}