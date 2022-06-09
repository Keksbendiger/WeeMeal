package de.fhe.ai.weemeal.domain.formats

data class QuantityFormat(
    val quantity: Float,
    val unit: String
) {
    fun toTypeConvertString(): String {
        return "$quantity;$unit"
    }
}
