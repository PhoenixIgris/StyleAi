package styleai.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.timeout
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import styleai.core.models.response.RefreshTokenResponse
import styleai.core.models.style.StyleRequest
import styleai.core.models.style.StyleResponse
import styleai.core.network.constants.ApiEndpoints
import styleai.core.network.constants.ApiEndpoints.REFRESH_TOKEN
import styleai.core.network.extensions.get
import styleai.core.network.extensions.getRawText
import styleai.core.network.extensions.post
import kotlin.time.Duration.Companion.seconds

interface StyleAiRemoteApi {

    suspend fun refreshToken(
        token: String,
        block: HttpRequestBuilder.() -> Unit
    ): ApiResult<RefreshTokenResponse>

    suspend fun getStadiumData(): ApiResult<String>

    suspend fun getStyleRecommendation(request: StyleRequest): RawApiResult<StyleResponse>

    suspend fun checkStyleBackendHealth(): RawApiResult<String>

}

class StyleAiRemoteApiImpl private constructor(
    private val authHttpClient: HttpClient,
    private val noAuthHttpClient: HttpClient,
    styleBackendBaseUrl: String,
) : StyleAiRemoteApi {

    private val styleBackendBaseUrl = styleBackendBaseUrl.trimEnd('/')

    override suspend fun refreshToken(
        token: String,
        block: HttpRequestBuilder.() -> Unit
    ): ApiResult<RefreshTokenResponse> =
        authHttpClient.get(path = REFRESH_TOKEN, block = {
            header("Authorization", "Bearer $token")
            block(this)
        })

    override suspend fun getStadiumData(): ApiResult<String> =
        authHttpClient.get(ApiEndpoints.STADIUM_DATA)

    override suspend fun getStyleRecommendation(request: StyleRequest): RawApiResult<StyleResponse> =
        noAuthHttpClient.post(
            path = styleBackendUrl(ApiEndpoints.STYLE_RECOMMENDATION),
            body = request,
            block = {
                timeout {
                    requestTimeoutMillis = 60.seconds.inWholeMilliseconds
                }
            },
        )

    override suspend fun checkStyleBackendHealth(): RawApiResult<String> =
        noAuthHttpClient.getRawText(
            path = styleBackendUrl(ApiEndpoints.STYLE_HEALTH),
            block = {
                timeout {
                    requestTimeoutMillis = 15.seconds.inWholeMilliseconds
                }
            }
        )

    private fun styleBackendUrl(path: String): String = "$styleBackendBaseUrl/$path"

    companion object {
        operator fun invoke(
            authHttpClient: HttpClient,
            noAuthHttpClient: HttpClient,
            styleBackendBaseUrl: String = defaultStyleApiBaseUrl(),
        ) = with(authHttpClient) {
            StyleAiRemoteApiImpl(
                authHttpClient = authHttpClient,
                noAuthHttpClient = noAuthHttpClient,
                styleBackendBaseUrl = styleBackendBaseUrl,
            )
        }
    }
}
