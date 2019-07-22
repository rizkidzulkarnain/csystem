package co.ejjv.ccms_mobile.network

import co.ejjv.ccms_mobile.model.param.ParamMain
import co.ejjv.ccms_mobile.model.param.ParamSaveRegisterPL
import co.ejjv.ccms_mobile.model.response.gson.*
import co.ejjv.ccms_mobile.model.response.main._MainResp
import com.example.ParamLogin
import com.example.ParamMaster
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface NetworkService {
    @POST("Account/Login")
    fun getLogin(@Body params: ParamLogin): Call<_MainResp<User>>

    @POST("pl/GetAll")
    fun getPLAll(@Body params: ParamMain): Call<_MainResp<ArrayList<PL>>>

    @GET("pl/GetById/{id}")
    fun getPLDet(@Path("id") id: Int): Call<_MainResp<PLDet>>

    @POST("pl/GetProxyList")
    fun getTagAll(@Body params: ParamMain): Call<_MainResp<ArrayList<Tag>>>

    @POST("pl/GetMaster")
    fun getMaster(@Body params: ParamMaster): Call<_MainResp<MasterDiscCat>>

    @Multipart
    @POST("pl/CreateWithUpload")
    fun saveRegisterPL(@Part("jsonData") name: RequestBody, @Part file: ArrayList<MultipartBody.Part>): Call<_MainResp<Int>>
}