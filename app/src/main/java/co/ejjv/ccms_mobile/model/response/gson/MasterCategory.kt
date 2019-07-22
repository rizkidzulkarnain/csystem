package co.ejjv.ccms_mobile.model.response.gson


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MasterCategory {

    @SerializedName("ID")
    @Expose
    private var iD: Int? = null
    @SerializedName("Code")
    @Expose
    private var code: String? = null
    @SerializedName("Description")
    @Expose
    private var description: String? = null

    fun getID(): Int? {
        return iD
    }

    fun setID(iD: Int?) {
        this.iD = iD
    }

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String) {
        this.code = code
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }


}