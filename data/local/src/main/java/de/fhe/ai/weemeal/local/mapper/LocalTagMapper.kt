package de.fhe.ai.weemeal.local.mapper.recipe

import de.fhe.ai.weemeal.domain.models.Tag
import de.fhe.ai.weemeal.local.entity.TagEntity

// TODO: find a better solution for this
fun TagEntity.toDomain() = Tag(
    name = this.name,
)

fun Tag.fromDomain() = TagEntity(
    name = this.name
)
