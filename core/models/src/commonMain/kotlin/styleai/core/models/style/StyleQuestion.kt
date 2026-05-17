package styleai.core.models.style

import kotlinx.serialization.Serializable

@Serializable
data class StyleQuestion(
    val id: String,
    val title: String,
    val options: List<String>,
)

object StyleQuestions {
    val all = listOf(
        StyleQuestion(
            id = "genderStyleCategory",
            title = "What style category fits you best?",
            options = listOf(
                "Woman / Feminine",
                "Man / Masculine",
                "Neutral / Androgynous",
                "Prefer not to say",
            ),
        ),
        StyleQuestion(
            id = "occasion",
            title = "What occasion are you styling for?",
            options = listOf(
                "Casual",
                "Work",
                "Wedding Guest",
                "Date Night",
                "Party",
                "Interview",
            ),
        ),
        StyleQuestion(
            id = "preferredStyle",
            title = "What style do you prefer?",
            options = listOf(
                "Minimal",
                "Classic",
                "Elegant",
                "Trendy",
                "Streetwear",
                "Traditional / Fusion",
            ),
        ),
        StyleQuestion(
            id = "skinTone",
            title = "What color palette feels closest to you?",
            options = listOf(
                "Warm",
                "Cool",
                "Neutral",
                "Deep / Rich",
                "Soft / Muted",
                "Not sure",
            ),
        ),
        StyleQuestion(
            id = "faceShape",
            title = "What is your face shape?",
            options = listOf(
                "Oval",
                "Round",
                "Square",
                "Heart",
                "Diamond",
                "Not sure",
            ),
        ),
        StyleQuestion(
            id = "heightRange",
            title = "What is your height range?",
            options = listOf(
                "Under 5'2\"",
                "5'2\" to 5'5\"",
                "5'6\" to 5'9\"",
                "5'10\" and above",
            ),
        ),
        StyleQuestion(
            id = "fitPreference",
            title = "What fit do you feel best in?",
            options = listOf(
                "Tailored Comfort",
                "Relaxed Fit",
                "Waist Defining",
                "Height Elongating",
                "Modest Coverage",
                "Not sure",
            ),
        ),
        StyleQuestion(
            id = "comfortLevel",
            title = "How bold should your look be?",
            options = listOf(
                "Very Comfortable",
                "Balanced",
                "Fashion-forward",
                "Experimental",
            ),
        ),
        StyleQuestion(
            id = "budget",
            title = "What is your budget?",
            options = listOf(
                "Budget-friendly",
                "Mid-range",
                "Premium",
                "No strict budget",
            ),
        ),
        StyleQuestion(
            id = "season",
            title = "What season or climate is this for?",
            options = listOf(
                "Spring",
                "Summer",
                "Fall",
                "Winter",
                "Rainy",
                "Indoor Event",
            ),
        ),
    )
}
