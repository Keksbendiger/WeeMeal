package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel

data class Tag(
    override var internalId: Long = 0,
    val name: String,
    val isDefaultValue: Boolean = false
) : BaseModel {

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
