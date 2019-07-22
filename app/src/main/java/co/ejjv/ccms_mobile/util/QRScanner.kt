package co.ejjv.ccms_mobile.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import me.dm7.barcodescanner.zbar.BarcodeFormat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView



class QRScanner : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private var mScannerView: ZBarScannerView? = null
    var _TAG: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //parameter dari activity yang memanggil scanner
        //dibuat ini karena kemungkinan dalam satu fragment terdapat 2 scanner result
        _TAG = intent.getStringExtra("tag")
        setupPermission()

        mScannerView = ZBarScannerView(this)

        val listFormat = ArrayList<BarcodeFormat>()
        listFormat.add(BarcodeFormat.QRCODE)//set only 2D/QR CODE FORMAT ONLYE
        listFormat.add(BarcodeFormat.CODE39)
        mScannerView!!.setFormats(listFormat)
        mScannerView!!.setAutoFocus(true)

        setContentView(mScannerView)
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        val aintent = Intent()
        val acode = rawResult!!.contents
        aintent.putExtra("tag", _TAG)
        aintent.putExtra("code", acode)
        setResult(Activity.RESULT_OK, aintent)
        finish()
    }

    fun setupPermission() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf("android.permission.CAMERA"), 50)
        }
    }
}