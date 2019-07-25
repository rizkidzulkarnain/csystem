package co.ejjv.ccms_mobile.model.param

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

class ParamSaveRegisterPL {
    @SerializedName("TagReference")
    @Expose
    var tagReference: String? = null
    @SerializedName("Details")
    @Expose
    var details: String? = null
    @SerializedName("Category")
    @Expose
    var category: String? = null
    @SerializedName("Phase")
    @Expose
    var phase: String? = null
    @SerializedName("Discipline")
    @Expose
    var discipline: String? = null
    @SerializedName("TargetDate")
    @Expose
    var targetDate: String? = null
    @SerializedName("CreatedBy")
    @Expose
    var createdBy: String? = null
    @SerializedName("CreatedOn")
    @Expose
    var createdOn: String? = null
    @SerializedName("ProjectID")
    @Expose
    var projectID: String? = null
}