package com.bbeniful.navigationexample.cutsomObj

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bbeniful.navigationexample.core.domain.models.CustomModel

@Composable
fun CustomTypeCompose(customModel: CustomModel) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = customModel.name)
        Text(text = "${customModel.someInt}")
    }
}