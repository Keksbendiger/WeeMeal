package de.fhe.ai.weemeal.local.mapper

import de.fhe.ai.weemeal.domain.models.Tag
import de.fhe.ai.weemeal.local.entity.TagEntity

/**
 * Mapper for TagEntity and Tag
 */
fun TagEntity.toDomain() = Tag(
    internalId = this.id,
    name = this.name,
    isDefaultValue = this.isDefaultValue
)

/**
 * Mapper for Tag and TagEntity
 */
fun Tag.fromDomain() = TagEntity(
    id = this.internalId,
    name = this.name,
    isDefaultValue = this.isDefaultValue
)
