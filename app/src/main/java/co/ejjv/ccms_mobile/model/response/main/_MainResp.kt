package co.ejjv.ccms_mobile.model.response.main


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class _MainResp<T> {

    @SerializedName("Status")
    @Expose
    var status: Boolean? = null
    @SerializedName("Message")
    @Expose
    var message: String? = null
    @SerializedName("Data")
    @Expose
    var data: T? = null
    @SerializedName("TotalData")
    @Expose
    var totalData: Int? = null

}