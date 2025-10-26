package styleai.core.data.utils

import styleai.core.database.local.StadiumEntity

fun String.toDomain(): StadiumEntity {
    return StadiumEntity(
        id = "1",
        imageUrl = this
    )
}
