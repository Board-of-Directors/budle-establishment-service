package ru.nsu.fit.directors.establishmentservice.enums;

import com.amazonaws.util.StringUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Tag implements ParseableEnum {
    wifi("WI-FI", "/wifi.svg", "Около Wi-Fi"),
    power("Розетки", "/zap.svg", "Около розетки"),
    television("Телевизоры", "/tv.svg", "Около телевизора"),
    quite("Тихое место", "/headphones.svg", "Тихое место"),
    kitchen("Кухня", "/eye.svg", "Около кухни"),
    dance("Танцпол", "/music.svg", "Около танцпола"),
    ;

    private final String translate;
    private final String assets;
    private final String translateForSpot;

    @Nonnull
    public static String getTags() {
        return Arrays.stream(Tag.values())
            .map(Objects::toString)
            .collect(Collectors.joining(StringUtils.COMMA_SEPARATOR));
    }

    @Nonnull
    @Override
    public String getParseableName() {
        return translate;
    }
}
