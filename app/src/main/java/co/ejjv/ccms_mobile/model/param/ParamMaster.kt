package com.example

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class ParamMaster {

    constructor(master : String){
        this.master = master;
    }

    @SerializedName("master")
    @Expose
    var master: String? = null
}