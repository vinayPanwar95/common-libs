package com.learning.commonlbs.datetime;

import lombok.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class DateTimerHelper {
    private static final ZoneId ZONE_ID_CET = ZoneId.of("CET");
    private static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

    public LocalDateTime getNowInUTCTime(){
        return LocalDateTime.now(ZONE_ID_UTC);
    }

    public LocalDateTime convertCETtoUTCTimer(@NonNull LocalDateTime cetTime){
        return cetTime.atZone(ZONE_ID_CET).withZoneSameInstant(ZONE_ID_UTC).toLocalDateTime();
    }

    public Instant convertToEpochMicros(@NonNull LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.MICROS);
    }
}
