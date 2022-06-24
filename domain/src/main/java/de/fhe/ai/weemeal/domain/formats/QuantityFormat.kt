package de.fhe.ai.weemeal.domain.formats

data class QuantityFormat(
    val quantity: Float,
    var unit: String
) {
    fun toTypeConvertString(): String {
        return "$quantity;$unit"
    }
}
