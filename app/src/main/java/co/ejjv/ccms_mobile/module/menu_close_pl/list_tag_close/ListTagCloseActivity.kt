package co.ejjv.ccms_mobile.module.menu_register_pl.list_tag

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.ejjv.ccms_mobile.model.response.gson.Tag
import co.ejjv.ccms_mobile.util.LoadingDialog
import co.ejjv.ccms_mobile.util.RecyclerItemClickListener
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.activity_list_pl.*
import kotlinx.android.synthetic.main.activity_list_pl.toolbarSearch
import kotlinx.android.synthetic.main.activity_list_tag.*
import kotlinx.android.synthetic.main.activity_list_tag.ivNext
import kotlinx.android.synthetic.main.activity_list_tag.ivPrev
import kotlinx.android.synthetic.main.activity_list_tag.tvPage

class ListTagActivity : AppCompatActivity(), ListTagContract.View {
    lateinit var mTagImpl: ListTagContract.Presenter
    lateinit var mLoading: LoadingDialog
    lateinit var mListTagAdapter: ListTagAdapter

    lateinit var mListTag: ArrayList<Tag>

    /*ini untuk selection by long click*/
    lateinit var mContextMenu: Menu
    var mActionMode: ActionMode? = null
    lateinit var mListTagMulti: ArrayList<Tag>
    internal var isMultiSelect: Boolean = false
    /*END*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tag)
        setSupportActionBar(toolbarSearch)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mTagImpl = ListTagImpl(
            this,
            ListTagDataInteractor()
        )
        mLoading = LoadingDialog.getInstance(this)

        ivNext.setOnClickListener(){
            mTagImpl.onNextPage()
        }

        ivPrev.setOnClickListener(){
            mTagImpl.onPrevPage()
        }

        setComponent()
    }

    override fun onResume() {
        super.onResume()
        mTagImpl.getListTag()
    }

    private fun setComponent() {
        swipeTag.setOnRefreshListener {
            mTagImpl.onRefreshSource()
        }

        mListTag = ArrayList()
        mListTagMulti = ArrayList()
        mListTagAdapter = ListTagAdapter(this, mListTag, mListTagMulti, this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTagList.layoutManager = layoutManager
        rvTagList.adapter = mListTagAdapter

        //RecyclerItemClickListener berasal dari class util
        rvTagList.addOnItemTouchListener(
            RecyclerItemClickListener(
                this@ListTagActivity,
                rvTagList,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        /*if (isMultiSelect) {
                            multi_select(position)
                        }*/
                        if (!isMultiSelect) {
                            mListTagMulti = ArrayList()
                            isMultiSelect = false
                            if (mActionMode == null) {
                                mActionMode = startActionMode(mActionModeCallback)
                            }
                        }
                        multi_select(position)
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                        /*if (!isMultiSelect) {
                            mListTagMulti = ArrayList()
                            isMultiSelect = false
                            if (mActionMode == null) {
                                mActionMode = startActionMode(mActionModeCallback)
                            }
                        }
                        multi_select(position)*/
                    }
                })
        )
    }

    private val mActionModeCallback = object : ActionMode.Callback {
        private var statusBarColor: Int = 0
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            val inflater = mode.menuInflater
            inflater.inflate(R.menu.menu_longclick_selection, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarColor = window.statusBarColor
                window.statusBarColor = ContextCompat.getColor(this@ListTagActivity, R.color.colorPrimary) //resources.getColor(R.color.colorPrimary)
            }
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.action_select -> {
                    val tagNumber = mListTagMulti.get(0).getItemKey()
                    goToRegisterPLActivity(tagNumber!!)
                    return true
                }
                else -> return false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = statusBarColor
            }
            mActionMode = null
            isMultiSelect = false
            mListTagMulti = ArrayList()
            refreshAdapter2()
        }
    }

    fun multi_select(position: Int) {
        if (mActionMode != null) {
            if (mListTagMulti.contains(mListTag.get(position)))
                mListTagMulti.remove(mListTag.get(position))
            else
                mListTagMulti.add(mListTag.get(position))
            if (mListTagMulti.size > 0) {
                mActionMode!!.setTitle("" + mListTagMulti.size)
                if (mListTagMulti.size == 1) {
                    mActionMode!!.getMenu().findItem(R.id.action_select).setVisible(true)
                } else {
                    mActionMode!!.getMenu().findItem(R.id.action_select).setVisible(true)
                }
            } else {
                mActionMode!!.setTitle("0")
                mActionMode!!.getMenu().findItem(R.id.action_select).setVisible(false)
            }
            refreshAdapter2()
        }
    }

    fun refreshAdapter2() {
        mListTagAdapter.mListTag_selected = mListTagMulti
        mListTagAdapter.mListTag = mListTag
        mListTagAdapter.notifyDataSetChanged()
    }


    override fun setListTag(listTag: ArrayList<Tag>) {
        mListTag.clear()
        mListTag.addAll(listTag)
        refreshAdapter()
    }

    override fun hideRefresh() {
        swipeTag.isRefreshing = false
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

    override fun goToRegisterPLActivity(tag : String) {
        val intent = Intent()
        intent.putExtra("code", tag)
        intent.putExtra("tag", "scan_tag")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun refreshAdapter() {
        mListTagAdapter.notifyDataSetChanged()
    }

    override fun getTagAdapter(): ListTagAdapter {
        return mListTagAdapter
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
        mTagImpl.onFilterDataSource(searchView)
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