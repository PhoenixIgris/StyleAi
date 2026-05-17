package styleai.core.data.repository

import kotlinx.coroutines.delay
import styleai.core.models.style.StyleRequest
import styleai.core.models.style.StyleResponse

class FakeStyleRepository : StyleRepository {
    override suspend fun generateRecommendation(request: StyleRequest): Result<StyleResponse> {
        delay(1_500)

        return Result.success(
            StyleResponse(
                summary = buildSummary(request),
                outfitIdea = buildOutfitIdea(request),
                colorsToTry = listOf(
                    "Cream",
                    "Champagne",
                    "Soft blush",
                    "Warm beige",
                    "Muted gold",
                ),
                colorsToAvoid = listOf(
                    "Very harsh neon tones",
                    "Overly cool gray if you prefer warmth",
                ),
                fitTips = listOf(
                    "Choose ${request.fitPreference.lowercase()} pieces that define your waist without feeling tight.",
                    "Use vertical lines or a long outer layer to create an elongated silhouette.",
                    "Balance soft fabrics with one structured piece.",
                ),
                accessories = listOf(
                    "Minimal gold earrings",
                    "Structured small handbag",
                    "Delicate bracelet or ring",
                ),
                footwear = "Nude, beige, or blush heels can create a clean and elongated look.",
                hairAndGrooming = "Soft waves, a clean bun, or natural polished styling will match the elegant look.",
                budgetTips = buildBudgetTips(request),
                confidenceBoost = "Your best look is the one that feels comfortable, polished, and true to your personality.",
            ),
        )
    }

    override suspend fun checkBackendHealth(): Result<String> {
        return Result.success("Fake StyleAI backend is available.")
    }

    private fun buildSummary(request: StyleRequest): String {
        val occasion = request.occasion.lowercase()
        val style = request.preferredStyle.lowercase()
        return "Soft $style looks for $occasion in warm neutrals with polished accessories."
    }

    private fun buildOutfitIdea(request: StyleRequest): String {
        val seasonNote = if (request.season.isNotBlank()) {
            " Keep the fabric weight comfortable for ${request.season.lowercase()}."
        } else {
            ""
        }
        val notes = if (request.notes.isNotBlank()) {
            " Since you mentioned \"${request.notes}\", keep that as a styling priority."
        } else {
            ""
        }

        return "Try a flowy blush or champagne dress layered with a structured beige blazer. " +
            "Add warm gold details for a refined ${request.occasion.lowercase()} finish." +
            seasonNote +
            notes
    }

    private fun buildBudgetTips(request: StyleRequest): List<String> {
        val budgetLead = when (request.budget) {
            "Budget-friendly" -> "Start with one affordable neutral blazer that can be reworn often."
            "Mid-range" -> "Invest in one well-made neutral blazer."
            "Premium" -> "Choose one premium tailored layer in a timeless neutral."
            else -> "Invest in one reusable neutral blazer."
        }

        return listOf(
            budgetLead,
            "Use accessories to make simple outfits feel elevated.",
            "Choose versatile shoes that can work across multiple occasions.",
        )
    }
}
