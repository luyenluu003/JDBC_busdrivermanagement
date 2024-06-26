package main;


import attribute.AssignmentTable;
import attribute.Drive;
import attribute.Route;
import service.AssignmentTableService;
import service.DriveService;
import service.RouteService;
import util.db.AssignmentTableDB;
import util.db.DriveDB;
import util.db.RouteDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class main {
    public static List<Drive> driveList;
    public static List<Route> routeList ;
    public static List<AssignmentTable> assignmentTableList ;
    public static DriveDB driveDB = new DriveDB();
    public static RouteDB routeDB = new RouteDB();
    public static AssignmentTableDB assignmentTableDB = new AssignmentTableDB();
    public static DriveService driveService = new DriveService();
    public static RouteService routeService = new RouteService();
    public static AssignmentTableService assignmentTableService = new AssignmentTableService();

    public static void initializeData() {
        driveService.initializeDriveData();
        routeService.initiaLizeRouteData();
        assignmentTableService.initializeAssignmentTableData();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeData();
        int choice;
        do{
            System.out.println("\nBus Driver Managenment");
            System.out.println("1. Thêm lái xe");
            System.out.println("2. Hiển thị lái xe");
            System.out.println("3. Thêm tuyến");
            System.out.println("4. Hiển thị tuyến");
            System.out.println("5. Lập bảng phân công");
            System.out.println("6. Hiển thị bảng phân công");
            System.out.println("7. Sắp xếp bảng phân công theo họ tên");
            System.out.println("8. Sắp xếp theo số lượng tuyến đảm nhận trong ngày");
            System.out.println("9. Bảng thống kê tổng khoảng cách xe chạy của mỗi lái xe");
            System.out.println("0. Thoát...");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    driveService.addNewDrive();
                    pause();
                    break;
                case 2:
                    driveService.showDrive();
                    pause();
                    break;
                case 3:
                    routeService.addNewRoute();
                    pause();
                    break;
                case 4:
                    routeService.showRoute();
                    pause();
                    break;
                case 5:
                    assignmentTableService.addNewAssignmentTable();
                    pause();
                    break;
                case 6:
                    assignmentTableService.showAssignmentTable();
                    pause();
                    break;
                case 7:
                    assignmentTableService.sortByName();
                    assignmentTableService.showAssignmentTable();
                    pause();
                    break;
                case 8:
                    assignmentTableService.sortByNumberOfRoute();
                    assignmentTableService.showAssignmentTable();
                    pause();
                    break;
                case 9:
                    assignmentTableService.totalDistance();
                    pause();
                    break;
                case 0:
                    System.out.println("Thoátttttt...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng thử lại.");
            }
        } while (choice != 0);

        sc.close();
    }



    public static void pause() {
        System.out.println("Nhấn phím Enter để tiếp tục...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
