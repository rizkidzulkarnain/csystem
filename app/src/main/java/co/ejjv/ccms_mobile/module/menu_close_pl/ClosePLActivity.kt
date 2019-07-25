package co.ejjv.ccms_mobile.module.menu_register_pl

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import co.ejjv.ccms_mobile.util.LoadingDialog
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.activity_register_pl.*
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import co.ejjv.ccms_mobile.module.menu_register_pl.list_tag.ListTagActivity
import co.ejjv.ccms_mobile.util.QRScanner
import java.util.*
import kotlin.collections.ArrayList
import com.vincent.filepicker.Constant
import com.vincent.filepicker.Constant.REQUEST_CODE_PICK_FILE
import com.vincent.filepicker.filter.entity.NormalFile
import com.asksira.bsimagepicker.BSImagePicker
import com.bumptech.glide.Glide
import java.io.File
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.Menu
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import co.ejjv.ccms_mobile.model.param.ParamSaveRegisterPL
import co.ejjv.ccms_mobile.module.menu.MenuActivity
import co.ejjv.ccms_mobile.util.AlertDialogCustom
import co.ejjv.ccms_mobile.util.StaticHelper

class RegisterPLActivity : AppCompatActivity(), RegisterPLContract.View, BSImagePicker.OnSingleImageSelectedListener,
    BSImagePicker.OnMultiImageSelectedListener,
    BSImagePicker.ImageLoaderDelegate {
    lateinit var mRegisterPLImpl: RegisterPLContract.Presenter
    lateinit var mLoading: LoadingDialog
    lateinit var mDate: DatePickerDialog.OnDateSetListener
    lateinit var mListFile: ArrayList<String>
    lateinit var mAdapterFile: RegisterPLFileAdapter

    lateinit var mGalleryPicker: BSImagePicker
    lateinit var mFilePicker: Intent
    var mSpinnerDialogCategory: SpinnerDialog? = null
    var mSpinnerDialogDiscipline: SpinnerDialog? = null
    var mPositionCategory: Int? = null
    var mPositionDiscipline: Int? = null

    var mRadio: RadioButton? = null

    val REQUEST_CODE_CAMERA = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pl)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mRegisterPLImpl = RegisterPLImpl(this, RegisterPLDataInteractor())
        mLoading = LoadingDialog.getInstance(this)

        mRegisterPLImpl.getMasterDiscCat()
        mDate = mRegisterPLImpl.setDatePicker()

        ivDate.setOnClickListener() {
            mRegisterPLImpl.setDatePickerDialog()
        }

        ivScan.setOnClickListener() {
            mRegisterPLImpl.setScanner("scan_tag")
        }

        ivListTag.setOnClickListener() {
            startActivityForResult(Intent(this, ListTagActivity::class.java), 1)
        }

        ivGallery.setOnClickListener() {
            mGalleryPicker.show(supportFragmentManager, "picker");
        }

        ivPhoto.setOnClickListener() {
            mRegisterPLImpl.setPermissionCamera()
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE_CAMERA);
        }

        ivFile.setOnClickListener() {
            startActivityForResult(mFilePicker, REQUEST_CODE_PICK_FILE)
        }

        /*setCategory(arrayListOf("test","test2"))
        setDiscipline(arrayListOf("test1","test12"))*/

        txtCategory.setOnClickListener {
            mSpinnerDialogCategory!!.showSpinerDialog();
        }

        txtDiscipline.setOnClickListener {
            mSpinnerDialogDiscipline!!.showSpinerDialog();
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            mRadio = findViewById(checkedId)
        }

        setComponent()
    }

    override fun setCategory(spinnerValue: ArrayList<String?>) {
        mSpinnerDialogCategory = SpinnerDialog(
            this@RegisterPLActivity,
            spinnerValue,
            "Select or Search Category",
            "Close"
        )// With No Animation
        mSpinnerDialogCategory!!.setCancellable(true);
        mSpinnerDialogCategory!!.setShowKeyboard(false);

        mSpinnerDialogCategory!!.bindOnSpinerListener { item, position ->
            txtCategory.setText(item)
            mPositionCategory = position
        }
    }

    override fun setDiscipline(spinnerValue: ArrayList<String?>) {
        mSpinnerDialogDiscipline = SpinnerDialog(
            this@RegisterPLActivity,
            spinnerValue,
            "Select or Search Discipline",
            "Close"
        )// With No Animation
        mSpinnerDialogDiscipline!!.setCancellable(true);
        mSpinnerDialogDiscipline!!.setShowKeyboard(false);

        mSpinnerDialogDiscipline!!.bindOnSpinerListener { item, position ->
            txtDiscipline.setText(item)
            mPositionDiscipline = position
        }
    }

    override fun setTxtDate(stringDate: String) {
        txtDate.setText(stringDate)
    }

    fun setComponent() {

        var selectedId = radioGroup!!.checkedRadioButtonId;
        mRadio = findViewById (selectedId);

        mListFile = ArrayList()
        mAdapterFile = RegisterPLFileAdapter(this, mListFile)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvFile.layoutManager = layoutManager
        rvFile.adapter = mAdapterFile

        mGalleryPicker = mRegisterPLImpl.setGallery()
        mFilePicker = mRegisterPLImpl.setFilePicker()
    }

    override fun showLoading() {
        mLoading.show()
    }

    override fun hideLoading() {
        mLoading.hide()
    }

    override fun showAlertDialogWithOptions(imsg: String, tag: String) {
        var alertDialog = AlertDialogCustom(this, this, tag)
        alertDialog.showAlertDialog(imsg, 3)
    }

    override fun onPostAlertDialogAction(itag: String) {
        if (itag == "savepl") {
            goToMenuActivity()
        } else {
            showToast("No option with your tag inputted !")
        }
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getContext(): Context {
        return this
    }

    override fun showDatePicker(calendar: Calendar) {
        DatePickerDialog(
            this,
            mDate,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_longclick_selection, menu)
        menu.getItem(0).title = "SAVE"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_select -> {
                var param = ParamSaveRegisterPL()
                param.tagReference = tagQR.text.toString()
                param.details = tvDesc.text.toString()
                param.category = txtCategory.text.toString()
                param.phase = mRadio!!.text.toString()
                param.discipline = txtDiscipline.text.toString()
                param.targetDate = txtDate.text.toString()
                param.createdBy = StaticHelper.USER!!.getUserName().toString()
                param.createdOn = mRegisterPLImpl.getDate()
                param.projectID = StaticHelper.PROJECT
                mRegisterPLImpl.saveAllData(param, mListFile)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showScanner(tag: String) {
        val aintent = Intent(this, QRScanner::class.java)
        aintent.putExtra("tag", tag)
        startActivityForResult(aintent, 1)
    }

    override fun goToMenuActivity() {
        val aintent = Intent(this, MenuActivity::class.java)
        startActivity(aintent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 ->
                if (resultCode == Activity.RESULT_OK) {
                    val atag = data!!.getStringExtra("tag")
                    val code = data.getStringExtra("code")
                    when (atag) {
                        "scan_tag" -> {
                            tagQR.text = code
                        }
                    }
                }
            REQUEST_CODE_CAMERA ->
                if (resultCode == RESULT_OK) {
                    if (resultCode == Activity.RESULT_OK) {
                        var photo = data!!.extras!!.get("data") as Bitmap
                        var tempUri: Uri = mRegisterPLImpl.getImageUri(applicationContext, photo);
                        var finalFile = File(mRegisterPLImpl.getRealPathFromURI(tempUri))
                        mListFile.add(finalFile.path)
                        mAdapterFile.notifyDataSetChanged()
                    }
                }
            REQUEST_CODE_PICK_FILE ->
                if (resultCode == RESULT_OK) {
                    val list = data!!.getParcelableArrayListExtra<NormalFile>(Constant.RESULT_PICK_FILE);
                    for (file in list) {
                        mListFile.add(file.path)
                    }
                    mAdapterFile.notifyDataSetChanged()
                }
        }
    }

    override fun onSingleImageSelected(uri: Uri?, tag: String?) {
        mListFile.add(uri!!.path!!)
        mAdapterFile.notifyDataSetChanged()
    }

    override fun onMultiImageSelected(uriList: MutableList<Uri>?, tag: String?) {
        for (file in uriList!!) {
            mListFile.add(file.path!!)
        }
        mAdapterFile.notifyDataSetChanged()
    }

    override fun loadImage(imageFile: File?, ivImage: ImageView?) {
        Glide.with(this@RegisterPLActivity).load(imageFile).into(ivImage!!);
    }

    override fun reloadActivity() {
        finish();
        startActivity(getIntent());
    }
}