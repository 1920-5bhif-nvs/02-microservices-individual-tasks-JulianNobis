package at.htl.microservice.player.entity;

import java.time.Period;
import java.time.Year;
import java.util.Calendar;

public class Player {
    // region Variables
    protected Long id;
    protected String name;
    protected double itn;
    protected int year_born;
    //sex; wins; losses from other data model will be neglected as for this current demo
    // endregion

    // region Getter & Setter

    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - (year_born); // not the best solution; but better than hardcoding the age :)
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getItn() {
        return itn;
    }

    public void setItn(double itn) {
        this.itn = itn;
    }

    public int getYear_born() {
        return year_born;
    }

    public void setYear_born(int year_born) {
        this.year_born = year_born;
    }
    // endregion
}
