package learn.pakinglot.strategy;

import java.util.Date;

import learn.pakinglot.models.VehicleType;

/**
 * FeesCalculationStrategy
 */
public interface FeesCalculationStrategy {
    Double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType);
}
