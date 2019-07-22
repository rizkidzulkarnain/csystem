package co.ejjv.ccms_mobile.model.response.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tag {
    @SerializedName("RN")
    @Expose
    private var rN: Int? = null
    @SerializedName("ID")
    @Expose
    private var iD: Int? = null
    @SerializedName("ItemKey")
    @Expose
    private var itemKey: String? = null
    @SerializedName("ItemDesc")
    @Expose
    private var itemDesc: String? = null
    @SerializedName("ItemTable")
    @Expose
    private var itemTable: String? = null
    @SerializedName("ItemTagType")
    @Expose
    private var itemTagType: String? = null
    @SerializedName("ItemRecordID")
    @Expose
    private var itemRecordID: Int? = null
    @SerializedName("ItemProject")
    @Expose
    private var itemProject: String? = null

    fun getRN(): Int? {
        return rN
    }

    fun setRN(rN: Int?) {
        this.rN = rN
    }

    fun getID(): Int? {
        return iD
    }

    fun setID(iD: Int?) {
        this.iD = iD
    }

    fun getItemKey(): String? {
        return itemKey
    }

    fun setItemKey(itemKey: String) {
        this.itemKey = itemKey
    }

    fun getItemDesc(): String? {
        return itemDesc
    }

    fun setItemDesc(itemDesc: String) {
        this.itemDesc = itemDesc
    }

    fun getItemTable(): String? {
        return itemTable
    }

    fun setItemTable(itemTable: String) {
        this.itemTable = itemTable
    }

    fun getItemTagType(): String? {
        return itemTagType
    }

    fun setItemTagType(itemTagType: String) {
        this.itemTagType = itemTagType
    }

    fun getItemRecordID(): Int? {
        return itemRecordID
    }

    fun setItemRecordID(itemRecordID: Int?) {
        this.itemRecordID = itemRecordID
    }

    fun getItemProject(): String? {
        return itemProject
    }

    fun setItemProject(itemProject: String) {
        this.itemProject = itemProject
    }
}