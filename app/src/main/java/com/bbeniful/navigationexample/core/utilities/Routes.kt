package com.bbeniful.navigationexample.core.utilities

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object SecondScreen : Routes("second_screen")
    data object CustomNav : Routes("custom_nav")
    data object ResultData : Routes("result_data")

    sealed class SharedViewModelRoute(val route: String) {
        data object SharedVM : SharedViewModelRoute("sharedVM")
        data object Start : SharedViewModelRoute("start")
        data object SharedViewModelCompose : Routes("shared_view_model_compose")
    }
}