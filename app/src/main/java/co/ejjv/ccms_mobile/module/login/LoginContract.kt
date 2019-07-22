package co.ejjv.ccms_mobile.module.login

import co.ejjv.ccms_mobile.model.response.gson.User
import co.ejjv.ccms_mobile.model.response.main._MainResp
import org.json.JSONObject

interface LoginContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showAlertDialog(imsg: String, itipe: Int)
        fun showToast(imsg: String?)
        fun goProjectListActivity()
    }

    interface Presenter {
        fun onClickButton(username : String, password : String)
    }

    interface Model {
        interface OnFinishedListener {
            fun onSuccess(mainResp: _MainResp<User>)
            fun onFailure(t: Throwable)
        }
        fun getLogin(onFinishedListener : OnFinishedListener, jsonObject : JSONObject)
    }
}