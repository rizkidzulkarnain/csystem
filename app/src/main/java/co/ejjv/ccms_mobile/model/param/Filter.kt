package co.ejjv.ccms_mobile.model.param

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Filter {
    @SerializedName("property")
    @Expose
    var property: String? = null
    @SerializedName("value")
    @Expose
    var value: String? = null
    @SerializedName("operator")
    @Expose
    var operator: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

    constructor(property : String, value : String, operator : String){
        this.property = property
        this.value = value
        this.operator = operator
    }

    constructor(property : String, value : String, operator : String, type : String){
        this.property = property
        this.value = value
        this.operator = operator
        this.type = type
    }

    //testing
}