package styleai.core.app

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import styleai.core.common.isDebugBuild


fun onStyleAiStarted() {
    if (isDebugBuild()) {
        Napier.base(DebugAntilog())
    }
}
