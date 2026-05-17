# StyleAI Mobile

**StyleAI Mobile** is a Kotlin Multiplatform mobile app that provides personalized styling recommendations based on a short user questionnaire. The app collects style preferences, sends them to a Spring Boot backend, and displays an AI-generated styling guide in a clean, mobile-friendly interface.

This project is built as a **portfolio MVP** to demonstrate Kotlin Multiplatform development, Compose Multiplatform UI, API integration, state management, and connection to a local AI-powered backend.

> The mobile app does not call OpenAI, Gemini, Claude, or Ollama directly. It communicates only with the Spring Boot backend, which handles the local AI agent logic.

---

## Screenshots

| Welcome | Questionnaire | Review |
| --- | --- | --- |
| ![Welcome screen](docs/screenshots/welcome.png) | ![Questionnaire screen](docs/screenshots/questionaire.png) | ![Review screen](docs/screenshots/review.png) |

| Loading | Result |
| --- | --- |
| ![Loading screen](docs/screenshots/loading.png) | ![Result screen](docs/screenshots/result.png) |


## Demo Video

[Watch the StyleAI demo video](https://drive.google.com/file/d/1QbK6uNv6gdTQWv64fT-TF3AFeyL9jkvC/view?usp=sharing)


## Project Purpose

The goal of StyleAI is to show how a mobile app can integrate with an AI system in a realistic way.

The mobile app focuses on:

- Collecting structured user preferences
- Creating a clean questionnaire flow
- Sending user answers to a backend API
- Handling loading, success, and error states
- Displaying a structured AI recommendation response
- Keeping fake repository support for previews and testing

The AI logic is handled by a separate Spring Boot backend using a local AI agent.

---

## Key Features

- Kotlin Multiplatform mobile app
- Compose Multiplatform UI
- Questionnaire-based style profile creation
- Backend API integration using Ktor Client
- Structured recommendation result screen
- Fake repository for previews and testing
- Remote repository for real backend responses
- Loading and error handling
- No authentication in version 1
- No local database in version 1
- No external AI API calls from the mobile app

---

## How the App Works

```text
User opens app
    ↓
Answers styling questionnaire
    ↓
App builds StyleRequest
    ↓
RemoteStyleRepository sends request using Ktor Client
    ↓
Spring Boot backend receives request
    ↓
Backend local StyleAI Agent generates recommendation
    ↓
Backend returns StyleResponse JSON
    ↓
Mobile app displays recommendation cards
```

---

## Full Architecture

```text
KMP Mobile UI
    ↓
Questionnaire State
    ↓
StyleRequest Mapper
    ↓
StyleRepository
    ↓
RemoteStyleRepository
    ↓
Ktor Client
    ↓
Spring Boot API
    ↓
StyleAI Local Agent
    ↓
Rule-Based Tools + Styling Knowledge Base
    ↓
Ollama Local LLM
    ↓
Structured JSON Response
    ↓
Result Screen
```

---

## Mobile Tech Stack

- Kotlin Multiplatform
- Compose Multiplatform
- Kotlin Coroutines
- Ktor Client
- kotlinx.serialization
- Repository pattern
- Shared models
- State-based UI handling

---

## Backend Integration

The mobile app connects to the backend endpoint:

```http
POST /api/style/recommend
```

The backend is expected to run on port `8085`.

### Android Emulator URL

Use this URL when running the mobile app on an Android emulator:

```text
http://10.0.2.2:8085
```

### Physical Android Device URL

Use your laptop's Wi-Fi IP address:

```text
http://YOUR_LAPTOP_WIFI_IP:8085
```

Example:

```text
http://192.168.1.12:8085
```

### Desktop or iOS Simulator URL

```text
http://localhost:8085
```

---

## API Request Model

```json
{
  "genderStyleCategory": "Woman",
  "occasion": "Wedding Guest",
  "preferredStyle": "Elegant",
  "skinTone": "Warm",
  "faceShape": "Oval",
  "heightRange": "5'2\" to 5'6\"",
  "fitPreference": "I prefer clothes that elongate height",
  "comfortLevel": "Balanced",
  "budget": "Medium",
  "season": "Summer",
  "notes": "I like pink and gold jewelry."
}
```

---

## API Response Model

```json
{
  "summary": "Elegant summer wedding guest look with warm colors and comfortable styling.",
  "outfitIdea": "Choose a flowy midi dress or refined co-ord set in peach, coral, or warm pink with gold accents.",
  "colorsToTry": ["Peach", "Coral", "Warm pink", "Cream", "Gold"],
  "colorsToAvoid": ["Icy gray", "Cool neon tones"],
  "fitTips": [
    "Try a high-waisted silhouette to visually elongate your frame.",
    "Use vertical details such as a long layer or clean front seam."
  ],
  "accessories": ["Gold earrings", "Soft clutch", "Delicate bracelet"],
  "footwear": "Comfortable block heels or embellished sandals would work well.",
  "hairAndGrooming": "Soft waves or a low bun would keep the look elegant and practical.",
  "budgetTips": [
    "Invest in the main outfit piece and reuse simple gold accessories.",
    "Choose neutral footwear that can be worn again."
  ],
  "confidenceBoost": "This look balances elegance, comfort, and warmth while staying practical for the occasion."
}
```

---

## Questionnaire Fields

The app collects these user inputs:

- Gender or style category
- Occasion
- Preferred style
- Skin tone or color palette
- Face shape
- Height range
- Fit preference
- Comfort level
- Budget
- Season or climate
- Optional notes

These answers are converted into a `StyleRequest` and sent to the backend.

---

## Suggested Project Structure

```text
styleai-mobile/
  composeApp/
    src/
      commonMain/
        kotlin/
          data/
            StyleApiClient.kt
            StyleRepository.kt
            FakeStyleRepository.kt
            RemoteStyleRepository.kt
          model/
            StyleRequest.kt
            StyleResponse.kt
            QuestionnaireState.kt
          ui/
            components/
              StyleButton.kt
              StyleCard.kt
              ChoiceChip.kt
              ResultInfoCard.kt
            screens/
              WelcomeScreen.kt
              QuestionnaireScreen.kt
              ReviewAnswersScreen.kt
              LoadingScreen.kt
              ResultScreen.kt
              ErrorScreen.kt
            theme/
              StyleTheme.kt
          state/
            StyleViewModel.kt
          navigation/
            AppNavigation.kt
```

---

## Repository Pattern

The app uses a repository layer so the UI does not directly depend on the network client.

```text
StyleRepository
    ↓
FakeStyleRepository      Used for previews and testing
RemoteStyleRepository    Used for real backend API calls
```

This makes it easy to test the UI without running the backend.

---

## UI State Flow

The app should handle these states:

```kotlin
sealed interface StyleUiState {
    data object Idle : StyleUiState
    data object Loading : StyleUiState
    data class Success(val response: StyleResponse) : StyleUiState
    data class Error(val message: String) : StyleUiState
}
```

Expected flow:

```text
Idle
  ↓
Loading
  ↓
Success or Error
```

---

## Running the Backend

Before running the mobile app with real AI responses, start the Spring Boot backend.

```bash
./gradlew bootRun
```

Health check:

```bash
curl http://localhost:8085/api/health
```

Recommendation endpoint:

```bash
curl -X POST http://localhost:8085/api/style/recommend \
  -H "Content-Type: application/json" \
  -d '{
    "genderStyleCategory": "Woman",
    "occasion": "Wedding Guest",
    "preferredStyle": "Elegant",
    "skinTone": "Warm",
    "faceShape": "Oval",
    "heightRange": "5'\''2\" to 5'\''6\"",
    "fitPreference": "I prefer clothes that elongate height",
    "comfortLevel": "Balanced",
    "budget": "Medium",
    "season": "Summer",
    "notes": "I like pink and gold jewelry."
  }'
```

---

## Running the Mobile App

### Android Emulator

Use this backend URL:

```text
http://10.0.2.2:8085
```

Then run the Android target from Android Studio.

### Physical Android Device

1. Make sure the phone and laptop are on the same Wi-Fi network.
2. Find your laptop IP:

```bash
ipconfig getifaddr en0
```

3. Use this as the backend URL:

```text
http://YOUR_LAPTOP_WIFI_IP:8085
```

---

## Android Cleartext HTTP Note

Because the local backend uses `http` instead of `https`, Android may block the request.

Add this to the Android manifest if needed:

```xml
<application
    android:usesCleartextTraffic="true"
    ... >
</application>
```

---

## Common Connection Issues

### Android emulator cannot connect to backend

Do not use:

```text
http://localhost:8085
```

Use:

```text
http://10.0.2.2:8085
```

### Physical phone cannot connect

Check that:

- Phone and laptop are on the same Wi-Fi
- Backend is running
- Correct laptop IP is used
- Firewall is not blocking port `8085`
- Backend is listening on the network

### Error: Failed to connect to `/192.168.x.x:8085`

This usually means:

- Wrong IP address
- Backend is not running
- Phone is not on same network
- Docker or firewall is blocking access

### Error: Local AI model is not available

This is a backend/Ollama issue, not a mobile app issue. Make sure Ollama is running and the backend can reach it.

---

## AI System Note

The AI logic is intentionally not inside the mobile app.

The backend handles:

- Style profile analysis
- Rule-based styling tools
- Local knowledge base lookup
- Prompt construction
- Ollama local LLM generation
- JSON schema validation
- Fallback recommendation generation

This separation keeps the mobile app clean and realistic.

---

## What I Learned

Through this project, I practiced:

- Kotlin Multiplatform project structure
- Compose Multiplatform UI design
- Repository pattern in shared code
- Ktor Client API integration
- kotlinx.serialization models
- State-driven UI rendering
- Connecting a mobile app to a local AI backend
- Handling emulator and physical device networking issues
- Separating mobile UI from backend AI logic

---

## Future Improvements

- Add user authentication
- Save recommendation history
- Add image-based color analysis
- Add wardrobe upload
- Add style archetype classification
- Add offline cached recommendations
- Add iOS polish
- Add backend deployment option
- Add shopping inspiration links
- Add better prompt/result evaluation

---

## Portfolio Highlights

This project demonstrates:

- Kotlin Multiplatform mobile development
- Compose Multiplatform UI implementation
- Real backend API integration
- Clean architecture with repository pattern
- Local AI system integration through backend
- Structured AI response rendering
- Practical MVP product thinking

---

## Disclaimer

StyleAI recommendations are generated for styling inspiration only. This is a portfolio MVP and not a production-grade fashion recommendation system.

---

## License

Add your preferred license here.

Example:

```text
MIT License
```
