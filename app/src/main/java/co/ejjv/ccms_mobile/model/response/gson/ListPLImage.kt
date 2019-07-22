package co.ejjv.ccms_mobile.model.response.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ListPLImage {
    @SerializedName("ID")
    @Expose
    var id: Int? = null
    @SerializedName("TIPunchList_ID")
    @Expose
    var tiPunchListID: Int? = null
    @SerializedName("FileName")
    @Expose
    var fileName: String? = null
    @SerializedName("Description")
    @Expose
    var description: String? = null
    @SerializedName("UploadOn")
    @Expose
    var uploadOn: String? = null
    @SerializedName("UploadBy")
    @Expose
    var uploadBy: String? = null
    @SerializedName("FilePath")
    @Expose
    var filePath: Any? = null
    @SerializedName("UrlFile")
    @Expose
    var urlFile: String? = null

}