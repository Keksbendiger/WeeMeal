package de.fhe.ai.weemeal.common.formats

import java.security.SecureRandom

enum class TimeUnit(val value: String) {
    NONE(""),
    MINUTES("min"),
    HOURS("h"),
    DAYS("day(s)");

    companion object {
        fun getRandom(): TimeUnit {
            return TimeUnit.values()[SecureRandom().nextInt(TimeUnit.values().size)]
        }
    }
}
