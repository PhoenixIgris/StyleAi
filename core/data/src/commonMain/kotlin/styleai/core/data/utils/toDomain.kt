package styleai.core.data.utils

import styleai.core.models.local.StadiumEntity

fun String.toDomain(): StadiumEntity {
    return StadiumEntity(
        id = "1",
        imageUrl = this
    )
}
