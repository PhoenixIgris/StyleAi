package styleai.core.models.style

import kotlinx.serialization.Serializable

@Serializable
data class StyleRequest(
    val genderStyleCategory: String,
    val occasion: String,
    val preferredStyle: String,
    val skinTone: String,
    val faceShape: String,
    val heightRange: String,
    val fitPreference: String,
    val comfortLevel: String,
    val budget: String,
    val season: String,
    val notes: String = "",
)
