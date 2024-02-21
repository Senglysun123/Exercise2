package Views;
import Models.Employee;
import Models.Shift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShiftV {
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
    public static void views(ArrayList<Shift> shifts){
        System.out.println(header()+spacing("Status", -10));
        shifts.forEach((shift -> {
            System.out.println(getInfo(shift)+spacing(shift.isStatus()?"assigned" : "unassigned", -10));
        }));
    }

    public static Shift input(ArrayList<String> skills){
        Shift shift = null;
        System.out.print("Enter shift name : ");
        String name = scanner.next();
        String skill;
        while(true) {
            try {
                for (int i = 0; i < skills.size(); i++) {
                    System.out.println(" " + i + "." + skills.get(i));
                }
                System.out.print("Select Skill no: ");
                int n = scanner.nextInt();
                if(n<0 || n>skills.size()){
                    System.err.println("Wrong option!");
                }else{
                    skill = skills.get(n);
                    break;
                }
            }catch (Exception e){
                System.err.printf("Error input!");
            }
        }
        try {
            System.out.print("Enter start date(yyyy-MM-dd) : ");
            String dateInput = scanner.next();
            LocalDate date = LocalDate.parse(dateInput, formatterDate);
            System.out.print("Enter startTime(HH:mm) : ");
            String start = scanner.next();
            LocalTime startTime = LocalTime.parse(start, formatterTime);
            System.out.print("Enter endTime(HH:mm) : ");
            String end = scanner.next();
            LocalTime endTime = LocalTime.parse(end, formatterTime);
            shift = new Shift(name, date, startTime, endTime, skill);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please enter the time in HH:mm format.");
        }
        return shift;
    }

    public static String header(){
        return  spacing("ID", -5)+
                spacing("Name", -15)+
                spacing("Required Skill", -15)+
                spacing("Date", -20)+
                spacing("Time", -20);
    }
    public static String getInfo(Shift shift){
        return  spacing(String.valueOf(shift.getID()), -5)+
                spacing(shift.getName(), -15)+
                spacing(shift.getRequireSkills(), -15)+
                spacing(formatterDate.format(shift.getDate()), -20)+
                spacing("("+formatterTime.format(shift.getStart())+"-"+formatterTime.format(shift.getEnd())+")", -20);
    }
    private static String spacing(String text, int width){
        return String.format("%"+width+"s", text);
    }

    public static ArrayList<Employee> filterEmployee(ArrayList<Employee> employees, Shift shift) {
        return employees.stream()
                .filter(emp -> (emp.getSkill().equals(shift.getRequireSkills()) &&  emp.getStartTime().isBefore(shift.getStart()) && emp.getEndTime().isAfter(shift.getEnd())))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
