package de.fhe.ai.weemeal.domain.formats

data class TimeFormat(
    val value: Float,
    val unit: String
) {
    override fun toString(): String {
        return "$value $unit"
    }
}
