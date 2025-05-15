package styleai.core.common



actual fun platform() = Platform.Android

actual fun isDebugBuild(): Boolean = BuildConfig.DEBUG
