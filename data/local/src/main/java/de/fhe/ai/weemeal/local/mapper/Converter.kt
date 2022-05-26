//package de.fhe.ai.weemeal.local.mapper
//
//import androidx.room.TypeConverter
//import de.fhe.ai.weemeal.domain.formats.TimeFormat
//
//class Converters {
//    @TypeConverter
//    fun fromTimestamp(value: String?): TimeFormat? {
//        return value?.let { TimeFormat(it.val) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: TimeFormat?): String? {
//        return date?.time?.toLong()
//    }
//}