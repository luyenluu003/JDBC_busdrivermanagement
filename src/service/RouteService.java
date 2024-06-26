package service;

import attribute.Drive;
import attribute.Route;
import main.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    public void showRoute(){
        for(Drive drives : main.driveList) System.out.println(drives);
    }

    public void addNewRoute(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào số tuyến bạn muốn thêm:");
        int routeNumber = -1;
        do{
            try{
                routeNumber = sc.nextInt();
                sc.nextLine();
                if(routeNumber >0){
                    break;
                }
                System.out.print("Vui lòng nhập vào số nguyên số tuyến bạn muốn thêm:");
            }catch (InputMismatchException e){
                System.out.print("Vui lòng nhập vào số nguyên số tuyến bạn muốn thêm:");
                sc.nextLine();
            }
        }while (true);
        for(int i=0;i<routeNumber;i++){
            Route routes = new Route();
            routes.inputRoute();
            main.routeList.add(routes);
            main.routeDB.writeDataToDB(routes);
        }
    }
    public  Route findIdRoute(int findId) {
        for(Route routes : main.routeList){
            if(routes.getId() == findId){
                return routes;
            }
        }
        return null;
    }

    public void initiaLizeRouteData(){
        List<Route> routeList = main.routeDB.readDataFromDBSQL();
        if(routeList.size()>0){
            Route.nextId = routeList.get(routeList.size()-1).getId() +1;
            main.routeList=routeList;
            System.out.println(main.routeList.size());
        }else{
            main.routeList= new ArrayList<>();
        }
    }
}
