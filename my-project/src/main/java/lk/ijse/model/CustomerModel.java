package lk.ijse.model;

import com.mysql.cj.xdevapi.PreparableStatement;
import javafx.scene.control.Alert;
import lk.ijse.Tm.CustomerTm;
import lk.ijse.db.dbConnection;
import lk.ijse.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public CustomerDto searchCustomer( String id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM Customer WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,id);

            ResultSet resultSet = pstm.executeQuery();
                CustomerDto dto =null;
            if (resultSet.next()){
                String Id = resultSet.getString(1);
                String Name = resultSet.getString(2);
                String Address = resultSet.getString(3);
                int tel = Integer.parseInt(resultSet.getString(4));

                dto = new CustomerDto( id,Name, Address,tel);
                }
            return dto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveCustomer(CustomerDto dto)  {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("insert into Customer values(?,?,?,?)");
            pstm.setObject(1, dto.getId());
            pstm.setObject(2,dto.getName());
            pstm.setObject(3,dto.getAddress());
            pstm.setObject(4,dto.getTel());

            boolean b= pstm.executeUpdate()>0;
            return b;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean editCustomer(CustomerDto dto) throws SQLException {
        Connection connection = dbConnection.getInstance().getConnection();

        String sql = "UPDATE Customer SET name = ?, address = ?, tel = ? WHERE id = ?"; //UPDATE Customer SET name = "Hashan" , address = "Kaluthara" , tel = 097234635 WHERE id = "C003";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, String.valueOf(dto.getTel()));
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    public int deleteCustomer(String id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();

            String sql = ("delete  from Customer where id=?");
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,id);
            int affectedRows = pstm.executeUpdate();
            return affectedRows;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CustomerTm> getAll() {
        ArrayList<CustomerTm> allData = new ArrayList<CustomerTm>();

        try {
          Connection  connection = dbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                allData.add(
                        new CustomerTm(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4)
                        )
                );
            }
            return allData;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public ArrayList<String> getAllId() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select id from customer");
            ResultSet resultSet = pstm.executeQuery();
            ArrayList<String> objects = new ArrayList<>();
            while (resultSet.next()){
                objects.add(resultSet.getString(1));
            }
            return objects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
