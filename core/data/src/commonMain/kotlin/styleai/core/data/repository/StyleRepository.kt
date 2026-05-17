package styleai.core.data.repository

import styleai.core.models.style.StyleRecommendation
import styleai.core.models.style.StyleRequest

interface StyleRepository {
    suspend fun generateRecommendation(request: StyleRequest): Result<StyleRecommendation>
}
