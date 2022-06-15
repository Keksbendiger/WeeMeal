package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.models.Tag
import io.bloco.faker.Faker

object TagMock {
    private val faker: Faker = Faker()
    fun generateSingleObject(): Tag {
        return Tag(name = faker.lorem.word())
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Tag> {

        val internalList: MutableList<Tag> = mutableListOf()
        Tag.generateDefaultTagList().forEach() {
            internalList.add(it)
        }
        for (i in 1..amount!!) {
            internalList.add(generateSingleObject())
        }
        return internalList
    }
}
