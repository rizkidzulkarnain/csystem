package co.ejjv.ccms_mobile.model.param

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

class ParamClosePL {
    @SerializedName("ID")
    @Expose
    var id: String? = null
    @SerializedName("ActionBy")
    @Expose
    var actionBy: String? = null
    @SerializedName("ProjectID")
    @Expose
    var projectID: String? = null
    @SerializedName("Remarks")
    @Expose
    var remarks: String? = null
}