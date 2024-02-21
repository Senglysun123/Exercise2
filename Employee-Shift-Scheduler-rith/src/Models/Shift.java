package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Shift {
    private static int count = 0;
    private int id;
    private String name;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private String requireSkills;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Shift(String name,LocalDate date, LocalTime start, LocalTime end, String requireSkills){
        this.id = ++count;
        this.name = name;
        this.date = date;
        this.start = start;
        this.end = end;
        this.requireSkills = requireSkills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public int getID(){
        return id;
    }
    public String getName() {
        return name;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getRequireSkills() {
        return requireSkills;
    }
}
