package de.fhe.ai.weemeal.domain.formats

import de.fhe.ai.weemeal.domain.enums.TimeUnit

data class TimeFormat(
    val value: Float,
    val unit: String
//    val unit: TimeUnit
) {
    override fun toString(): String {
        return "$value $unit"
    }
    fun toTypeConvertString(): String {
        return "$value;$unit"
    }
}
