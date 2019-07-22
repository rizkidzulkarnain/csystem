package com.example

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class ParamLogin {

    constructor(username : String, password : String){
        this.username = username;
        this.password = password;
    }

    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
}