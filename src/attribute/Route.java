package attribute;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Route {
    public static int nextId=100;
    private int id;
    private float distance;
    private int stopPointNumber;

    public Route(){}

    public Route(int id,float distance,int stopPointNumber){
        this.id =id;
        this.distance=distance;
        this.stopPointNumber=stopPointNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getStopPointNumber() {
        return stopPointNumber;
    }

    public void setStopPointNumber(int stopPointNumber) {
        this.stopPointNumber = stopPointNumber;
    }

    public void inputRoute(){
        this.id = nextId;
        nextId++;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập khoảng cách: ");
        float distanceFloat = -1;
        do{
            distanceFloat=sc.nextFloat();
            sc.nextLine();
            try{
                if(distanceFloat>0){
                    this.distance=distanceFloat;
                    break;
                }
                System.out.print("Khoảng cách phải lớn hơn 0, Vui lòng nhập lại:");
            }catch (InputMismatchException e){
                System.out.print("Khoảng cách phải lớn hơn 0, Vui lòng nhập lại:");
                sc.nextLine();
            }
        }while (true);

        System.out.print("Nhập vào số điểm dừng:");
        int numberStop =-1;
        do{
            try{
                numberStop=sc.nextInt();
                sc.nextLine();
                if(numberStop>0){
                    this.stopPointNumber=numberStop;
                    break;
                }
                System.out.print("số điểm dừng phải là số nguyên lớn hơn 0, Vui lòng nhập lại:");
            }catch (InputMismatchException e){
                System.out.print("số điểm dừng phải là số nguyên lớn hơn 0, Vui lòng nhập lại:");
                sc.nextLine();
            }
        }while (true);
    }

    @Override
    public String toString() {
        return "Route : {" + "Mã tuyến:'" + id + '\'' + ", Khoảng cách:'" + distance + '\'' + ", Số điểm dừng:'" + stopPointNumber + '\'' + '}';
    }
}
