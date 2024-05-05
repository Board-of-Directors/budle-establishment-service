package ru.nsu.fit.directors.establishmentservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nsu.fit.directors.establishmentservice.exception.IncorrectDayOfWeekException;

import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@RequiredArgsConstructor
public enum DayOfWeek {
    Monday("Пн", "пн", 1),
    Tuesday("Вт", "вт", 2),
    Wednesday("Ср", "ср", 3),
    Thursday("Чт", "чт", 4),
    Friday("Пт", "пт", 5),
    Saturday("Сб", "сб", 6),
    Sunday("Вс", "вс", 7);

    private final String translate;
    private final String translateLittle;
    private final Integer ordinal;

    static public DayOfWeek getDayFromDayOfWeek(java.time.DayOfWeek day) {
        String dayName = day.getDisplayName(TextStyle.SHORT, new Locale("ru"));
        return getDayByLittleString(dayName);
    }

    static public DayOfWeek getDayByString(String day) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.translate.equals(day)) {
                return dayOfWeek;
            }
        }
        throw new IncorrectDayOfWeekException();
    }

    static public DayOfWeek getDayByLittleString(String day) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.translateLittle.equals(day)) {
                return dayOfWeek;
            }
        }
        throw new IncorrectDayOfWeekException();
    }
}
