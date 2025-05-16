package styleai.core.network.extensions

import styleai.core.models.ApiCallFailure


actual fun Exception.handlePlatformError(): ApiCallFailure {
    return this.message?.let { ApiCallFailure.HTTPFailure(it) }
        ?: ApiCallFailure.UnknownFailure
}