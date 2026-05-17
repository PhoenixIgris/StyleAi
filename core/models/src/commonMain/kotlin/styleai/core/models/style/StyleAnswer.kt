package styleai.core.models.style

import kotlinx.serialization.Serializable

@Serializable
data class StyleAnswer(
    val questionId: String,
    val value: String,
)
