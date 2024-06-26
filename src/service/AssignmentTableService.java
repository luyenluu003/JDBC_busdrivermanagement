package service;

import attribute.AssignmentTable;
import attribute.Drive;
import attribute.Route;
import main.main;

import java.util.*;
import java.util.stream.Collectors;

public class AssignmentTableService {
    public void showAssignmentTable() {
        for(AssignmentTable assignmentTables : main.assignmentTableList) System.out.println(assignmentTables);
    }

    public boolean isAssignmentExist(int idDrive, int idRoute) {
        for (AssignmentTable assignment : main.assignmentTableList) {
            if (assignment.getDrive().getId() == idDrive && assignment.getRoute().getId() == idRoute) {
                return true;
            }
        }
        return false;
    }

    public boolean isDriveAssignmentCountExceeded(int idDrive,int idRoute,int additionalCount ) {
        int total = 0;
        for (AssignmentTable assignmentTables : main.assignmentTableList) {
            if(assignmentTables.getDrive().getId()==idDrive){
                total+= assignmentTables.getNumberOfTurns();
                if(assignmentTables.getRoute().getId() == idRoute && assignmentTables.getNumberOfTurns() + additionalCount > 15 ){
                    return true;
                }
            }
        }
        return total + additionalCount >15;
    }


    public void addNewAssignmentTable(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập Id lái xe bạn muốn thêm vào bảng phân công:");
        Drive drives;
        int idDrive=9999;
        do{
            idDrive = sc.nextInt();
            sc.nextLine();
            try{
                drives= main.driveService.findIdDrive(idDrive);
                if(drives != null && idDrive>=10000){
                    break;
                }
                System.out.print("Không tìm thấy Id của mã lái xe, vui lòng nhập lại: ");
            }catch (InputMismatchException e){
                System.out.print("Id của lái xe bạn nhập không hợp lệ, vui lòng nhập lại: ");
                sc.nextLine();
            }
        }while (true);
        System.out.print("Nhập Id tuyến mà bạn muốn thêm vào bảng phân công:");
        int idRoute=99;
        Route routes;
        do{
            idRoute = sc.nextInt();
            sc.nextLine();
            try{
                routes = main.routeService.findIdRoute(idRoute);
                if(routes!=null && idRoute>=100){
                    break;
                }System.out.print("Không tìm thấy Id của tuyến, vui lòng nhập lại: ");
            }catch (InputMismatchException e){
                System.out.print("Id tuyến bạn nhập không hợp lệ, vui lòng nhập lại: ");
                sc.nextLine();
            }
        }while (true);

        System.out.print("Nhập số lượt lái xe trên tuyến này: ");
        int assignmentCount = -1;
        do {
            try {
                assignmentCount =sc.nextInt();
                sc.nextLine();

                if (assignmentCount > 0) {
                    break;
                }
                System.out.print("Số lượng tuyến phải là số nguyên, vui lòng nhập lại số lượt: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số lượng tuyến phải là số nguyên, vui lòng nhập lại số lượt: ");
                sc.nextLine();
            }
        } while (true);

        if (isDriveAssignmentCountExceeded(idDrive, idRoute, assignmentCount)) {
            System.out.println("Số lượt lái của lái xe này trên tuyến hoặc tổng số lượt lái đã vượt quá 15. Vui lòng nhập số lượt lái khác.");
            return;
        }

        if (isAssignmentExist(idDrive, idRoute)) {
            System.out.println("Lái xe với tuyến này đã tồn tại trong bảng phân công.");
            System.out.print("Bạn có muốn cập nhật lại không ? (y/n): ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                for (AssignmentTable assignment : main.assignmentTableList) {
                    if (assignment.getDrive().getId() == idDrive && assignment.getRoute().getId() == idRoute) {
                        assignment.setNumberOfTurns(assignmentCount);
                        main.assignmentTableDB.update(assignment);
                        System.out.println("Bảng phân công mới đã cập nhập thành công.");
                        break;
                    }
                }
            } else {
                System.out.println("Quay lại menu chính.");
            }
        } else {
            AssignmentTable assignmentTables = new AssignmentTable(drives,routes,assignmentCount);
            main.assignmentTableList.add(assignmentTables);
            main.assignmentTableDB.writeDataToDB(assignmentTables);
            System.out.println("Bảng phân công mới đã được thêm thành công.");
        }
    }

    public void sortByName(){
        System.out.println("Danh sách bảng phân công sau khi sắp xếp theo tên là:");
        main.assignmentTableList.sort((a1, a2) -> {
            String name1 = a1.getDrive().getName();
            String name2 = a2.getDrive().getName();
            String subName1 = name1.substring(name1.lastIndexOf(" ") + 1);
            String subName2 = name2.substring(name2.lastIndexOf(" ") + 1);
            int result = subName1.compareTo(subName2);
            if (result == 0) {
                String firstName1 = name1.substring(0, name1.lastIndexOf(" "));
                String firstName2 = name2.substring(0, name2.lastIndexOf(" "));
                return firstName1.compareTo(firstName2);
            } else {
                return result;
            }
        });

    }

    public void sortByNumberOfRoute() {
        System.out.println("Danh sách bảng phân công sau khi sắp xếp theo số lượng tuyến là:");

        Map<Drive, Integer> driveRouteCount = new HashMap<>();
        for (AssignmentTable assignment : main.assignmentTableList) {
            Drive drive = assignment.getDrive();
            driveRouteCount.put(drive, driveRouteCount.getOrDefault(drive, 0) + assignment.getNumberOfTurns());
        }
        main.assignmentTableList.sort((a1, a2) -> {
            Drive drive1 = a1.getDrive();
            Drive drive2 = a2.getDrive();
            int totalTurnsDrive1 = driveRouteCount.get(drive1);
            int totalTurnsDrive2 = driveRouteCount.get(drive2);
            return Integer.compare(totalTurnsDrive2, totalTurnsDrive1);
        });
    }

    public void totalDistance(){
        System.out.println("Bảng tổng kê khoảng cách chạy trong ngày:");
        Map<Drive, Integer> total = new HashMap<>();
        for(AssignmentTable assignment : main.assignmentTableList) {
            Drive drive = assignment.getDrive();
            Route route = assignment.getRoute();
            float distancePerTurn = route.getDistance();
            float totalDistanceForAssignment = distancePerTurn*assignment.getNumberOfTurns();
            total.put(drive, (int) (total.getOrDefault(drive,0)+ totalDistanceForAssignment));
        }

        for (Map.Entry<Drive, Integer> entry : total.entrySet()) {
            Drive drive = entry.getKey();
            int totalDistance = entry.getValue();
            System.out.println("Tên lái xe: " + drive.getName() + " - Tổng khoảng cách: " + totalDistance + " km");
        }

    }



    public void initializeAssignmentTableData() {
        main.assignmentTableList = main.assignmentTableDB.readDataFromDBSQL();
        if (main.assignmentTableList.isEmpty()) {
            main.assignmentTableList = new ArrayList<>();
        }
    }
}
