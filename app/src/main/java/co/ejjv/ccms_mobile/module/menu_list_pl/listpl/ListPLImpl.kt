package co.ejjv.ccms_mobile.module.menu_list_pl.listpl

import android.support.v7.widget.SearchView
import co.ejjv.ccms_mobile.model.param.Filter
import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.param.Sort
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.util.StaticHelper

class ListPLImpl : ListPLContract.Presenter,
    ListPLContract.Model.OnFinishedListener {
    var mListPLView: ListPLContract.View
    var mListPLDataInteractor: ListPLContract.Model
    lateinit var mParamMain: ParamMain

    var mPage : Int = 1
    var mIsSearch : Boolean = false
    var mQuerysearch : String = ""
    var mQueryTextChange : Boolean = false

    constructor(listPLView: ListPLContract.View, listPLDataInteractor: ListPLContract.Model) {
        this.mListPLView = listPLView
        this.mListPLDataInteractor = listPLDataInteractor
    }

    override fun getListPL() {
        mListPLView.showLoading()
        //paramMain sudah ada defaultnya tinggal diubah param yang ingin dirubah

        var filter = Filter("Category", "'B'", "eq")
        var listFilter = ArrayList<Filter>()
        listFilter.add(filter)

        var sort = Sort("ID", "DESC")
        var listSort = ArrayList<Sort>()
        listSort.add(sort)

        mParamMain = ParamMain(mPage, 10, listSort as List<Sort>, listFilter as List<Filter>, StaticHelper.PROJECT)

        mListPLDataInteractor.getListPLAll(this, mParamMain)
    }

    override fun onRefreshSource() {
        mListPLDataInteractor.getListPLAll(this, mParamMain)
    }

    override fun onSuccess(mainResp: _MainResp<ArrayList<PL>>) {
        mListPLView.setListPL(mainResp.data!!)
        mListPLView.setPagination(mPage, mainResp.totalData!!)
        mListPLView.hideLoading()
        mListPLView.hideRefresh()
    }

    override fun onFailure(t: Throwable) {
        mListPLView.showToast(t.message.toString())
        mListPLView.hideLoading()
        mListPLView.hideRefresh()
    }

    override fun onFilterDataSource(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mIsSearch = true
                mQuerysearch = query
                mPage = 1
                searchParam(mQuerysearch)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.equals("")){
                    if(mQueryTextChange) { // biar tidak reload yang pertama kali di akses
                        mPage = 1
                        mIsSearch = false
                        mQueryTextChange = false
                        getListPL()
                    }
                    mQueryTextChange = true
                }
                return true
            }
        })
    }

    fun searchParam(query : String){
        mListPLView.showLoading()
        var filter = Filter("PunchNo", query, "like")
        var listFilter = ArrayList<Filter>()
        listFilter.add(filter)

        var sort = Sort("ID", "DESC")
        var listSort = ArrayList<Sort>()
        listSort.add(sort)

        var paramMain =
            ParamMain(mPage, 10, listSort as List<Sort>, listFilter as List<Filter>, StaticHelper.PROJECT)
        mListPLDataInteractor.getListPLAll(this@ListPLImpl, paramMain)
    }

    override fun onNextPage() {
        mPage += 1
        if(mIsSearch){
            searchParam(mQuerysearch)
        }else{
            getListPL()
        }
    }

    override fun onPrevPage() {
        mPage -= 1
        if(mIsSearch){
            searchParam(mQuerysearch)
        }else{
            getListPL()
        }
    }
}