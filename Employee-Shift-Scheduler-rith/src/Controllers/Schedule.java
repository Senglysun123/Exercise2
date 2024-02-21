package Controllers;

import Models.Employee;
import Models.Shift;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Schedule {
    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;

    public Schedule(ArrayList<Shift> shifts, ArrayList<Employee> employees){
        this.shifts = shifts;
        this.employees = employees;
    }

    public void addShifts(Shift shift){
        shifts.add(shift);
    }
    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void getSchedule(){
        ArrayList<Employee> filter = employees.stream().filter(e -> !e.getShifts().isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));;

    }
}
