package co.ejjv.ccms_mobile.model.response.gson


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListProject {
    @SerializedName("MainField")
    @Expose
    var mainField: String? = null
    @SerializedName("DescField")
    @Expose
    var descField: String? = null
}