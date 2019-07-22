package co.ejjv.ccms_mobile.module.projects

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import co.ejjv.ccms_mobile.module.menu.MenuActivity
import co.ejjv.ccms_mobile.util.LoadingDialog
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.activity_list_project.*
import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.widget.Toast

class ProjectsActivity : AppCompatActivity(), ProjectsContract.View {
    lateinit var mLoginImpl: ProjectsContract.Presenter
    lateinit var mLoading: LoadingDialog
    var mSpinnerDialog: SpinnerDialog? = null
    var mPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_project)

        mLoginImpl = ProjectsImpl(this)
        mLoading = LoadingDialog.getInstance(this)

        mLoginImpl.setListProject()

        txtProject.setOnClickListener {
            mSpinnerDialog!!.showSpinerDialog();
        }

        btn_go.setOnClickListener {
            if (!txtProject.text.isNullOrEmpty()) {
                mLoginImpl.onClickButton(mPosition!!)
            } else {
                Toast.makeText(this, "Pilih Project Dulu !", Toast.LENGTH_LONG).show()
            }
        }

        mSpinnerDialog!!.bindOnSpinerListener { item, position ->
            txtProject.setText(item)
            mPosition = position
        }
    }

    override fun setSpinnerListProject(spinnerValue: ArrayList<String?>) {
        mSpinnerDialog =
            SpinnerDialog(this@ProjectsActivity, spinnerValue, "Select or Search Project", "Close")// With No Animation
        mSpinnerDialog!!.setCancellable(true);
        mSpinnerDialog!!.setShowKeyboard(false);
    }

    override fun goMenuActivity() {
        val intent = Intent(applicationContext, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}