package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {
    private int repetitions;
    private int recovery;

    public SprintEntry (String n, int d, int m, int y, int h, int min, int s, float dist, int repetitions, int recovery) {
        super(n, d, m, y, h, min, s, dist);
        this.recovery = recovery;
        this.repetitions = repetitions;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getRecovery() {
        return recovery;
    }

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    @Override
    public String getEntry() {
        String result = getName()+" sprinted " + getRepetitions() + "x"+ getDistance() + "km in "
                +getHour()+":"+getMin()+":"+ getSec() + " with " + getRecovery() + " minutes recovery on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;
    }
}
