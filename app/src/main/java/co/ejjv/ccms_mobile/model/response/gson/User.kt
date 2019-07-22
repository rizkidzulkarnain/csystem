package co.ejjv.ccms_mobile.model.response.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {
    @SerializedName("RN")
    @Expose
    private var rN: Int? = null
    @SerializedName("ID")
    @Expose
    private var iD: Int? = null
    @SerializedName("Client_ID")
    @Expose
    private var clientID: Int? = null
    @SerializedName("AppGroup_ID")
    @Expose
    private var appGroupID: Int? = null
    @SerializedName("GroupName")
    @Expose
    private var groupName: String? = null
    @SerializedName("UserName")
    @Expose
    private var userName: String? = null
    @SerializedName("UserPassword")
    @Expose
    private var userPassword: String? = null
    @SerializedName("FirstName")
    @Expose
    private var firstName: String? = null
    @SerializedName("MidName")
    @Expose
    private var midName: String? = null
    @SerializedName("LastName")
    @Expose
    private var lastName: String? = null
    @SerializedName("LastLogin")
    @Expose
    private var lastLogin: String? = null
    @SerializedName("Email")
    @Expose
    private var email: String? = null
    @SerializedName("Position")
    @Expose
    private var position: String? = null
    @SerializedName("Phone")
    @Expose
    private var phone: String? = null
    @SerializedName("Fax")
    @Expose
    private var fax: String? = null
    @SerializedName("PhoneExt")
    @Expose
    private var phoneExt: String? = null
    @SerializedName("Note")
    @Expose
    private var note: String? = null
    @SerializedName("CreateBy")
    @Expose
    private var createBy: Int? = null
    @SerializedName("CreateDate")
    @Expose
    private var createDate: String? = null
    @SerializedName("Creator")
    @Expose
    private var creator: String? = null
    @SerializedName("ModifiedBy")
    @Expose
    private var modifiedBy: Int? = null
    @SerializedName("ModifiedDate")
    @Expose
    private var modifiedDate: String? = null
    @SerializedName("Modifier")
    @Expose
    private var modifier: String? = null
    @SerializedName("DeleteFlag")
    @Expose
    private var deleteFlag: Int? = null
    @SerializedName("IsEmailFwd")
    @Expose
    private var isEmailFwd: Int? = null
    @SerializedName("Mobile")
    @Expose
    private var mobile: String? = null
    @SerializedName("IsMobileNotify")
    @Expose
    private var isMobileNotify: Int? = null
    @SerializedName("Picture")
    @Expose
    private var picture: String? = null
    @SerializedName("Signature")
    @Expose
    private var signature: String? = null
    @SerializedName("CurrentTheme")
    @Expose
    private var currentTheme: String? = null
    @SerializedName("LastPasswordChanged")
    @Expose
    private var lastPasswordChanged: String? = null
    @SerializedName("LastPasswordChangedBy")
    @Expose
    private var lastPasswordChangedBy: String? = null
    @SerializedName("SecurityQuestion1")
    @Expose
    private var securityQuestion1: String? = null
    @SerializedName("Answer1")
    @Expose
    private var answer1: String? = null
    @SerializedName("SecurityQuestion2")
    @Expose
    private var securityQuestion2: String? = null
    @SerializedName("Answer2")
    @Expose
    private var answer2: String? = null
    @SerializedName("IsLocked")
    @Expose
    private var isLocked: Int? = null
    @SerializedName("ForgotPasswordToken")
    @Expose
    private var forgotPasswordToken: String? = null
    @SerializedName("TokenCreateOn")
    @Expose
    private var tokenCreateOn: String? = null
    @SerializedName("InvalidCounter")
    @Expose
    private var invalidCounter: Int? = null
    @SerializedName("ExpiredPeriod")
    @Expose
    private var expiredPeriod: String? = null
    @SerializedName("ExpiredPeriodTotal")
    @Expose
    private var expiredPeriodTotal: Int? = null
    @SerializedName("ExpiredDate")
    @Expose
    private var expiredDate: String? = null
    @SerializedName("LastInvalidPasswordDate")
    @Expose
    private var lastInvalidPasswordDate: String? = null
    @SerializedName("IsPasswordResetted")
    @Expose
    private var isPasswordResetted: Int? = null
    @SerializedName("ListProject")
    @Expose
    private var listProject: List<ListProject>? = null

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

    fun getClientID(): Int? {
        return clientID
    }

    fun setClientID(clientID: Int?) {
        this.clientID = clientID
    }

    fun getAppGroupID(): Int? {
        return appGroupID
    }

    fun setAppGroupID(appGroupID: Int?) {
        this.appGroupID = appGroupID
    }

    fun getGroupName(): String? {
        return groupName
    }

    fun setGroupName(groupName: String) {
        this.groupName = groupName
    }

    fun getUserName(): String? {
        return userName
    }

    fun setUserName(userName: String) {
        this.userName = userName
    }

    fun getUserPassword(): String? {
        return userPassword
    }

    fun setUserPassword(userPassword: String) {
        this.userPassword = userPassword
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    fun getMidName(): String? {
        return midName
    }

    fun setMidName(midName: String) {
        this.midName = midName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun getLastLogin(): String? {
        return lastLogin
    }

    fun setLastLogin(lastLogin: String) {
        this.lastLogin = lastLogin
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPosition(): String? {
        return position
    }

    fun setPosition(position: String) {
        this.position = position
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getFax(): String? {
        return fax
    }

    fun setFax(fax: String) {
        this.fax = fax
    }

    fun getPhoneExt(): String? {
        return phoneExt
    }

    fun setPhoneExt(phoneExt: String) {
        this.phoneExt = phoneExt
    }

    fun getNote(): String? {
        return note
    }

    fun setNote(note: String) {
        this.note = note
    }

    fun getCreateBy(): Int? {
        return createBy
    }

    fun setCreateBy(createBy: Int?) {
        this.createBy = createBy
    }

    fun getCreateDate(): String? {
        return createDate
    }

    fun setCreateDate(createDate: String) {
        this.createDate = createDate
    }

    fun getCreator(): String? {
        return creator
    }

    fun setCreator(creator: String) {
        this.creator = creator
    }

    fun getModifiedBy(): Int? {
        return modifiedBy
    }

    fun setModifiedBy(modifiedBy: Int?) {
        this.modifiedBy = modifiedBy
    }

    fun getModifiedDate(): String? {
        return modifiedDate
    }

    fun setModifiedDate(modifiedDate: String) {
        this.modifiedDate = modifiedDate
    }

    fun getModifier(): String? {
        return modifier
    }

    fun setModifier(modifier: String) {
        this.modifier = modifier
    }

    fun getDeleteFlag(): Int? {
        return deleteFlag
    }

    fun setDeleteFlag(deleteFlag: Int?) {
        this.deleteFlag = deleteFlag
    }

    fun getIsEmailFwd(): Int? {
        return isEmailFwd
    }

    fun setIsEmailFwd(isEmailFwd: Int?) {
        this.isEmailFwd = isEmailFwd
    }

    fun getMobile(): String? {
        return mobile
    }

    fun setMobile(mobile: String) {
        this.mobile = mobile
    }

    fun getIsMobileNotify(): Int? {
        return isMobileNotify
    }

    fun setIsMobileNotify(isMobileNotify: Int?) {
        this.isMobileNotify = isMobileNotify
    }

    fun getPicture(): String? {
        return picture
    }

    fun setPicture(picture: String) {
        this.picture = picture
    }

    fun getSignature(): String? {
        return signature
    }

    fun setSignature(signature: String) {
        this.signature = signature
    }

    fun getCurrentTheme(): String? {
        return currentTheme
    }

    fun setCurrentTheme(currentTheme: String) {
        this.currentTheme = currentTheme
    }

    fun getLastPasswordChanged(): String? {
        return lastPasswordChanged
    }

    fun setLastPasswordChanged(lastPasswordChanged: String) {
        this.lastPasswordChanged = lastPasswordChanged
    }

    fun getLastPasswordChangedBy(): String? {
        return lastPasswordChangedBy
    }

    fun setLastPasswordChangedBy(lastPasswordChangedBy: String) {
        this.lastPasswordChangedBy = lastPasswordChangedBy
    }

    fun getSecurityQuestion1(): String? {
        return securityQuestion1
    }

    fun setSecurityQuestion1(securityQuestion1: String) {
        this.securityQuestion1 = securityQuestion1
    }

    fun getAnswer1(): String? {
        return answer1
    }

    fun setAnswer1(answer1: String) {
        this.answer1 = answer1
    }

    fun getSecurityQuestion2(): String? {
        return securityQuestion2
    }

    fun setSecurityQuestion2(securityQuestion2: String) {
        this.securityQuestion2 = securityQuestion2
    }

    fun getAnswer2(): String? {
        return answer2
    }

    fun setAnswer2(answer2: String) {
        this.answer2 = answer2
    }

    fun getIsLocked(): Int? {
        return isLocked
    }

    fun setIsLocked(isLocked: Int?) {
        this.isLocked = isLocked
    }

    fun getForgotPasswordToken(): String? {
        return forgotPasswordToken
    }

    fun setForgotPasswordToken(forgotPasswordToken: String) {
        this.forgotPasswordToken = forgotPasswordToken
    }

    fun getTokenCreateOn(): String? {
        return tokenCreateOn
    }

    fun setTokenCreateOn(tokenCreateOn: String) {
        this.tokenCreateOn = tokenCreateOn
    }

    fun getInvalidCounter(): Int? {
        return invalidCounter
    }

    fun setInvalidCounter(invalidCounter: Int?) {
        this.invalidCounter = invalidCounter
    }

    fun getExpiredPeriod(): String? {
        return expiredPeriod
    }

    fun setExpiredPeriod(expiredPeriod: String) {
        this.expiredPeriod = expiredPeriod
    }

    fun getExpiredPeriodTotal(): Int? {
        return expiredPeriodTotal
    }

    fun setExpiredPeriodTotal(expiredPeriodTotal: Int?) {
        this.expiredPeriodTotal = expiredPeriodTotal
    }

    fun getExpiredDate(): String? {
        return expiredDate
    }

    fun setExpiredDate(expiredDate: String) {
        this.expiredDate = expiredDate
    }

    fun getLastInvalidPasswordDate(): String? {
        return lastInvalidPasswordDate
    }

    fun setLastInvalidPasswordDate(lastInvalidPasswordDate: String) {
        this.lastInvalidPasswordDate = lastInvalidPasswordDate
    }

    fun getIsPasswordResetted(): Int? {
        return isPasswordResetted
    }

    fun setIsPasswordResetted(isPasswordResetted: Int?) {
        this.isPasswordResetted = isPasswordResetted
    }

    fun getListProject(): List<ListProject>? {
        return listProject
    }

    fun setListProject(listProject: List<ListProject>) {
        this.listProject = listProject
    }
}