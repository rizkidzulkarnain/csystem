package co.ejjv.ccms_mobile.module.login

import co.ejjv.ccms_mobile.model.response.gson.User
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.util.StaticHelper
import org.json.JSONObject

class LoginImpl : LoginContract.Presenter,  LoginContract.Model.OnFinishedListener{
    var mLoginView : LoginContract.View
    var mLoginDataInteractor : LoginContract.Model

    constructor(loginView : LoginContract.View, loginDataInteractor: LoginContract.Model){
        this.mLoginView = loginView
        this.mLoginDataInteractor = loginDataInteractor
    }

    override fun onClickButton(username: String, password: String) {
        mLoginView.showLoading()

        var jsonbj = JSONObject()
        jsonbj.put("username", username)
        jsonbj.put("password", password)

        mLoginDataInteractor.getLogin(this, jsonbj)
    }

    override fun onSuccess(mainResp: _MainResp<User>) {
        StaticHelper.USER = mainResp.data
        mLoginView.goProjectListActivity()
        mLoginView.hideLoading()
    }

    override fun onFailure(t: Throwable) {
        mLoginView.showToast(t.message.toString())
        mLoginView.hideLoading()
    }
}