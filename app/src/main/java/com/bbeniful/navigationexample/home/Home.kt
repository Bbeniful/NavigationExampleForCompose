package com.bbeniful.navigationexample.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.bbeniful.navigationexample.core.domain.models.CustomModel

@Composable
fun Home(
    onNavigate: (String) -> Unit = {},
    onCustomNavigate: (CustomModel) -> Unit = {},
    onResult: () -> Unit = {},
    onSharedViewModel: (String) -> Unit,
    resultData: String? = null
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text, onValueChange = {
                text = it
            }, modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        if (!resultData.isNullOrEmpty()) {
            Text(text = resultData)
        }
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onNavigate(text) }) {
                Text(text = "Navigate to simple String")
            }

            Button(onClick = {
                onCustomNavigate(CustomModel(name = text, someInt = text.length))
            }) {
                Text(text = "Navigate to custom")
            }

            Button(onClick = onResult) {
                Text(text = "Navigate to result")
            }
            Button(onClick = { onSharedViewModel(text) }) {
                Text(text = "Navigate to sharedviewmodel")
            }
        }

    }
}