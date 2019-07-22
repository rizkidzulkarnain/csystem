package co.ejjv.ccms_mobile.module.menu_list_pl.listpldet

import co.ejjv.ccms_mobile.model.response.gson.ListPLImage
import co.ejjv.ccms_mobile.model.response.gson.PLDet
import co.ejjv.ccms_mobile.model.response.main._MainResp

class ListPLDetImpl : ListPLDetContract.Presenter,
    ListPLDetContract.Model.OnFinishedListener {
    var mListPLView: ListPLDetContract.View
    var mListPLDataInteractor: ListPLDetContract.Model

    constructor(listPLView: ListPLDetContract.View, listPLDataInteractor: ListPLDetContract.Model) {
        this.mListPLView = listPLView
        this.mListPLDataInteractor = listPLDataInteractor
    }

    override fun getListPLDet(id: Int) {
        mListPLView.showLoading()
        mListPLDataInteractor.getListPLDet(this, id)
    }

    override fun onSuccess(mainResp: _MainResp<PLDet>) {
        var listPLDet_Hash = ArrayList<HashMap<String, String>>()
        var PLDet_Hash : HashMap<String, String> = HashMap()

        PLDet_Hash.put("Punchlist Number", mainResp.data!!.getPunchNo()!!)
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Punchlist Description", mainResp.data!!.getDetails() ?: "")
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Tag Reference", mainResp.data!!.getTagReference() ?: "")
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Category", mainResp.data!!.getCategory() ?: "")
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Phase", mainResp.data!!.getPhase() ?: "")
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Discipline", mainResp.data!!.getDiscipline() ?: "")
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Target Date", (mainResp.data!!.getTargetDate() ?: "") as String)
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Entered Signature", (mainResp.data!!.getCreatedBy() ?: ""))
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("System No", (mainResp.data!!.getSystemNo() ?: "") as String)
        listPLDet_Hash.add(PLDet_Hash)

        PLDet_Hash = HashMap()
        PLDet_Hash.put("Sub System No", (mainResp.data!!.getSubSystemNo() ?: "") as String)
        listPLDet_Hash.add(PLDet_Hash)

        mListPLView.setListPLDet(listPLDet_Hash, mainResp!!.data!!.getListPLImage() as ArrayList<ListPLImage>)
        mListPLView.hideLoading()
    }

    override fun onFailure(t: Throwable) {
        mListPLView.hideLoading()
    }
}