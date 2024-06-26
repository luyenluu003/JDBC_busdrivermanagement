package util.db;

import attribute.Route;
import util.file.DataReadable;
import util.file.DataWriteable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDB  implements DataReadable<Route>, DataWriteable<Route> {

    @Override
    public List<Route> readDataFromDBSQL() {
        List<Route> routeList = new ArrayList<Route>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdrivermanagement","root","");
            Statement stmt = conn.createStatement();
            String sql = "select * from tuyen";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt(1);
                Float distance = rs.getFloat(2);
                int stopPointNumber = rs.getInt(3);
                Route routes =  new Route(id, distance, stopPointNumber);
                routeList.add(routes);
            }
        }catch (Exception thException){
            thException.printStackTrace();
        }

        return routeList;
    }

    @Override
    public void writeDataToDB(Route route) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdrivermanagement","root","");
            String sql = "insert into tuyen(id,khoangCach,soDiemDung) values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, route.getId());
            pstmt.setFloat(2,route.getDistance());
            pstmt.setInt(3,route.getStopPointNumber());
            pstmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
