package com.UpkeepAccService.UCCS.events.entity;

import javax.json.bind.annotation.JsonbProperty;
import java.time.Instant;
import java.util.Objects;

public abstract class AccountEvent {

    @JsonbProperty
    private final Instant instant;

    protected AccountEvent() {
        this.instant = Instant.now();
    }

    protected AccountEvent(Instant instant) {
        Objects.requireNonNull(instant);
        this.instant = instant;
    }

    public Instant getInstant() {return instant;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEvent that = (AccountEvent) o;
        return instant.equals(that.getInstant());
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }

}
