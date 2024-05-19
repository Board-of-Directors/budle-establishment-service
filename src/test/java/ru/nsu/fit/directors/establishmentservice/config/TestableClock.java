package ru.nsu.fit.directors.establishmentservice.config;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TestableClock extends Clock {
    private final Clock clock;
    private Clock fixed;

    public TestableClock() {
        this.clock = Clock.systemDefaultZone();
    }

    @Override
    public ZoneId getZone() {
        if (fixed == null) {
            return clock.getZone();
        }
        return fixed.getZone();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Instant instant() {
        if (fixed == null) {
            return clock.instant();
        }
        return fixed.instant();
    }

    public void setFixed(Instant instant) {
        this.fixed = Clock.fixed(instant, ZoneId.systemDefault());
    }
}
