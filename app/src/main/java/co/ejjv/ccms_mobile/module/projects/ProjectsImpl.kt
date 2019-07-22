package co.ejjv.ccms_mobile.module.projects

import co.ejjv.ccms_mobile.util.StaticHelper

class ProjectsImpl : ProjectsContract.Presenter {
    var mLoginView: ProjectsContract.View
    var mSpinnerKey = HashMap<Int, String>()

    constructor(loginView: ProjectsContract.View) {
        this.mLoginView = loginView
    }

    override fun setListProject() {
        val listProject = StaticHelper.USER!!.getListProject()!!
        var spinnerArrayList = ArrayList<String?>()
        for (i in 0 until listProject.size) {
            mSpinnerKey[i] = listProject[i].mainField!!
            spinnerArrayList.add(listProject[i].descField!!)
        }
        mLoginView.setSpinnerListProject(spinnerArrayList)
    }

    override fun onClickButton(idProject: Int) {
        StaticHelper.PROJECT = mSpinnerKey[idProject]!!
        mLoginView.goMenuActivity()
    }
}