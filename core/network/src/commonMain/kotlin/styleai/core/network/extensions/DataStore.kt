package styleai.core.network.extensions

import io.ktor.client.plugins.auth.providers.BearerTokens
import styleai.core.datastore.DataStore

internal fun DataStore.getBearerTokens(): BearerTokens? {
    return BearerTokens(
        accessToken = accessToken ?: return null,
        refreshToken = refreshToken ?: return null,
    )
}

internal fun DataStore.saveBearerTokens(bearerTokens: BearerTokens?) {
    accessToken = bearerTokens?.accessToken
    refreshToken = bearerTokens?.refreshToken
}
