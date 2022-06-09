package de.fhe.ai.weemeal.common.extentions

import de.fhe.ai.weemeal.common.formats.MeasurementUnit
import de.fhe.ai.weemeal.domain.enums.TimeUnit
import io.bloco.faker.Faker

fun Faker.measurementUnit(): String {
    return MeasurementUnit.getRandom().value
}

fun Faker.timeUnit(): String {
    return TimeUnit.getRandom().value
}
