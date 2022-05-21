package de.fhe.ai.weemeal.common.formats

data class TimeFormat(
    val value: Float,
    val unit: String
) {
    override fun toString(): String {
        return "$value $unit"
    }
}
