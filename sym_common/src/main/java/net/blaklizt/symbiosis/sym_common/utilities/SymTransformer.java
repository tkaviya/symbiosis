package net.blaklizt.symbiosis.sym_common.utilities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class SymTransformer {

    public static boolean toBoolean(String name, Optional<Object> val) {
        if (val.isPresent()) {
            Object o = val.get();
            if ((o instanceof Boolean) || (o.getClass().isAssignableFrom(Boolean.TYPE))) {
                return (Boolean)o;
            } else {
                boolean isAllowedType = o instanceof Character;
                isAllowedType |= o.getClass().isAssignableFrom(Character.TYPE);
                isAllowedType |= o instanceof String;
                isAllowedType |= o instanceof Integer;
                isAllowedType |= o.getClass().isAssignableFrom(Integer.TYPE);
                isAllowedType |= o instanceof Long;
                isAllowedType |= o.getClass().isAssignableFrom(Long.TYPE);

                if (!isAllowedType) {
                    throw new IllegalArgumentException("setName cannot be converted to boolean as it is a " + val.get().getClass());
                }

                return o.toString().equalsIgnoreCase("1") ||
                        o.toString().equalsIgnoreCase("yes") ||
                        o.toString().equalsIgnoreCase("true") ||
                        o.toString().equalsIgnoreCase("y");

            }
        } else {
            return false;
        }
    }

    public static <T extends Number> T toNumber(Number number, Class<T> target) {
        if (number == null) {
            return null;
        }
        try {
            return target.getConstructor(String.class).newInstance(number.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BigDecimal toBigDecimal(Number number) {
        if (number == null) {
            return null;
        }
        return new BigDecimal(number.toString());
    }

    public static Boolean toBoolean(String o) {
        return o != null && (o.equalsIgnoreCase("1") || o.equalsIgnoreCase("yes") || o.equalsIgnoreCase("true") || o.equalsIgnoreCase("y"));

    }

    public static Boolean toBoolean(Character o) {
        return o != null && (o == '1' || o == 'y' || o == 'Y');
    }

    public static Boolean toBoolean(Number o) {
        return o != null && o.intValue() == 1;
    }

    public static Date localDateToDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        long epochSecond = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000L;
        return new Date(epochSecond);
    }

    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()));
    }

    public static java.sql.Date localDateToSqlDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        long epochSecond = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000L;
        return new java.sql.Date(epochSecond);
    }

    public static LocalDate sqlDateToLocalDate(java.sql.Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    public static Timestamp localDateTimeToTimestamp(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(date);
        long epochSecond = date.toInstant(offset).toEpochMilli();
        return new Timestamp(epochSecond);
    }

    public static LocalDateTime longTolocalDateTime(Long epoch) {
        return dateToLocalDateTime(new Date(epoch));
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDateTime();
    }

    public static Date localDateTimeToDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(date);
        long epochSecond = date.toInstant(offset).toEpochMilli();
        return new Date(epochSecond);
    }

    public static Long localDateTimeToLong(LocalDateTime date) {
        return localDateTimeToDate(date).getTime();
    }

    public static String localDateTimeToString(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateTime);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
    }
}
