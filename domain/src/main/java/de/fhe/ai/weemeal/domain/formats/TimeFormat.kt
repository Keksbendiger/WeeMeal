package de.fhe.ai.weemeal.domain.formats

data class TimeFormat(
    val value: Float,
    val unit: TimeUnit
) {
    override fun toString(): String {
        return "$value ${unit.value}"
    }
}
