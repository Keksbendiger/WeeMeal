package de.fhe.ai.weemeal.mocks.local

import de.fhe.ai.weemeal.domain.models.Tag
import de.fhe.ai.weemeal.local.entity.TagEntity
import de.fhe.ai.weemeal.local.mapper.recipe.fromDomain
import io.bloco.faker.Faker

object TagEntityMock {
    private val faker: Faker = Faker()

    fun generateSingleObject(
        id: Long = 0,
        name: String = faker.lorem.word()
    ): TagEntity {
        return TagEntity(id = id, name = name)
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<TagEntity> {

        val internalList: MutableList<TagEntity> = mutableListOf()
        Tag.generateDefaultTagList().forEach() {
            internalList.add(it.fromDomain())
        }
        for (i in 1..amount!!) {
            internalList.add(generateSingleObject())
        }
        return internalList
    }
}
