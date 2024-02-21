package Models;
import java.time.LocalTime;
import java.util.ArrayList;

public class Employee {
    private static int count=0;
    private int id;
    private String name;
    private String skill;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<Shift> shifts = new ArrayList<>();

    public Employee(String name, String skill, LocalTime startTime, LocalTime endTime) {
        this.id = ++count;
        this.name = name;
        this.skill = skill;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getID(){
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            Employee other = (Employee) obj;
            return other.id == this.id;
        }
        return false;
    }

    public void addShift(Shift shift) throws Exception {
        if(shifts.contains(shift)) throw new Exception("Assigned!");
        if(!shift.getRequireSkills().contains(skill)) throw  new Exception("Skill is not the same!");
        if(startTime.isBefore(shift.getStart()) && endTime.isAfter(shift.getEnd())){
            shift.setStatus(true);
            shifts.add(shift);
        }else{
            throw new Exception("Shift end time is not within the employee's working hours!");
        }
    }
}
