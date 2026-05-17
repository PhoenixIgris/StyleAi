package styleai.core.models.style

import kotlinx.serialization.Serializable

@Serializable
data class StyleQuestionnaireState(
    val genderStyleCategory: String = "",
    val occasion: String = "",
    val preferredStyle: String = "",
    val skinTone: String = "",
    val faceShape: String = "",
    val heightRange: String = "",
    val fitPreference: String = "",
    val comfortLevel: String = "",
    val budget: String = "",
    val season: String = "",
    val notes: String = "",
    val currentQuestionIndex: Int = 0,
) {
    val isComplete: Boolean
        get() = genderStyleCategory.isNotBlank() &&
            occasion.isNotBlank() &&
            preferredStyle.isNotBlank() &&
            skinTone.isNotBlank() &&
            faceShape.isNotBlank() &&
            heightRange.isNotBlank() &&
            fitPreference.isNotBlank() &&
            comfortLevel.isNotBlank() &&
            budget.isNotBlank() &&
            season.isNotBlank()

    fun toRequest() = StyleRequest(
        genderStyleCategory = genderStyleCategory,
        occasion = occasion,
        preferredStyle = preferredStyle,
        skinTone = skinTone,
        faceShape = faceShape,
        heightRange = heightRange,
        fitPreference = fitPreference,
        comfortLevel = comfortLevel,
        budget = budget,
        season = season,
        notes = notes,
    )
}
