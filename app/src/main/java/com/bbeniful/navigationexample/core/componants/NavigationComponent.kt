package com.bbeniful.navigationexample.core.componants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bbeniful.navigationexample.SharedViewModelCompose
import com.bbeniful.navigationexample.core.SharedViewModel
import com.bbeniful.navigationexample.core.domain.models.CustomModel
import com.bbeniful.navigationexample.core.extensions.sharedViewModel
import com.bbeniful.navigationexample.core.utilities.Routes
import com.bbeniful.navigationexample.cutsomObj.CustomTypeCompose
import com.bbeniful.navigationexample.home.Home
import com.bbeniful.navigationexample.resultBack.ParamAsResult
import com.bbeniful.navigationexample.second_screen.SecondScreen

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) { entry ->
            //previous entry, it is the same like setResult in the old XML view
            val data = entry.savedStateHandle.get<String>("result_data")
            Home(onNavigate = {
                //navigation with parameter in the path/route
                navController.navigate(Routes.SecondScreen.route + "?data=$it")

            }, onCustomNavigate = {customObj ->
                navController.navigate(Routes.CustomNav.route + "/${customObj.toGson()}")
            }, onResult = {
                navController.navigate(Routes.ResultData.route)
            },
                onSharedViewModel = {
                    navController.navigate(Routes.SharedViewModelRoute.SharedVM.route)
                }, resultData = data
            )
        }
        composable(
            //optional parameter
            Routes.SecondScreen.route + "?data={data}",
            arguments = listOf(navArgument(name = "data") {
                NavType.StringType
                defaultValue = ""
            })
        ) {
            val data = it.arguments?.getString("data")
            //get data directly from the entry
            // or get the data from navController inside the composable function
            SecondScreen(navController)
        }
        composable(
            Routes.CustomNav.route + "/{customData}", arguments = listOf(navArgument(
                name = "customData"
            ) {
                type = CustomModelNavType()
                nullable = true
            })
        ) {
            //Custom parcelable object pass
            val customType = it.arguments?.getCustomParcelable<CustomModel>("customData")
            CustomTypeCompose(customModel = customType ?: return@composable)
        }

        composable(Routes.ResultData.route) {
            ParamAsResult(navController = navController)
        }
        //Separated navigation for sharedViewModel
        navigation(
            startDestination = Routes.SharedViewModelRoute.Start.route,
            route = Routes.SharedViewModelRoute.SharedVM.route
        ) {
            composable(Routes.SharedViewModelRoute.Start.route) { entry ->
                val viewModel =
                    entry.sharedViewModel<SharedViewModel>(navController = navController)
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var text by remember {
                        mutableStateOf("")
                    }
                    TextField(
                        value = text, onValueChange = {
                            text = it
                        }, modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                    )
                    Button(onClick = {
                        viewModel.updateData(text)
                        navController.navigate(Routes.SharedViewModelRoute.SharedViewModelCompose.route)
                    }) {
                        Text(text = "Asd")
                    }
                }
            }
            composable(Routes.SharedViewModelRoute.SharedViewModelCompose.route) { entry ->
                val viewModel =
                    entry.sharedViewModel<SharedViewModel>(navController = navController)
                val data = viewModel.data.collectAsState()
                SharedViewModelCompose(data = data.value)
            }
        }
    }
}
