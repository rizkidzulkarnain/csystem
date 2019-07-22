package co.ejjv.ccms_mobile.module.menu_list_pl.listpldet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import co.ejjv.ccms_mobile.model.response.gson.ListPLImage
import co.ejjv.ccms_mobile.util.LoadingDialog
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.activity_list_pl_det.*


class ListPLDetActivity : AppCompatActivity(), ListPLDetContract.View {
    lateinit var mPLImplDet: ListPLDetContract.Presenter
    lateinit var mLoading: LoadingDialog
    lateinit var mListPLAdapter: ListPLDetAdapter
    lateinit var mListPLDet: ArrayList<HashMap<String, String>>
    lateinit var mListPLDetImage: ArrayList<ListPLImage>
    var mId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pl_det)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mPLImplDet = ListPLDetImpl(
            this,
            ListPLDetDataInteractor()
        )
        mLoading = LoadingDialog.getInstance(this)

        val param = intent.extras
        if (param != null)
            mId = param.getInt("id")

        setComponent()
    }

    override fun onResume() {
        super.onResume()
        mPLImplDet.getListPLDet(mId!!)
    }

    fun setComponent() {
        mListPLDet = ArrayList()
        mListPLDetImage = ArrayList()
        mListPLAdapter = ListPLDetAdapter(this, mListPLDet, mListPLDetImage)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPLDet.layoutManager = layoutManager
        rvPLDet.adapter = mListPLAdapter;
    }

    override fun setListPLDet(listPLDet: ArrayList<HashMap<String, String>>, listPLDetImage: ArrayList<ListPLImage>) {
        mListPLDet.clear()
        mListPLDet.addAll(listPLDet)
        mListPLDetImage.clear()
        mListPLDetImage.addAll(listPLDetImage)

        mListPLAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        mLoading.show()
    }

    override fun hideLoading() {
        mLoading.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}