package de.fhe.ai.weemeal.local.mapper

import androidx.room.TypeConverter
import de.fhe.ai.weemeal.domain.formats.QuantityFormat
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import java.util.Date

/**
 * Converters for specific types in the database.
 */
class Converters {
    @TypeConverter
    fun quantityFormatToString(value: String?): QuantityFormat? {
        return value?.let {
            QuantityFormat(it.split(";")[0].toFloat(), it.split(";")[1])
        }
    }

    @TypeConverter
    fun stringToQuantityFormat(quantityFormat: QuantityFormat?): String? {
        return quantityFormat?.toTypeConvertString()
    }

    @TypeConverter
    fun timeFormatToString(value: String?): TimeFormat? {
        return value?.let {
            TimeFormat(it.split(";")[0].toFloat(), it.split(";")[1])
        }
    }

    @TypeConverter
    fun stringToTimeFormat(timeFormat: TimeFormat?): String? {
        return timeFormat?.toTypeConvertString()
    }

    @TypeConverter
    fun longToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
