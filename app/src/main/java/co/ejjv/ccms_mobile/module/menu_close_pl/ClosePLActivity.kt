package co.ejjv.ccms_mobile.module.menu_close_pl

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import co.ejjv.ccms_mobile.util.LoadingDialog
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.activity_register_pl.*
import android.content.Intent
import co.ejjv.ccms_mobile.module.menu_register_pl.list_tag.ListTagActivity
import co.ejjv.ccms_mobile.util.QRScanner
import android.content.Context
import android.widget.Toast
import co.ejjv.ccms_mobile.module.menu.MenuActivity
import co.ejjv.ccms_mobile.module.menu_close_pl.list_tag_close.ListTagCloseActivity

class ClosePLActivity : AppCompatActivity(), ClosePLContract.View {
    lateinit var mClosePLImpl: ClosePLContract.Presenter
    lateinit var mLoading: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_close_pl)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mClosePLImpl = ClosePLImpl(this)
        mLoading = LoadingDialog.getInstance(this)

        ivScan.setOnClickListener() {
            mClosePLImpl.setScanner("scan_tag")
        }

        ivListTag.setOnClickListener() {
            startActivity(Intent(this, ListTagCloseActivity::class.java))
        }
    }

    override fun showLoading() {
        mLoading.show()
    }

    override fun hideLoading() {
        mLoading.hide()
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getContext(): Context {
        return this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
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
                        }
                    }
                }
        }
    }
}