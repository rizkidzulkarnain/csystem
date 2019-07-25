package co.ejjv.ccms_mobile.module.menu_close_pl

class ClosePLImpl : ClosePLContract.Presenter {
    var mRegisterPLView: ClosePLContract.View

    constructor(listPLView: ClosePLContract.View) {
        this.mRegisterPLView = listPLView
    }

    override fun setScanner(tag: String) {
        mRegisterPLView.showScanner(tag)
    }
}