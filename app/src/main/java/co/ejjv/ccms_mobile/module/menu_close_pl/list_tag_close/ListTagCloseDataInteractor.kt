package co.ejjv.ccms_mobile.module.menu_close_pl.list_tag_close

import co.ejjv.ccms_mobile.model.param.ParamClosePL
import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.gson.Tag
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.network.NetworkGenerator
import co.ejjv.ccms_mobile.network.NetworkService
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class ListTagCloseDataInteractor : ListTagCloseContract.Model {
    override fun getListTagAll(onFinishedListener : ListTagCloseContract.Model.OnFinishedListener, paramMain: ParamMain) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        val call = networkService.getPLAll(paramMain)

        call.enqueue(object : Callback<_MainResp<ArrayList<PL>>> {
            override fun onResponse(call: Call<_MainResp<ArrayList<PL>>>, response: Response<_MainResp<ArrayList<PL>>>) {
                onFinishedListener.onSuccess(response.body()!!, "get_close_pl")
            }

            override fun onFailure(call: Call<_MainResp<ArrayList<PL>>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }

    override fun saveClosePL(onFinishedListener : ListTagCloseContract.Model.OnFinishedListener, listClosePL: List<ParamClosePL>) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        val call = networkService.saveClosePL(listClosePL)

        call.enqueue(object : Callback<_MainResp<Int>> {
            override fun onResponse(call: Call<_MainResp<Int>>, response: Response<_MainResp<Int>>) {
                onFinishedListener.onSuccessSave(response.body()!!)
            }

            override fun onFailure(call: Call<_MainResp<Int>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}