package styleai.core.network

import com.github.kittinunf.result.Result
import styleai.core.models.ApiCallFailure
import styleai.core.models.Response


typealias ApiResult<T> = Result<Response<T>, ApiCallFailure>
typealias RawApiResult<T> = Result<T, ApiCallFailure>
