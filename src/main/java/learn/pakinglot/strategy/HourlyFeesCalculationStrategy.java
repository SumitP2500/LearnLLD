package learn.pakinglot.strategy;

import java.time.Duration;
import java.util.Date;

import learn.pakinglot.models.VehicleType;
import learn.pakinglot.models.VehicleTypeFees;

public class HourlyFeesCalculationStrategy implements FeesCalculationStrategy {

    @Override
    public Double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {

        // calculate hours required to calculate fees
        long mins = Duration.between(entryTime.toInstant(), exitTime.toInstant()).toMinutes();
        long hours = (mins + 59) / 60; // round up: any part of an hour is billed as a full hour
        
        double rate = VehicleTypeFees.valueOf(vehicleType.name()).getFeesPerHour();
        return hours * rate;

    }

}
