package de.fhe.ai.weemeal.domain.formats

data class TimeFormat(
    var value: Float,
    var unit: String
//    val unit: TimeUnit
) {
    override fun toString(): String {
        return "$value $unit"
    }
    fun toTypeConvertString(): String {
        return "$value;$unit"
    }
}
