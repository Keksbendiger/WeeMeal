package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.models.Tag
import io.bloco.faker.Faker

object TagMock {
    private var faker: Faker = Faker()

    fun generateCustomTag(): Tag {
        return Tag(name = faker.lorem.word())
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Tag> {

        val recipeList: MutableList<Tag> = mutableListOf()
        Tag.generateDefaultTagList().forEach(){
            recipeList.add(it)
        }
        for (i in 1..amount!!) {
            recipeList.add(generateCustomTag())
        }
        return recipeList
    }
}