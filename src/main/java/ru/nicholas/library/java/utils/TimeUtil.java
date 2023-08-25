package ru.nicholas.library.java.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TimeUtil {

    public static String getTime(long seconds) {
        int minutes = 0;
        int hours = 0;
        int days = 0;
        int weeks = 0;
        int months = 0;
        int years = 0;
        if (seconds >= 60) {
            final long i = seconds / 60;
            seconds -= 60 * i;
            minutes += i;
        }
        if (minutes >= 60) {
            final int i = minutes / 60;
            minutes -= 60 * i;
            hours += i;
        }
        if (hours >= 24) {
            int i = hours / 24;
            hours -= 24 * i;
            days += i;
        }
        if (days >= 7) {
            int i = days / 7;
            days -= 7 * i;
            weeks += i;
        }
        if (weeks >= 4) {
            int i = weeks / 4;
            weeks -= 4 * i;
            months += i;
        }
        if (months >= 12) {
            final int i = months / 12;
            months -= 12 * i;
            years += i;
        }
        final StringBuilder builder = new StringBuilder();
        if (years != 0) {
            builder.append(getTimeUnitName(years, TimeUnit.YEARS)).append(" ");
        }
        if (months != 0) {
            builder.append(getTimeUnitName(months, TimeUnit.MONTHS)).append(" ");
        }
        if (weeks != 0) {
            builder.append(getTimeUnitName(weeks, TimeUnit.WEEKS)).append(" ");
        }
        if (days != 0) {
            builder.append(getTimeUnitName(days, TimeUnit.DAYS)).append(" ");
        }
        if (hours != 0) {
            builder.append(getTimeUnitName(hours, TimeUnit.HOURS)).append(" ");
        }
        if (minutes != 0) {
            builder.append(getTimeUnitName(minutes, TimeUnit.MINUTES)).append(" ");
        }
        if (seconds != 0) {
            builder.append(getTimeUnitName(seconds, TimeUnit.SECONDS));
        }
        return builder.toString();
    }

    public static long parseStringToTime(String str) {
        long time = 0;
        String[] array = str.split(" ");
        for (String element : array) {
            if (element.contains("s")) {
                long seconds = Long.parseLong(element.replace("s", ""));
                time += seconds;
            }
            if (element.contains("m")) {
                long minutes = Long.parseLong(element.replace("m", ""));
                time += minutes * 60;
            }

            if (element.contains("H")) {
                long hours = Long.parseLong(element.replace("H", ""));
                time += hours * 60 * 60;
            }

            if (element.contains("d")) {
                long days = Long.parseLong(element.replace("d", ""));
                time += days * 24 * 60 * 60;
            }

            if (element.contains("w")) {
                long weeks = Long.parseLong(element.replace("w", ""));
                time += weeks * 7 * 24 * 60 * 60;
            }

            if (element.contains("M")) {
                long months = Long.parseLong(element.replace("M", ""));
                time += months * (long) (30.44 * 24 * 60 * 60);
            }
            if (element.contains("y")) {
                long years = Long.parseLong(element.replace("y", ""));
                time += years * (long) (365.24 * 24 * 60 * 60);
            }
        }

        return time;
    }

    public static String formatting(int i, String one, String two, String three) {
        if (i % 100 > 10 && i % 100 < 15) {
            return i + " " + three;
        }
        switch (i % 10) {
            case 1: {
                return i + " " + one;
            }
            case 2:
            case 3:
            case 4: {
                return i + " " + two;
            }
            default: {
                return i + " " + three;
            }
        }
    }

    public static String getTimeAgo(LocalDateTime dateTime, long seconds) {
        LocalDateTime endDateTime = dateTime.plusSeconds(seconds);

        long years = ChronoUnit.YEARS.between(dateTime, endDateTime);
        dateTime = dateTime.plusYears(years);

        long months = ChronoUnit.MONTHS.between(dateTime, endDateTime);
        dateTime = dateTime.plusMonths(months);

        long days = ChronoUnit.DAYS.between(dateTime, endDateTime);
        dateTime = dateTime.plusDays(days);

        long hours = ChronoUnit.HOURS.between(dateTime, endDateTime);
        dateTime = dateTime.plusHours(hours);

        long minutes = ChronoUnit.MINUTES.between(dateTime, endDateTime);
        dateTime = dateTime.plusMinutes(minutes);

        seconds = ChronoUnit.SECONDS.between(dateTime, endDateTime);

        StringBuilder sb = new StringBuilder();
        if (years > 0) {
            sb.append(years).append(" ").append(getTimeUnitName(years, TimeUnit.YEARS)).append(" ");
        }
        if (months > 0 || sb.length() > 0) {
            sb.append(months).append(" ").append(getTimeUnitName(months, TimeUnit.MONTHS)).append(" ");
        }
        if (days > 0 || sb.length() > 0) {
            sb.append(days).append(" ").append(getTimeUnitName(days, TimeUnit.DAYS)).append(" ");
        }
        if (hours > 0 || sb.length() > 0) {
            sb.append(hours).append(" ").append(getTimeUnitName(hours, TimeUnit.HOURS)).append(" ");
        }
        if (minutes > 0 || sb.length() > 0) {
            sb.append(minutes).append(" ").append(getTimeUnitName(minutes, TimeUnit.MINUTES)).append(" ");
        }
        sb.append(seconds).append(" ").append(getTimeUnitName(seconds, TimeUnit.SECONDS));

        return sb.toString();
    }

    private static String getTimeUnitName(long value, TimeUnit timeUnit) {
        String unitName;
        if (value % 10 == 1 && value % 100 != 11) {
            unitName = timeUnit.getOne();
        } else if (value % 10 >= 2 && value % 10 <= 4 && (value % 100 < 10 || value % 100 >= 20)) {
            unitName = timeUnit.getTwo();
        } else {
            unitName = timeUnit.getThree();
        }
        return value + " " + unitName;
    }

    public static long getDuration(String date) {
        LocalDateTime localDateTime = parseDate(date);
        Instant startInstant = localDateTime.atZone(ZoneId.of("Europe/Moscow")).toInstant();
        Duration duration = Duration.between(startInstant, Instant.now());
        return duration.getSeconds();
    }

    public static LocalDateTime parseDate(String date) {
        Instant instant = Instant.parse(date);
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public enum TimeUnit {
        SECONDS("секунда", "секунды", "секунд"),
        MINUTES("минута", "минуты", "минут"),
        HOURS("час", "часа", "часов"),
        DAYS("день", "дня", "дней"),
        WEEKS("неделя", "недели", "недель"),
        MONTHS("месяц", "месяца", "месяцев"),
        YEARS("год", "года", "лет");

        private final String one;
        private final String two;
        private final String three;

        TimeUnit(final String one, final String two, final String three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        public String getOne() {
            return this.one;
        }

        public String getTwo() {
            return this.two;
        }

        public String getThree() {
            return this.three;
        }
    }
}