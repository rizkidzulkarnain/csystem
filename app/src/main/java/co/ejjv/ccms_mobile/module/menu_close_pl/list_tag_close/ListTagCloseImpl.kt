package co.ejjv.ccms_mobile.module.menu_close_pl.list_tag_close

import android.support.v7.widget.SearchView
import co.ejjv.ccms_mobile.model.param.Filter
import co.ejjv.ccms_mobile.model.param.ParamClosePL
import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.param.Sort
import co.ejjv.ccms_mobile.model.response.gson.PL
import co.ejjv.ccms_mobile.model.response.gson.Tag
import co.ejjv.ccms_mobile.model.response.main._MainResp
import co.ejjv.ccms_mobile.util.StaticHelper

class ListTagCloseImpl : ListTagCloseContract.Presenter,
    ListTagCloseContract.Model.OnFinishedListener {
    var mListTagView: ListTagCloseContract.View
    var mlistTagCloseDataInteractor: ListTagCloseContract.Model
    lateinit var mParamMain: ParamMain

    var mPage: Int = 1
    var mIsSearch: Boolean = false
    var mQuerysearch: String = ""
    var mQueryTextChange: Boolean = false

    constructor(listTagView: ListTagCloseContract.View, listTagCloseDataInteractor: ListTagCloseContract.Model) {
        this.mListTagView = listTagView
        this.mlistTagCloseDataInteractor = listTagCloseDataInteractor
    }

    override fun getListTag() {
        mListTagView.showLoading()
        var filter = Filter("PLStatus", "0", "eq")
        var listFilter = ArrayList<Filter>()
        listFilter.add(filter)

        var sort = Sort("ID", "DESC")
        var listSort = ArrayList<Sort>()
        listSort.add(sort)

        mParamMain = ParamMain(mPage, 10, listSort, listFilter, StaticHelper.PROJECT)

        mlistTagCloseDataInteractor.getListTagAll(this, mParamMain)
    }

    override fun saveListClosePL(listSelectList : List<PL>) {
        var listClosePL = arrayListOf<ParamClosePL>()
        for(item in listSelectList){
            var paramClosePL = ParamClosePL()
            paramClosePL.actionBy = StaticHelper.USER!!.getUserName()
            paramClosePL.id = item.getProjectID()
            paramClosePL.projectID = StaticHelper.PROJECT
            paramClosePL.remarks = "testing"
            listClosePL.add(paramClosePL)
        }
        mlistTagCloseDataInteractor.saveClosePL(this, listClosePL)
    }

    override fun onRefreshSource() {
        mlistTagCloseDataInteractor.getListTagAll(this, mParamMain)
    }

    override fun onSuccess(mainResp: _MainResp<ArrayList<PL>>, tag : String) {
        when(tag){
            "get_close_pl"->{
                mListTagView.setListTag(mainResp.data!!)
                mListTagView.setPagination(mPage, mainResp.totalData!!)
                mListTagView.hideLoading()
                mListTagView.hideRefresh()
            }
        }
    }

    override fun onSuccessSave(mainResp: _MainResp<Int>) {
        mListTagView.showAlertDialog(mainResp.data.toString() + " Punchlist Berhasil di close !", 3)
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
        mlistTagCloseDataInteractor.getListTagAll(this@ListTagCloseImpl, paramMain)
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