package co.ejjv.ccms_mobile.module.menu_register_pl.list_tag

import android.support.v7.widget.SearchView
import co.ejjv.ccms_mobile.model.param.Filter
import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.param.Sort
import co.ejjv.ccms_mobile.model.response.gson.Tag
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.util.StaticHelper

class ListTagImpl : ListTagContract.Presenter,
    ListTagContract.Model.OnFinishedListener {
    var mListTagView: ListTagContract.View
    var mListTagDataInteractor: ListTagContract.Model
    lateinit var mParamMain: ParamMain

    var mPage: Int = 1
    var mIsSearch: Boolean = false
    var mQuerysearch: String = ""
    var mQueryTextChange: Boolean = false

    constructor(listTagView: ListTagContract.View, listTagDataInteractor: ListTagContract.Model) {
        this.mListTagView = listTagView
        this.mListTagDataInteractor = listTagDataInteractor
    }

    override fun getListTag() {
        mListTagView.showLoading()
        mParamMain = ParamMain(mPage, 10, null, null, StaticHelper.PROJECT)

        mListTagDataInteractor.getListTagAll(this, mParamMain)
    }

    override fun onRefreshSource() {
        mListTagDataInteractor.getListTagAll(this, mParamMain)
    }

    override fun onSuccess(mainResp: _MainResp<ArrayList<Tag>>) {
        mListTagView.setListTag(mainResp.data!!)
        mListTagView.setPagination(mPage, mainResp.totalData!!)
        mListTagView.hideLoading()
        mListTagView.hideRefresh()
    }

    override fun onFailure(t: Throwable) {
        mListTagView.hideLoading()
        mListTagView.hideRefresh()
    }

    override fun onFilterDataSource(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mIsSearch = true
                mQuerysearch = query
                mPage = 1
                searchParam(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.equals("")) {
                    if (mQueryTextChange) { // biar tidak reload yang pertama kali di akses
                        mPage = 1
                        mIsSearch = false
                        mQueryTextChange = false
                        getListTag()
                    }
                    mQueryTextChange = true
                }
                return true
            }
        })
    }

    fun searchParam(query: String) {
        mListTagView.showLoading()
        var filter = Filter("ItemDesc", query, "like")
        var listFilter = ArrayList<Filter>()
        listFilter.add(filter)

        var paramMain = ParamMain(1, 10, null, listFilter as List<Filter>, StaticHelper.PROJECT)
        mListTagDataInteractor.getListTagAll(this@ListTagImpl, paramMain)
    }

    override fun onNextPage() {
        mPage += 1
        if(mIsSearch){
            searchParam(mQuerysearch)
        }else{
            getListTag()
        }
    }

    override fun onPrevPage() {
        mPage -= 1
        if(mIsSearch){
            searchParam(mQuerysearch)
        }else{
            getListTag()
        }
    }
}