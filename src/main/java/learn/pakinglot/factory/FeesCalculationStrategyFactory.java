package learn.pakinglot.factory;

import learn.pakinglot.models.FeesCalculationStrategyType;
import learn.pakinglot.strategy.FeesCalculationStrategy;
import learn.pakinglot.strategy.HourlyFeesCalculationStrategy;

public class FeesCalculationStrategyFactory {
    public static FeesCalculationStrategy getFeesCalculationStrategy(FeesCalculationStrategyType feesCalculationStrategyType) {
        if(feesCalculationStrategyType == FeesCalculationStrategyType.HOURLY) {
            return new HourlyFeesCalculationStrategy();
        } else {
            throw new IllegalArgumentException("Invalid FeesCalculationStrategyType: "+feesCalculationStrategyType.name());
        }
    }
}
