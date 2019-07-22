package co.ejjv.ccms_mobile.model.response.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MasterDiscCat {
    @SerializedName("MasterCategory")
    @Expose
    var masterCategory: List<MasterCategory>? = null
    @SerializedName("MasterDiscipline")
    @Expose
    var masterDiscipline: List<MasterDiscipline>? = null
}