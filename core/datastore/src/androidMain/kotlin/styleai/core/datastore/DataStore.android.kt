package styleai.core.datastore

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings
import styleai.core.datastore.Constants.DEVICE_PREFERENCES
import styleai.core.datastore.Constants.USER_PREFERENCES
import me.sujanpoudel.utils.contextProvider.applicationContext

internal actual fun deviceDataStore(): ObservableSettings {
    val name = "${applicationContext.packageName}.$DEVICE_PREFERENCES"

    return SharedPreferencesSettings(
        applicationContext.getSharedPreferences(
            name,
            Context.MODE_PRIVATE
        )
    )
}

internal actual fun userDataStore(): ObservableSettings {
    val name = "${applicationContext.packageName}.$USER_PREFERENCES"

    return SharedPreferencesSettings(
        applicationContext.getSharedPreferences(
            name,
            Context.MODE_PRIVATE
        )
    )
}
