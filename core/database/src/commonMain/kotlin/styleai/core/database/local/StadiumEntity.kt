package styleai.core.database.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class StadiumEntity(
    @PrimaryKey
    val id: String,
    var localImagePath: String? = null,
    val imageUrl: String,
)