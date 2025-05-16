package review;

public class Time {
    private int hour;
    private int minute;
    private int second;
    
    public Time() {
        this(0, 0, 0);
    }

    public Time(int hour) {
        this(hour, 0, 0);
    }

    public Time(int hour, int minute) {
        this(hour, minute, 0);
    }

    public Time(int hour, int minute, int second) {
        setTime(hour, minute, second);
    }

    public void setTime(int hour, int minute, int second) {
        if (hour >= 0 && hour < 24)
            this.hour = hour;
        else
            this.hour = 0;

        if (minute >= 0 && minute < 60)
            this.minute = minute;
        else
            this.minute = 0;

        if (second >= 0 && second < 60)
            this.second = second;
        else
            this.second = 0;
    }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String toString() {
        return String.format("%d:%02d:%02d %s",
                (hour == 0 || hour == 12) ? 12 : hour % 12,
                minute,
                second,
                (hour < 12) ? "AM" : "PM");
    }
}

