package co.ejjv.ccms_mobile.model.response.main

class Menu {
    var title: String? = null
    var iconMenu: Int = 0
    var id: Int = 0

    constructor(id: Int, title: String, iconMenu: Int) {
        this.id = id
        this.title = title
        this.iconMenu = iconMenu
    }
}