package co.ejjv.ccms_mobile.model.param

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ParamMain {
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("sort")
    @Expose
    var sort: List<Sort>? = ArrayList()
    @SerializedName("filter")
    @Expose
    var filter: List<Filter>? = ArrayList()
    @SerializedName("projectID")
    @Expose
    var projectID: String? = null

    constructor(page: Int, limit: Int, sort: List<Sort>?, filter: List<Filter>?, projectID: String){
        this.page = page
        this.limit = limit
        this.sort = sort
        this.filter = filter
        this.projectID = projectID
    }
}