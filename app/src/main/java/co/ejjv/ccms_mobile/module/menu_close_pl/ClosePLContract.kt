package co.ejjv.ccms_mobile.module.menu_register_pl

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import co.ejjv.ccms_mobile.model.param.ParamSaveRegisterPL
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.util._MainContract
import com.asksira.bsimagepicker.BSImagePicker
import com.example.ParamMaster
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.*
import kotlin.collections.ArrayList

interface RegisterPLContract {
    interface View : _MainContract.MainView{
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String?)
        fun showDatePicker(calendar: Calendar)
        fun setTxtDate(stringDate: String)
        fun showScanner(tag : String)
        fun getContext(): Context
        fun setCategory(spinnerValue: ArrayList<String?>)
        fun setDiscipline(spinnerValue: ArrayList<String?>)
        fun reloadActivity()
        fun goToMenuActivity()
        fun showAlertDialogWithOptions(imsg : String, tag : String)
    }

    interface Presenter {
        fun setDatePicker(): DatePickerDialog.OnDateSetListener
        fun setDatePickerDialog()
        fun setScanner(tag : String)
        fun setGallery() : BSImagePicker
        fun setPermissionCamera()
        fun setFilePicker() : Intent
        fun getImageUri(context: Context, bitmap: Bitmap): Uri
        fun getRealPathFromURI(uri: Uri): String
        fun getMasterDiscCat()
        fun saveAllData(param : ParamSaveRegisterPL, listFile : ArrayList<String>)
        fun getDate() : String
    }

    interface Model {
        interface OnFinishedListener {
            fun onSuccess(mainResp: _MainResp<*>, tag : String)
            fun onFailure(t: Throwable)
        }

        fun getCatDisc(onFinishedListener: OnFinishedListener, paramMaster: ParamMaster)
        fun saveRegisterPL(onFinishedListener: OnFinishedListener, jsonData: RequestBody, file : ArrayList<MultipartBody.Part>)
    }
}