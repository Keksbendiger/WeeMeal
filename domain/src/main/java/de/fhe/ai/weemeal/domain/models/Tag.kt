package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel

data class Tag(
    override var internalId: Long = 0,
    val name: String,
    val isDefaultValue: Boolean = false
) : BaseModel {

    fun equalsWithoutId(other: Any?): Boolean {
        return (
            (other is Tag) &&
                other.name == this.name &&
                other.isDefaultValue == this.isDefaultValue
            )
    }

    companion object {
        fun generateDefaultTagList(): List<Tag> {
            return listOf(
                Tag(name = "Frühstück", isDefaultValue = true),
                Tag(name = "Mittag", isDefaultValue = true),
                Tag(name = "Abendbrot", isDefaultValue = true),
            )
        }

        fun List<Tag>.areTagListsEqualWithoutId(other: List<Tag>?): Boolean {
            this.forEachIndexed { index, thisItem ->
                if (!thisItem.equalsWithoutId(other?.get(index))) {
                    return false
                }
            }
            return true
        }
    }
}
