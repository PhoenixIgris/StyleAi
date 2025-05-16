package styleai.core.network

import com.github.kittinunf.result.getOrNull
import com.github.kittinunf.result.map
import com.github.kittinunf.result.onFailure
import com.github.kittinunf.result.onSuccess
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.HttpRequestBuilder
import styleai.core.analytics.Log
import styleai.core.datastore.DataStore
import styleai.core.network.extensions.saveBearerTokens

internal class BearerTokenRefresher(
    private val dataStore: DataStore,
    private val remoteApi: StyleAiRemoteApi,
) {
    suspend fun refreshToken(block: HttpRequestBuilder.() -> Unit): BearerTokens? {
        val refreshToken = dataStore.refreshToken ?: return null

        Log.info("Refreshing tokens")
        return remoteApi.refreshToken(refreshToken, block)
            .map {
                it.data?.token?.let { token ->
                    BearerTokens(
                        token.accessToken,
                        token.refreshToken
                    )
                }
            }
            .onSuccess {
                Log.info("Tokens rotated!!, saving them to store")
                dataStore.saveBearerTokens(it)
            }
            .onFailure {
                Log.error(it, "Unable to rotate token.")
                dataStore.isLoggedIn = false
            }.getOrNull()
    }
}
