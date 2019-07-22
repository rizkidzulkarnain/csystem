package co.ejjv.ccms_mobile.model.param

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sort {
    @SerializedName("property")
    @Expose
    var property: String? = null
    @SerializedName("direction")
    @Expose
    var direction: String? = null

    constructor(property : String, direction : String){
        this.property = property
        this.direction = direction
    }
}