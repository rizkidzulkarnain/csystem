package co.ejjv.ccms_mobile.module.projects

interface ProjectsContract {
    interface View {
        fun setSpinnerListProject(spinnerValue: ArrayList<String?>)
        fun goMenuActivity()
    }

    interface Presenter {
        fun onClickButton(idProject : Int)
        fun setListProject();
    }

    interface Model {

    }
}