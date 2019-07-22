package co.ejjv.ccms_mobile.module.menu_list_pl.listpl

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.module.menu_list_pl.listpldet.ListPLDetActivity
import co.ejjv.ccms_mobile.util.LoadingDialog
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.activity_list_pl.*

class ListPLActivity : AppCompatActivity(), ListPLContract.View {
    lateinit var mPLImpl: ListPLContract.Presenter
    lateinit var mLoading: LoadingDialog
    lateinit var mListPLAdapter: ListPLAdapter
    lateinit var mListPL: ArrayList<PL>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pl)
        setSupportActionBar(toolbarSearch)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mPLImpl = ListPLImpl(
            this,
            ListPLDataInteractor()
        )
        mLoading = LoadingDialog.getInstance(this)

        ivNext.setOnClickListener(){
            mPLImpl.onNextPage()
        }

        ivPrev.setOnClickListener(){
            mPLImpl.onPrevPage()
        }
        setComponent()
    }

    override fun onResume() {
        super.onResume()
        mPLImpl.getListPL()
    }

    fun setComponent() {
        swipePL.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            mPLImpl.onRefreshSource()
        })

        mListPL = ArrayList()
        mListPLAdapter = ListPLAdapter(this, mListPL, this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPlList.setLayoutManager(layoutManager)
        rvPlList.setAdapter(mListPLAdapter);
    }

    override fun setListPL(listPL: ArrayList<PL>) {
        mListPL.clear()
        mListPL.addAll(listPL)
        refreshAdapter()
    }

    override fun hideRefresh() {
        swipePL.isRefreshing = false
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

    override fun showToast(imsg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToListPLDetActivity(id: Int) {
        val intent = Intent(applicationContext, ListPLDetActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun refreshAdapter() {
        mListPLAdapter.notifyDataSetChanged()
    }

    override fun getPLAdapter(): ListPLAdapter {
        return mListPLAdapter
    }

    override fun setPagination(page: Int, totalData: Int) {
        var totalPage : Int
        if(totalData % 10 == 0){
            totalPage = totalData/10
        }else{
            totalPage = totalData/10 + 1
        }

        tvPage.text = "$page/$totalPage"

        if(page == 1){
            ivPrev.visibility = View.INVISIBLE
        }else{
            ivPrev.visibility = View.VISIBLE
        }

        if(totalData == 0){
            ivNext.visibility = View.INVISIBLE
        }else{
            ivNext.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        mPLImpl.onFilterDataSource(searchView)
        return true
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