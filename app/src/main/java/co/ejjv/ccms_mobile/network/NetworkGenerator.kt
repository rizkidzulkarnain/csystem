package co.ejjv.ccms_mobile.network

import co.ejjv.ccms_mobile.util.StaticHelper
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkGenerator {
    companion object {
        private val aokHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(getLogBody())
            .build()

        private fun getLogBody() : HttpLoggingInterceptor{
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }
        //untuk @serialized in case sensitive
        //ini belum berhasil pake yang alternative saja dulu, seharusnya bisa upper otomatis yang huruf awal
        //semial item_sample --> Item_sample --> UPPER CAMEL CASE //https://futurestud.io/tutorials/gson-builder-basics-naming-policies
        var gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create()
        private val aretrofitBuilder = Retrofit.Builder()
            .baseUrl(StaticHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

        fun <S> createService(serviceClass: Class<S>): S {
            val aretrofit = aretrofitBuilder.client(aokHttpClient).build()
            return aretrofit.create(serviceClass)
        }
    }
}
