package de.fhe.ai.weemeal.common.extentions

import de.fhe.ai.weemeal.common.formats.MeasurementUnit
import de.fhe.ai.weemeal.domain.formats.TimeUnit
import io.bloco.faker.Faker

fun Faker.measurementUnit(): String {
    return MeasurementUnit.getRandom().value
}

fun Faker.timeUnit(): de.fhe.ai.weemeal.domain.formats.TimeUnit {
    return de.fhe.ai.weemeal.domain.formats.TimeUnit.getRandom()
}
