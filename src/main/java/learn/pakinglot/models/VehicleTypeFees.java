package learn.pakinglot.models;

public enum VehicleTypeFees {
    TWO_WHEELER(10.0),
    FOUR_WHEELER(40.0);

    private final double feesPerHour;

    VehicleTypeFees(double feesPerHour) {
        this.feesPerHour = feesPerHour;
    }

    public double getFeesPerHour() {
        return feesPerHour;
    }
}
