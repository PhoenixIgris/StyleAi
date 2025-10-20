package styleai.core.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import styleai.core.models.response.RefreshTokenResponse
import styleai.core.network.constants.ApiEndpoints
import styleai.core.network.constants.ApiEndpoints.REFRESH_TOKEN
import styleai.core.network.extensions.get

interface StyleAiRemoteApi {

    suspend fun refreshToken(
        token: String,
        block: HttpRequestBuilder.() -> Unit
    ): ApiResult<RefreshTokenResponse>

    suspend fun getStadiumData(): ApiResult<String>

}

class StyleAiRemoteApiImpl private constructor(
    private val httpClient: HttpClient
) : StyleAiRemoteApi {


    override suspend fun refreshToken(
        token: String,
        block: HttpRequestBuilder.() -> Unit
    ): ApiResult<RefreshTokenResponse> =
        httpClient.get(path = REFRESH_TOKEN, block = {
            header("Authorization", "Bearer $token")
            block(this)
        })

    override suspend fun getStadiumData(): ApiResult<String> =
        httpClient.get(ApiEndpoints.STADIUM_DATA)

    companion object {
        operator fun invoke(httpClient: HttpClient) = with(httpClient) {
            StyleAiRemoteApiImpl(httpClient)
        }
    }
}
