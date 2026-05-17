package styleai.core.data.repository

import styleai.core.models.style.StyleRequest
import styleai.core.models.style.StyleResponse
import styleai.core.network.StyleAiRemoteApi

class RemoteStyleRepository(
    private val remoteApi: StyleAiRemoteApi,
) : StyleRepository {

    override suspend fun generateRecommendation(request: StyleRequest): Result<StyleResponse> {
        return remoteApi.getStyleRecommendation(request).fold(
            success = { response -> Result.success(response) },
            failure = { failure -> Result.failure(Exception(failure.message)) },
        )
    }

    override suspend fun checkBackendHealth(): Result<String> {
        return remoteApi.checkStyleBackendHealth().fold(
            success = { message -> Result.success(message) },
            failure = { failure -> Result.failure(Exception(failure.message)) },
        )
    }
}
