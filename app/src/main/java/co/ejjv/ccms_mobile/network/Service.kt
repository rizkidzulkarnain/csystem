package co.ejjv.ccms_mobile.network

class Service {
    private var networkService: NetworkService? = null

    fun Service(networkService: NetworkService) {
        this.networkService = networkService
    }
}