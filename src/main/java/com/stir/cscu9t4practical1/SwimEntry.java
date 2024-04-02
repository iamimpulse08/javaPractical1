package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry {

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    String where;

    public SwimEntry (String n, int d, int m, int y, int h, int min, int s, float dist, String where) {
        super(n, d, m, y, h, min, s, dist);
        this.where = where;
    }

    // Alice swam 3.0 km outdoors in 0:16:7 on 1/2/2003


    @Override
    public String getEntry () {
        String result = getName()+" swam " + getDistance() + " km " + getWhere() + " in "
                +getHour()+":"+getMin()+":"+ getSec() + " on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;
    } //getEntry
}
