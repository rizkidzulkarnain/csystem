package co.ejjv.ccms_mobile.module.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import co.ejjv.ccms_mobile.module.menu.MenuActivity
import co.ejjv.ccms_mobile.module.projects.ProjectsActivity
import co.ejjv.ccms_mobile.util.LoadingDialog
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View{
    lateinit var mLoginImpl :LoginContract.Presenter
    lateinit var mLoading : LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginImpl = LoginImpl(this, LoginDataInteractor())
        mLoading  = LoadingDialog.getInstance(this)

        btn_login.setOnClickListener{
            var username = txt_username.text.toString()
            var password = txt_password.text.toString()

            mLoginImpl.onClickButton("arinobo", "3ch0P@ssw0rd")
            //mLoginImpl.onClickButton(username, password)
        }
    }

    override fun showLoading() {
        mLoading.show()
    }

    override fun hideLoading() {
        mLoading.hide()
    }

    override fun showAlertDialog(imsg: String, itipe: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun goProjectListActivity() {
        val intent = Intent(applicationContext, ProjectsActivity::class.java)
        startActivity(intent)
    }
}