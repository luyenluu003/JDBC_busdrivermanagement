package util.db;

import attribute.AssignmentTable;
import attribute.Drive;
import attribute.Route;
import main.main;
import util.file.DataReadable;
import util.file.DataUpdateable;
import util.file.DataWriteable;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class AssignmentTableDB  implements DataReadable<AssignmentTable>, DataWriteable<AssignmentTable>  , DataUpdateable<AssignmentTable> {

    @Override
    public List<AssignmentTable> readDataFromDBSQL() {
        List<AssignmentTable> assignmentTableList= new ArrayList<AssignmentTable>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdrivermanagement","root","");
            Statement stmt = conn.createStatement();
            String sql = "select * from bangphancong";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int driveId=rs.getInt(1);
                int routeId = rs.getInt(2);
                int numberOfTurns = rs.getInt(3);
                Drive drives;
                do{
                    try{
                        drives= main.driveService.findIdDrive(driveId);
                        if(drives != null){
                            break;
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Input Mismatch");
                    }
                }while (true);
                Route routes;
                do{
                    try{
                        routes = main.routeService.findIdRoute(routeId);
                        if(routes!=null ){
                            break;
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Input Mismatch");
                    }
                }while (true);
                AssignmentTable assignmentTables = new AssignmentTable(drives,routes,numberOfTurns);
                assignmentTableList.add(assignmentTables);
            }
        }catch (Exception thException){
            thException.printStackTrace();
        }
        return assignmentTableList;
    }



    @Override
    public void writeDataToDB(AssignmentTable assignmentTable) {
        try{
            Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/busdrivermanagement","root","");
            String sql = "insert into bangphancong(laiXe_Id,tuyen_Id,soLuot) values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,assignmentTable.getDrive().getId());
            pstmt.setInt(2,assignmentTable.getRoute().getId());
            pstmt.setInt(3,assignmentTable.getNumberOfTurns());
            pstmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(AssignmentTable assignmentTable) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdrivermanagement","root","");
            String sql = "update bangphancong set soLuot =? where laiXe_id=? and tuyen_id=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,assignmentTable.getNumberOfTurns());
            pstmt.setInt(2,assignmentTable.getDrive().getId());
            pstmt.setInt(3,assignmentTable.getRoute().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
