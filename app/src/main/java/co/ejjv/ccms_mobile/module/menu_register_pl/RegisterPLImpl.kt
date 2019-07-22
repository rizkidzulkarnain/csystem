package co.ejjv.ccms_mobile.module.menu_register_pl

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import co.ejjv.ccms_mobile.model.param.ParamSaveRegisterPL
import co.ejjv.ccms_mobile.model.response.gson.MasterDiscCat
import co.ejjv.ccms_mobile.model.response.main._MainResp
import com.asksira.bsimagepicker.BSImagePicker
import com.ejjv.ccms_mobile.R
import com.example.ParamMaster
import com.vincent.filepicker.Constant
import com.vincent.filepicker.activity.BaseActivity
import com.vincent.filepicker.activity.NormalFilePickActivity
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import okhttp3.RequestBody
import okhttp3.MultipartBody
import com.google.gson.Gson
import okhttp3.MediaType
import java.io.File


class RegisterPLImpl : RegisterPLContract.Presenter,
    RegisterPLContract.Model.OnFinishedListener {
    var mRegisterPLView: RegisterPLContract.View
    var mRegisterPLDataInteractor: RegisterPLContract.Model
    var mCalendar: Calendar
    var mSpinnerKeyCategory = HashMap<Int, Int>()
    var mSpinnerKeyDiscipline = HashMap<Int, Int>()

    constructor(listPLView: RegisterPLContract.View, listPLDataInteractor: RegisterPLContract.Model) {
        this.mRegisterPLView = listPLView
        this.mRegisterPLDataInteractor = listPLDataInteractor
        mCalendar = Calendar.getInstance()
    }

    override fun getMasterDiscCat() {
        var paramMaster = ParamMaster("")
        mRegisterPLDataInteractor.getCatDisc(this, paramMaster)
    }

    inline override fun onSuccess(mainResp: _MainResp<*>, tag: String) {
        if (tag.toLowerCase() == "getCatDisc".toLowerCase()) {
            var listMaster = mainResp as _MainResp<MasterDiscCat>

            /*Category*/
            var spinnerArrayListCategory = ArrayList<String?>()
            for (i in 0 until listMaster.data!!.masterCategory!!.size) {
                mSpinnerKeyCategory[i] = listMaster.data!!.masterCategory!![i].getID()!!
                spinnerArrayListCategory.add(listMaster.data!!.masterCategory!![i].getCode()!!)
            }
            mRegisterPLView.setCategory(spinnerArrayListCategory)

            /*Discipline*/
            var spinnerArrayListDiscipline = ArrayList<String?>()
            for (i in 0 until listMaster.data!!.masterDiscipline!!.size) {
                mSpinnerKeyDiscipline[i] = listMaster.data!!.masterDiscipline!![i].iD!!
                spinnerArrayListDiscipline.add(listMaster.data!!.masterDiscipline!![i].disciplineCode!!)
            }
            mRegisterPLView.setDiscipline(spinnerArrayListDiscipline)

        } else if (tag.toLowerCase() == "saveRegisterPL".toLowerCase()) {
            if (mainResp.message == "") {
                var response = mainResp as _MainResp<Int>
                mRegisterPLView.showAlertDialogWithOptions(
                    "[Success]Punch list number : " + response.data + " Created !",
                    "savepl"
                ) /**/
            } else {
                mRegisterPLView.showToast(mainResp.message)
            }
        }
        mRegisterPLView.hideLoading()
    }

    override fun saveAllData(param: ParamSaveRegisterPL, listFile: ArrayList<String>) {
        mRegisterPLView.showLoading()

        var jsonString: String = Gson().toJson(param)
        var jsonData = RequestBody.create(MediaType.parse("application/json"), jsonString)

        var listParamFile = ArrayList<MultipartBody.Part>()
        if (listFile.size > 0) {
            for (path in listFile) {
                val file = File(path)

                val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)

                var fileItem = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody)
                listParamFile.add(fileItem)
            }
        }

        mRegisterPLDataInteractor.saveRegisterPL(this, jsonData, listParamFile)
    }

    override fun onFailure(t: Throwable) {
        mRegisterPLView.showToast(t.message.toString())
        mRegisterPLView.hideLoading()
    }

    override fun setDatePicker(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            mCalendar.set(Calendar.YEAR, year)
            mCalendar.set(Calendar.MONTH, monthOfYear)
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd MMMM yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            mRegisterPLView.setTxtDate(sdf.format(mCalendar.getTime()))
        }
    }

    override fun setDatePickerDialog() {
        mRegisterPLView.showDatePicker(mCalendar)
    }

    override fun setScanner(tag: String) {
        mRegisterPLView.showScanner(tag)
    }

    override fun setGallery(): BSImagePicker {
        return BSImagePicker.Builder("com.yourdomain.yourpackage.fileprovider")
            .isMultiSelect
            .setMaximumMultiSelectCount(6)
            .setMultiSelectBarBgColor(android.R.color.white)
            .setMultiSelectTextColor(R.color.primary_text)
            .setMultiSelectDoneTextColor(R.color.colorAccent)
            .setOverSelectTextColor(R.color.error_text)
            .disableOverSelectionMessage()
            .build()
    }

    override fun setFilePicker(): Intent {
        val intent4 = Intent(mRegisterPLView.getContext(), NormalFilePickActivity::class.java)
        intent4.putExtra(Constant.MAX_NUMBER, 9)
        intent4.putExtra(BaseActivity.IS_NEED_FOLDER_LIST, true)
        intent4.putExtra(
            NormalFilePickActivity.SUFFIX,
            arrayOf("xlsx", "xls", "doc", "dOcX", "ppt", ".pptx", "pdf")
        )
        return intent4
    }

    override fun setPermissionCamera() {
        if (ContextCompat.checkSelfPermission(
                mRegisterPLView.getContext(),
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                mRegisterPLView.getContext() as Activity,
                arrayOf("android.permission.CAMERA"),
                50
            )
        }
    }

    override fun getImageUri(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null)
        return Uri.parse(path)
    }

    override fun getRealPathFromURI(uri: Uri): String {
        var path = ""
        val contextResolver = mRegisterPLView.getContext().contentResolver
        if (contextResolver != null) {
            val cursor = contextResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }

    override fun getDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy")
        val date = Date()
        return dateFormat.format(date)
    }
}