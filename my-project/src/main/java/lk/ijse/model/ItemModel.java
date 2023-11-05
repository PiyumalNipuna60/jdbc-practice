package lk.ijse.model;

import lk.ijse.Tm.ItemTm;
import lk.ijse.db.dbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.ItemDto;

import java.awt.image.DataBufferDouble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {


    public boolean saveItem(ItemDto dto) {

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("insert into Item values(?,?,?,?)");
            pstm.setObject(1,dto.getItemCode());
            pstm.setObject(2,dto.getDescription());
            pstm.setObject(3, dto.getPrice());
            pstm.setObject(4,dto.getQty());
            
            boolean b = pstm.executeUpdate() > 0;
            return  b;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ItemDto searchItem(String itemCode) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Item WHERE ItemCode = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,itemCode);

            ResultSet resultSet = pstm.executeQuery();
            ItemDto dto = null;
            if (resultSet.next()){
                String ItemCode = resultSet.getString(1);
                String description = resultSet.getString(2);
                double price = Double.parseDouble(resultSet.getString(3));
                int qty = Integer.parseInt(resultSet.getString(4));

                dto =new  ItemDto(ItemCode,description,price,qty);
            }
            return dto;

        } catch (SQLException e) {
            throw new RuntimeException(itemCode);
        }

    }

    public boolean updateItem(ItemDto dto) throws SQLException {
        Connection connection = dbConnection.getInstance().getConnection();

        String sql = "UPDATE Item SET Description = ?, Price = ?, Qty = ? WHERE ItemCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        pstm.setString(1,dto.getDescription());
        pstm.setString(2, String.valueOf(dto.getPrice()));
        pstm.setString(3, String.valueOf(dto.getQty()));
        pstm.setString(4,dto.getItemCode());
        
        return pstm.executeUpdate() > 0;

    }

    public int deleteItem(String itemCode) {
        Connection connection = null;
        try {
            connection = dbConnection.getInstance().getConnection();
            String sql = ("delete  from Item where ItemCode=?");
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,itemCode);
            int affectedRows  = pstm.executeUpdate();

            return affectedRows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ItemTm> getAll() {
        ArrayList<ItemTm> allData = new ArrayList<ItemTm>();

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                allData.add(
                        new ItemTm(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4)
                        )
                );
            }
            return  allData;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
