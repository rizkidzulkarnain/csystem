package co.ejjv.ccms_mobile.module.menu_register_pl

import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.param.ParamSaveRegisterPL
import co.ejjv.ccms_mobile.model.response.gson.MasterDiscCat
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.gson.PLDet
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.network.NetworkGenerator
import co.ejjv.ccms_mobile.network.NetworkService
import com.example.ParamMaster
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import java.util.*
import kotlin.collections.ArrayList


class RegisterPLDataInteractor : RegisterPLContract.Model {
    override fun getCatDisc(onFinishedListener : RegisterPLContract.Model.OnFinishedListener, paramMaster: ParamMaster) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        val call = networkService.getMaster(paramMaster)

        call.enqueue(object : Callback<_MainResp<MasterDiscCat>> {
            override fun onResponse(call: Call<_MainResp<MasterDiscCat>>, response: Response<_MainResp<MasterDiscCat>>) {
                onFinishedListener.onSuccess(response.body()!!, "getCatDisc")
            }

            override fun onFailure(call: Call<_MainResp<MasterDiscCat>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }

    override fun saveRegisterPL(onFinishedListener : RegisterPLContract.Model.OnFinishedListener, requestBody: RequestBody, listFile : ArrayList<MultipartBody.Part>) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        val call = networkService.saveRegisterPL(requestBody, listFile)

        call.enqueue(object : Callback<_MainResp<Int>> {
            override fun onResponse(call: Call<_MainResp<Int>>, response: Response<_MainResp<Int>>) {
                onFinishedListener.onSuccess(response.body()!!, "saveRegisterPL")
            }

            override fun onFailure(call: Call<_MainResp<Int>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}