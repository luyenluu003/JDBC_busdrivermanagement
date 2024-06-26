package attribute;

import java.util.Scanner;

public class Drive {

    public static int nextId = 10000;
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String level;

    public Drive() {
    }

    public Drive(int id, String name, String address, String phoneNumber, String level) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.level = level;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void inPutDrive() {
        this.id = nextId;
        nextId++;

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập họ tên: ");
        this.name = sc.nextLine();

        System.out.print("Nhập địa chỉ: ");
        this.address = sc.nextLine();

        System.out.print("Nhập số điện thoại: ");
        this.phoneNumber = sc.nextLine();

        inPutDriveLevel();
    }

    public void inPutDriveLevel() {
        System.out.print("Nhập trình độ từ (A-F): ");
        String driveLevel;
        Scanner sc = new Scanner(System.in);
        do {
            driveLevel = sc.nextLine().toUpperCase();
            if (driveLevel.matches("[a-fA-F]")) {
                this.level = driveLevel;
                break;
            }
            System.out.println("Trình độ phải từ A đến F. Vui lòng thử lại.");
        } while (true);
    }

    @Override
    public String toString() {
        return "Drive : {" +
                "Mã tài xế:'" + id + '\'' +
                ", Họ tên:'" + name + '\'' +
                ", Địa chỉ:'" + address + '\'' +
                ", Số điện thoại:'" + phoneNumber + '\'' +
                ", Trình độ:'" + level + '\'' +
                '}';
    }
}
