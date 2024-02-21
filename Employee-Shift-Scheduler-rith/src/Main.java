import Controllers.Schedule;
import Models.Employee;
import Models.Shift;
import Views.EmployeeV;
import Views.ShiftV;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input;
    static Schedule schedule;
    static ArrayList<String> skills;
    public static void main(String[] args) {
        input = new Scanner(System.in);

        skills = new ArrayList<>();
        skills.add("IT");
        skills.add("Support");
        skills.add("Sale");

        schedule = new Schedule(new ArrayList<>(), new ArrayList<>());

        schedule.addEmployee(new Employee("Lysun", skills.get(0), LocalTime.of(7, 0), LocalTime.of(11, 0)));
        schedule.addEmployee(new Employee("Dara", skills.get(2), LocalTime.of(1, 0), LocalTime.of(5, 0)));
        schedule.addEmployee(new Employee("Panha", skills.get(0), LocalTime.of(1, 0), LocalTime.of(5, 0)));
        schedule.addEmployee(new Employee("Cheata", skills.get(1), LocalTime.of(7, 0), LocalTime.of(11, 0)));
        schedule.addEmployee(new Employee("Sokha", skills.get(0), LocalTime.of(1, 0), LocalTime.of(5, 0)));
        schedule.addEmployee(new Employee("Heng", skills.get(1), LocalTime.of(7, 0), LocalTime.of(11, 0)));
        schedule.addEmployee(new Employee("Ratha", skills.get(2), LocalTime.of(7, 0), LocalTime.of(11, 0)));
        Shift shift1 = new Shift("Event1", LocalDate.of(2024, 2, 15), LocalTime.of(7, 30), LocalTime.of(10, 30), skills.get(0));
//        Shift shift2 = new Shift("Shift 2", LocalDate.of(2024, 2, 15), LocalTime.of(17, 0), LocalTime.of(10, 0), "Java");
//        Shift shift3 = new Shift("Shift 3",LocalDate.of(2024, 2, 15), LocalTime.of( 17, 0), LocalTime.of(10, 0), "Python");
//
        schedule.addShifts(shift1);
//        schedule.addShifts(shift2);
//        schedule.addShifts(shift3);

        int op = 0;
        do{
            System.out.println('\n');
            System.out.println("================= Employee Shift Scheduler ===================");
            System.out.println(" 1. Employee Management");
            System.out.println(" 2. Shift Management");
            System.out.println(" 3. Exit");
            System.out.println("==============================================================");
            System.out.print(" Choose : ");
            try{
                op = input.nextInt();
            }catch (Exception e){
                System.err.println("Wrong input!");
                input.next();
                continue;
            }
            switch (op){
                case 1:
                    manageEmployee();
                    break;
                case 2:
                    manageShift();
                    break;
                case 3: break;
                default:
                    System.err.println("Wrong option!");
                    break;
            }
        } while(op!=3);
        input.close();
    }
    static void manageEmployee(){
        int op = 0;
        do {
            System.out.println('\n');
            System.out.println("================= Employee =================");
            System.out.println(" 1. View Employee");
            System.out.println(" 2. Add Employee");
            System.out.println(" 3. Back");
            System.out.println("============================================");
            System.out.print(" Choose : ");
            try{
                op = input.nextInt();
            }catch (Exception e){
                System.err.println("Wrong input!");
                input.next();
                continue;
            }
            switch (op){
                case 1:
                    EmployeeV.viewEmployee(schedule.getEmployees());
                    break;
                case 2:
                    Employee emp = EmployeeV.input(skills);
                    if(emp!=null){
                        schedule.addEmployee(emp);
                    }
                    break;
                case 3: break;
                default:
                    System.err.println("Wrong option!");
                    break;
            }
        }while(op!=3);
    }
    static void manageShift(){
        int op = 0;
        do {
            System.out.println('\n');
            System.out.println("================== Shift ===================");
            System.out.println(" 1. View Shift");
            System.out.println(" 2. Add Shift");
            System.out.println(" 3. Back");
            System.out.println("============================================");
            System.out.print(" Choose : ");
            try{
                op = input.nextInt();
            }catch (Exception e){
                System.err.println("Wrong input!");
                input.next();
                continue;
            }
            switch (op){
                case 1:
                    if(schedule.getShifts().isEmpty()){
                        System.err.println(" No Data");
                        break;
                    }
                    ShiftV.views(schedule.getShifts());
                    System.out.print("Enter Id to assign shift, (0)exit : ");
                    op = input.nextInt();
                    if(op==0) break;
                    Shift foundShift = null;
                    for(Shift shift : schedule.getShifts()){
                        if(shift.getID()==op){
                            foundShift=shift;
                            break;
                        }
                    }
                    if(foundShift==null){
                        System.err.println("Wrong option!");
                        continue;
                    }
                    ArrayList<Employee> employees = ShiftV.filterEmployee(schedule.getEmployees(), foundShift);
                    if(employees.isEmpty()){
                        System.err.println("No employee relate with skill and time!");
                    }else {
                        assignShift(employees, foundShift);
                    }
                    break;
                case 2:
                        Shift shift = ShiftV.input(skills);
                        if(shift!=null){
                            schedule.addShifts(shift);
                        }
                    break;
                case 3: break;
                default:
                    System.err.println("Wrong option!");
                    break;
            }
        }while(op!=3);
    }



    static void assignShift(ArrayList<Employee> employees,Shift shift){
        int op = 0;
        do {
            EmployeeV.header();
            employees.forEach(emp -> {
                System.out.println(EmployeeV.getInfo(emp));
            });
            System.out.print("Enter Id to assign shift to employee, (0)exit : ");
            op = input.nextInt();
            if (op == 0) break;
            Employee found = null;
            for (Employee emp : employees) {
                if (emp.getID() == op) {
                    found = emp;
                    break;
                }
            }
            if (found == null) {
                System.err.println("Wrong option!");
                continue;
            }
            try{
                found.addShift(shift);
                System.out.println("Shift assigned");
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }while (false);
    }
}
