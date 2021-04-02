package by.gstu.workout.enums;

public enum ProgramSegmentTypeEnum {
    WARM_UP("Warm up"), EXERCISE("Exercise"), COOL_DOWN("Cool down");

    private final String value;

    ProgramSegmentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
