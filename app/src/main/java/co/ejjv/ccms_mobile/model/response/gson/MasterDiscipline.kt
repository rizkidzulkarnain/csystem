package co.ejjv.ccms_mobile.model.response.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MasterDiscipline {
    @SerializedName("ID")
    @Expose
    var iD: Int? = null
    @SerializedName("DisciplineCode")
    @Expose
    var disciplineCode: String? = null
    @SerializedName("Description")
    @Expose
    var description: String? = null
    @SerializedName("Completion")
    @Expose
    var completion: String? = null
    @SerializedName("Punchlist")
    @Expose
    var punchlist: String? = null
    @SerializedName("Equipment")
    @Expose
    var equipment: String? = null
    @SerializedName("Cable")
    @Expose
    var cable: String? = null
    @SerializedName("Preservation")
    @Expose
    var preservation: String? = null
    @SerializedName("ProjectID")
    @Expose
    var projectID: String? = null
    @SerializedName("Icon")
    @Expose
    var icon: String? = null
}