package styleai.core.data.repository

import styleai.core.models.style.StyleRequest
import styleai.core.models.style.StyleResponse

interface StyleRepository {
    suspend fun generateRecommendation(request: StyleRequest): Result<StyleResponse>

    suspend fun checkBackendHealth(): Result<String>
}
