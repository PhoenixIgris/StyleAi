package styleai.core.data.utils

import com.github.kittinunf.result.Result
import styleai.core.models.ApiCallFailure
import styleai.core.models.Response

fun <T> Result<Response<T>, ApiCallFailure>.toResult(): Result<T?, ApiCallFailure> {
    return this.fold(
        success = { response ->
            response.data?.let {
                Result.success(it)
            } ?: Result.success(null)
        },
        failure = { failure -> Result.failure(failure) }
    )
}
