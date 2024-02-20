

package com.bbeniful.navigationexample.core.componants

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.bbeniful.navigationexample.core.domain.models.CustomModel
import com.google.gson.Gson

class CustomModelNavType : NavType<CustomModel>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): CustomModel? {
        return bundle.getCustomParcelable<CustomModel>(key)
    }

    override fun parseValue(value: String): CustomModel {
        return Gson().fromJson(value, CustomModel::class.java)

    }

    override fun serializeAsValue(value: CustomModel): String {
        return Gson().toJson(value)
    }

    override fun put(bundle: Bundle, key: String, value: CustomModel) {
        bundle.putParcelable(key, value)

    }
}


@Suppress("DEPRECATION")
inline fun <reified T> Bundle.getCustomParcelable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelable(key, T::class.java)
    } else {
        this.getParcelable(key)
    }
}