package util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


object TimeUtil {

    val MIN_DATE_TIME: LocalDateTime = LocalDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.MIN)
    val MAX_DATE_TIME: LocalDateTime = LocalDateTime.of(LocalDate.of(3000, 1, 1), LocalTime.MIN)

    /**
     * 时间是否处于 (MIN_DATE_TIME, MAX_DATE_TIME) 开区间
     *
     * @param time 传入时间
     * @return 是否有效
     */
    fun valid(time: LocalDateTime): Boolean {
        if (time == null) {
            return false
        }
        if (!MIN_DATE_TIME.isBefore(time)) {
            return false
        }
        return MAX_DATE_TIME.isAfter(time)
    }

    /**
     * 一般的日期格式 yyyy-MM-dd
     */
    val DATE_SIMPLE: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val DATE_MM_DD: DateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd")
    val DATE_YYYY_MM: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM")

    /**
     * 一般的时间格式 HH:mm:ss
     */
    val TIME_SIMPLE: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    val TIME_HOUR_MINUS: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val DATE_TIME_SIMPLE: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun formatDate(dateTime: LocalDateTime): String {
        return format(dateTime, DATE_SIMPLE)
    }

    fun formatDateMonthDay(dateTime: LocalDateTime): String {
        return format(dateTime, DATE_MM_DD)
    }

    fun formatDateYearMonth(dateTime: LocalDateTime): String {
        return format(dateTime, DATE_YYYY_MM)
    }

    fun formatTime(dateTime: LocalDateTime): String {
        return format(dateTime, TIME_SIMPLE)
    }

    fun formatTimeHourMinus(dateTime: LocalDateTime): String {
        return format(dateTime, TIME_HOUR_MINUS)
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        return format(dateTime, DATE_TIME_SIMPLE)
    }

    fun format(dateTime: LocalDateTime, dtf: DateTimeFormatter): String {
        return dtf.format(dateTime)
    }

    fun parse(date: String, time: String): LocalDateTime {
        val d = LocalDate.parse(date, DATE_SIMPLE)
        val t = LocalTime.parse(time, TIME_SIMPLE)
        return LocalDateTime.of(d, t)
    }

    fun parse(dateTime: String): LocalDateTime {
        return LocalDateTime.parse(dateTime, DATE_TIME_SIMPLE)
    }

    fun parse(timestamp: Long): LocalDateTime {
        // 转换为 LocalDateTime（使用系统默认时区）
        return Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    fun getLocalDateTimeMill(localDateTime: LocalDateTime): Long {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli()
    }
}
