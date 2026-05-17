# StyleAI Vector Assets

This package contains clean SVG vector assets for the StyleAI mobile app UI. They are named for easy use in Kotlin Multiplatform / Compose Multiplatform and for AI coding assistants such as Claude, Codex, or ChatGPT.

## Brand colors

| Token | Hex |
|---|---|
| Cream background | `#FFF7EE` |
| Card background | `#FFFDF8` |
| Blush pink primary | `#F7D6DA` |
| Soft rose accent | `#E7A09A` |
| Light beige | `#F5EDE3` |
| Beige | `#F1E5D6` |
| Warm dark brown text | `#6B4A3A` |
| Muted gold accent | `#E0B26B` |

## Suggested Kotlin package names

Use this resource structure:

```text
StyleAi/core/resources/src/commonMain/composeResources/drawable
```

Copy all `.svg` files from `icons/`, `illustrations/`, `decorative/`, and `ui_tokens/` into the `drawable` folder.

Recommended naming style is already Android/Compose friendly:

```text
ic_outfit_hanger.svg
ic_color_palette.svg
ic_face_shape.svg
ic_body_measurement.svg
ic_occasion_calendar_star.svg
ic_budget_price_tag.svg
ic_accessories_earrings.svg
ic_shoes_footwear.svg
ic_ai_sparkle.svg
ic_questionnaire_checklist.svg
ic_edit_pencil.svg
ic_generate_refresh_sparkle.svg
```

## Main 12 icons

1. `ic_outfit_hanger.svg`
2. `ic_color_palette.svg`
3. `ic_face_shape.svg`
4. `ic_body_measurement.svg`
5. `ic_occasion_calendar_star.svg`
6. `ic_budget_price_tag.svg`
7. `ic_accessories_earrings.svg`
8. `ic_shoes_footwear.svg`
9. `ic_ai_sparkle.svg`
10. `ic_questionnaire_checklist.svg`
11. `ic_edit_pencil.svg`
12. `ic_generate_refresh_sparkle.svg`

## Extra app UI icons

These support the questionnaire and recommendation screens:

```text
ic_work_briefcase.svg
ic_wedding_ring.svg
ic_date_heart.svg
ic_party_discoball.svg
ic_interview_person.svg
ic_handbag.svg
ic_piggy_bank.svg
ic_magic_wand.svg
```

## Illustrations

```text
ill_welcome_outfit_scene.svg
ill_ai_loading_cards.svg
```

Use `ill_welcome_outfit_scene.svg` on the Welcome screen.
Use `ill_ai_loading_cards.svg` on the Loading screen.

## Decorative assets

```text
deco_sparkles.svg
deco_soft_blobs.svg
```

Use these as optional background decoration behind cards or headers.

## UI token assets

```text
logo_styleai_wordmark.svg
color_palette_styleai.svg
```

## Compose Multiplatform usage example

```kotlin
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource
import styleai.generated.resources.Res
import styleai.generated.resources.ic_ai_sparkle

@Composable
fun AiSparkleIcon() {
    Image(
        painter = painterResource(Res.drawable.ic_ai_sparkle),
        contentDescription = "AI sparkle"
    )
}
```

## Claude/Codex prompt to use these assets

Paste this into Claude or Codex after adding the SVG files:

```text
Use the SVG assets in StyleAi/core/resources/src/commonMain/composeResources/drawable for the StyleAI app.

Build the UI in Kotlin Compose Multiplatform using these resources:
- Welcome illustration: ill_welcome_outfit_scene.svg
- Loading illustration: ill_ai_loading_cards.svg
- Questionnaire icons: ic_outfit_hanger.svg, ic_work_briefcase.svg, ic_wedding_ring.svg, ic_date_heart.svg, ic_party_discoball.svg, ic_interview_person.svg
- Review screen icons: ic_face_shape.svg, ic_body_measurement.svg, ic_color_palette.svg, ic_budget_price_tag.svg, ic_occasion_calendar_star.svg, ic_questionnaire_checklist.svg
- Result icons: ic_outfit_hanger.svg, ic_color_palette.svg, ic_body_measurement.svg, ic_accessories_earrings.svg, ic_shoes_footwear.svg, ic_piggy_bank.svg, ic_generate_refresh_sparkle.svg

Design tokens:
- Background: #FFF7EE
- Card: #FFFDF8
- Primary blush: #F7D6DA
- Accent gold: #E0B26B
- Text brown: #6B4A3A
- Radius: 24.dp for cards/buttons
- Button height: 56.dp
- Spacing scale: 8.dp, 16.dp, 24.dp
- Use soft shadows and rounded cards.

Create five screens:
WelcomeScreen, QuestionnaireScreen, ReviewStyleProfileScreen, LoadingStyleGuideScreen, RecommendationResultScreen.
```

## Notes

- These SVGs are vector assets and can be resized without quality loss.
- They use a consistent warm pastel line style matching the StyleAI mockups.
- All filenames use lowercase snake_case so they are safe for Android and Compose resources.
