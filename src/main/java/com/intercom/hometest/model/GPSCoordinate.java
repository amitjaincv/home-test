package com.intercom.hometest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class GPSCoordinate {
    private double latitude;
    private double longitude;

    private GPSCoordinate() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public GPSCoordinate(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    /**
     * This entity is so simple that you don't really need to use the builder
     * pattern (use a constructor instead). I use the builder pattern here because
     * it makes the code a bit more easier to read.
     */
    public static class Builder {
        double latitude;
        double longitude;

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public GPSCoordinate build() {
            requireValidValue();
            GPSCoordinate build = new GPSCoordinate(this);
            return build;
        }

        private void requireValidValue() {

        }
    }
}
