package service;

import attribute.Drive;
import main.main;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriveService {
    public void showDrive(){
        for(Drive drives : main.driveList) System.out.println(drives);
    }

    public void addNewDrive(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào số lái xe bạn muốn thêm:");
        int driveNumber = -1;
        do{
            try{
                driveNumber= sc.nextInt();
                sc.nextLine();
                if(driveNumber>0){
                    break;
                }
                System.out.print("Vui lòng nhập số nguyên số lái xe > 0:");
            }catch (InputMismatchException e){
                System.out.print("Vui lòng nhập số nguyên số lái xe > 0:");
                sc.nextLine();
            }
        }while (true);
        for(int i=0;i<driveNumber;i++){
            Drive drives = new Drive();
            drives.inPutDrive();
            main.driveList.add(drives);
            main.driveDB.writeDataToDB(drives);
        }
    }

    public  Drive findIdDrive(int findId){
        for (Drive drivers : main.driveList) {
            if (drivers.getId() == findId)
                return drivers;
        }
        return null;
    }

    public void initializeDriveData(){
        List<Drive> driveList= main.driveDB.readDataFromDBSQL();
        if(driveList.size()>0){
            Drive.nextId = driveList.get(driveList.size()-1 ).getId()+1;
            main.driveList= driveList;
            System.out.println(main.driveList.size());
        }else{
            main.driveList= new ArrayList<>();
        }
    }
}
