package de.fhe.ai.weemeal.common.extentions

import android.view.View
import android.view.ViewGroup
import de.fhe.ai.weemeal.common.formats.MeasurementUnit
import io.bloco.faker.Faker


fun Faker.measurementUnit(): String{
    return MeasurementUnit.getRandom().value
}

fun View.setMarginStart(margin: Int) {
    val layoutParams = layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginStart = margin
}