package co.ejjv.ccms_mobile.util

import android.app.AlertDialog
import android.content.Context
import dmax.dialog.SpotsDialog


class LoadingDialog {
    private var mContext: Context
    private var mDialog: AlertDialog

    companion object {
        var single_instance: LoadingDialog? = null

        fun getInstance(context: Context): LoadingDialog {
            single_instance = LoadingDialog(context)

            return single_instance as LoadingDialog
        }
    }

    constructor(context: Context) {
        this.mContext = context

        this.mDialog = SpotsDialog.Builder().setContext(mContext).build()
        mDialog.setMessage("Please wait...")
    }

    fun show() {
        mDialog.show()
    }

    fun hide() {
        mDialog.hide()
    }
}