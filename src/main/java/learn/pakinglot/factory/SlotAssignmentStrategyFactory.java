package learn.pakinglot.factory;

import learn.pakinglot.models.SlotAssignmentStrategyType;
import learn.pakinglot.strategy.RandomSlotAssignmentStrategy;
import learn.pakinglot.strategy.SlotAssignmentStrategy;

public class SlotAssignmentStrategyFactory {

    public static SlotAssignmentStrategy getSlotAssignmentStrategy(SlotAssignmentStrategyType slotAssignmentStrategyType) {
        if(slotAssignmentStrategyType == SlotAssignmentStrategyType.RANDOM) {
            return new RandomSlotAssignmentStrategy();
        } else {
            throw new RuntimeException("Unknown Slot Assignment Strategy Type");
        }
    }

}
