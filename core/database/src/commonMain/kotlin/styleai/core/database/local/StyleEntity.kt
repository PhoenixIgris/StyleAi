package styleai.core.database.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class StyleEntity(
    @PrimaryKey(autoGenerate = false) @SerialName("id") val id: String,
    @SerialName("value") val value: String,
)
