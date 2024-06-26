package util.db;

import attribute.Drive;
import util.file.DataReadable;
import util.file.DataWriteable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriveDB implements DataReadable<Drive>, DataWriteable<Drive> {
    @Override
    public List<Drive> readDataFromDBSQL() {
        List<Drive> driverList = new ArrayList<Drive>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdrivermanagement","root","");
            Statement stmt = conn.createStatement();
            String sql ="Select * from laixe order by id";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String phoneNumber = rs.getString(4);
                String level = rs.getString(5);
                Drive drivers = new Drive(id,name,address,phoneNumber,level);
                driverList.add(drivers);
            }
        }catch(Exception thException){
            thException.printStackTrace();
        }

        return driverList;
    }

    @Override
    public void writeDataToDB(Drive drive) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdrivermanagement","root","");
            String sql = " insert into laixe (id, hoTen,diaChi,SDT,trinhDo) values (?,?,?,?,?)";
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setInt(1,drive.getId());
            pstmt.setString(2, drive.getName());
            pstmt.setString(3,drive.getAddress());
            pstmt.setString(4,drive.getPhoneNumber());
            pstmt.setString(5, drive.getLevel());
            pstmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
