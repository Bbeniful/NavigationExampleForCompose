package com.bbeniful.navigationexample.resultBack

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import com.bbeniful.navigationexample.core.extensions.popBackstackIfCan


@Composable
fun ParamAsResult(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    BackHandler {
        navController.previousBackStackEntry?.savedStateHandle?.set("result_data", text)
        navController.popBackstackIfCan()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text, onValueChange = {
                text = it
            }, modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Button(onClick = {
            navController.previousBackStackEntry?.savedStateHandle?.set("result_data", text)
            navController.popBackstackIfCan()
        }) {
            Text(text = "Finish")
        }
    }
}