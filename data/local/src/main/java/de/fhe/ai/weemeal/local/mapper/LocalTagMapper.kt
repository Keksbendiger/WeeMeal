package de.fhe.ai.weemeal.local.mapper

import de.fhe.ai.weemeal.domain.models.Tag
import de.fhe.ai.weemeal.local.entity.TagEntity

fun TagEntity.toDomain() = Tag(
    internalId = this.id,
    name = this.name,
    isDefaultValue = this.isDefaultValue
)

fun Tag.fromDomain() = TagEntity(
    id = this.internalId,
    name = this.name,
    isDefaultValue = this.isDefaultValue
)
