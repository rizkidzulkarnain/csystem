package co.ejjv.ccms_mobile.module.login

import co.ejjv.ccms_mobile.model.response.gson.User
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.network.NetworkGenerator
import co.ejjv.ccms_mobile.network.NetworkService
import com.example.ParamLogin
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class LoginDataInteractor : LoginContract.Model {
    override fun getLogin(onFinishedListener : LoginContract.Model.OnFinishedListener, jsonObject: JSONObject) {
        val networkService = NetworkGenerator.createService(NetworkService::class.java)

        var atestResp = ParamLogin(
            jsonObject.getString("username"),
            jsonObject.getString("password")
        )
        val call = networkService.getLogin(atestResp)

        call.enqueue(object : Callback<_MainResp<User>> {
            override fun onResponse(call: Call<_MainResp<User>>, response: Response<_MainResp<User>>) {
                onFinishedListener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<_MainResp<User>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}