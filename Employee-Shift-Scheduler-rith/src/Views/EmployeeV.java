package Views;

import Models.Employee;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeV {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private static Scanner scanner = new Scanner(System.in);;

    public static void viewEmployee(ArrayList<Employee> employees){
        int op;
        while(true){
            if(employees.isEmpty()){
                System.out.println("No data!");
                return;
            }
            EmployeeV.header();
            for(Employee emp : employees){
                System.out.println(EmployeeV.getInfo(emp));
            }
            System.out.print("Enter ID to view shift, (0)exit : ");
            op = scanner.nextInt();
            if(op==0) break;
            Employee found = null;
            for(Employee emp : employees){
                if(emp.getID()==op){
                    found = emp;
                    break;
                }
            }
            if(found==null){
                System.err.println("Wrong option!");
                continue;
            }
            var shifts = found.getShifts();
            if(shifts.isEmpty()) continue;
            ShiftV.views(shifts);
            System.out.printf("\n");
            break;
        }
    }
    public static Employee input(ArrayList<String> skills){
        Employee emp = null;
        System.out.print("Enter Name : ");
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
            System.out.print("Enter startTime(HH:mm) : ");
            String start = scanner.next();
            LocalTime startTime = LocalTime.parse(start, formatter);
            System.out.print("Enter endTime(HH:mm) : ");
            String end = scanner.next();
            LocalTime endTime = LocalTime.parse(end, formatter);
            emp = new Employee(name, skill, startTime, endTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please enter the time in HH:mm format.");
        }
        return emp;
    }

    public static void header(){
        System.out.println(spacing("ID", -5)+
            spacing("Name", -15)+
            spacing("Skill", -10)+
            spacing("Working Hour", -15)+
            spacing("Shift Assigned", -5));
    }

    public static String getInfo(Employee emp){
        return spacing(String.valueOf(emp.getID()), -5)+
                spacing(emp.getName(), -15)+
                spacing(emp.getSkill(), -10)+
                spacing("("+emp.getStartTime().format(formatter)+"-"+emp.getEndTime().format(formatter)+")", -15)+
                spacing(String.valueOf(emp.getShifts().size()), -5);
    }
    public static void views(ArrayList<Employee> employees){
        header();
        employees.forEach(emp -> {
            System.out.println(getInfo(emp));
        });
    }

    private static String spacing(String text, int width){
        return String.format("%"+width+"s", text);
    }
}
