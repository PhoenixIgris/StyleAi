package styleai.core.models.style

import kotlinx.serialization.Serializable

@Serializable
data class StyleResponse(
    val summary: String,
    val outfitIdea: String,
    val colorsToTry: List<String>,
    val colorsToAvoid: List<String>,
    val fitTips: List<String>,
    val accessories: List<String>,
    val footwear: String,
    val hairAndGrooming: String,
    val budgetTips: List<String>,
    val confidenceBoost: String,
)

