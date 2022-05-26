package de.fhe.ai.weemeal.domain.models

data class Tag(
    val name: String,
    val isDefaultValue: Boolean = false
) {

    companion object {
        fun generateDefaultTagList(): List<Tag> {
            return listOf(
                Tag(name = "Frühstück", isDefaultValue = true),
                Tag(name = "Mittag", isDefaultValue = true),
                Tag(name = "Abendbrot", isDefaultValue = true),
            )
        }
    }
}
