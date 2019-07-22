package co.ejjv.ccms_mobile.util

import co.ejjv.ccms_mobile.model.response.gson.User

class StaticHelper {
    companion object {
        var BASE_URL = "http://192.168.1.85/csystem-api/api/" /*jangan lupa diubah juga di xml network security config ada opsi untuk 69 dan 85 */
        var PROJECT = ""
        var USER: User? = null
    }
}