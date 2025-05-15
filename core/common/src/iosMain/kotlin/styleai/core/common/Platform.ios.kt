package styleai.core.common

import kotlin.experimental.ExperimentalNativeApi

actual fun platform(): Platform = Platform.IOS

@OptIn(ExperimentalNativeApi::class)
actual fun isDebugBuild(): Boolean = kotlin.native.Platform.isDebugBinary
