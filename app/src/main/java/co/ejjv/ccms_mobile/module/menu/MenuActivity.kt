package co.ejjv.ccms_mobile.module.menu

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.ejjv.ccms_mobile.R
import co.ejjv.ccms_mobile.model.response.main.Menu
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.widget.Toast
import co.ejjv.ccms_mobile.module.login.LoginActivity
import co.ejjv.ccms_mobile.module.projects.ProjectsActivity
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity(), MenuContract.View{

    private lateinit var mMenuList: List<Menu>
    private lateinit var mMenuImpl : MenuImpl
    private lateinit var mAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mMenuImpl = MenuImpl(this, this)
        setComponent()
        mMenuImpl.prepareMenu(mMenuList as ArrayList<Menu>)
    }

    fun setComponent(){
        mMenuList = ArrayList()
        mAdapter = MenuAdapter(this, mMenuList)

        val mLayoutManager = GridLayoutManager(this, 2)
        rvMenu.setLayoutManager(mLayoutManager)
        rvMenu.setAdapter(mAdapter);
    }

    override fun refreshAdapter() {
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(applicationContext, ProjectsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout -> {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Anda sudah logout !", Toast.LENGTH_LONG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }

    private var doubleBackToExitPressedOnce = false
    /*override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please back once again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }*/
}