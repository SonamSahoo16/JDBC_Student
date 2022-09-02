package com.sonam.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adhaar 
{
	public static void main(String[] args) throws SQLException 
	{
		Adhaar obj = new Adhaar();
		
		Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/my_db?useSSL=false", "root", "admin");
		
		obj.create(connection);
		obj.read(connection);
		obj.update(connection);
		obj.read(connection);
		//obj.delete(connection);
	}
	 void create(Connection connection) throws SQLException
	{
		String createStatement = "INSERT INTO adhaar_card" +
		        "  (id, name, dob, address, card_num) VALUES " +
		        " (?, ?, ?, ?, ?);";
		
		PreparedStatement pps = connection.prepareStatement(createStatement);
		pps.setInt(1,3);
		pps.setString(2,"Sweddy");
		pps.setDate(3,new Date(1999, 05, 16));
		pps.setString(4,"Plot 54, Rasulgarh");
		pps.setLong(5, 703979);
		
		int rows = pps.executeUpdate(); //returns the no of rows updated
		System.out.println("Rows : "+ rows);
		
	}
	 void read(Connection connection) throws SQLException
	{
		String readStatement = "Select * from adhaar_card where id=?";
		
		PreparedStatement pps = connection.prepareStatement(readStatement);
		pps.setInt(1, 1);
		
		ResultSet rs = pps.executeQuery(); //result stored in kind of a list
		
		while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Date dob = rs.getDate("dob");
            String address = rs.getString("address");
            long card_num = rs.getLong("card_num");
            System.out.println(id + "," + name + "," + dob + "," + address + "," + card_num);
		}
		
		
	}
	 void update(Connection connection) throws SQLException 
	{
		String updateStatement = "update adhaar_card set name = ? where id = ?;" ;
		
		PreparedStatement pps = connection.prepareStatement(updateStatement);
		
		pps.setString(1, "Sahoo");
        pps.setInt(2, 2);
        pps.executeUpdate();
        
        

		
	}
	 void delete(Connection connection) throws SQLException 
	{
		String deleteStatement = "delete from adhaar_card where id=?";
		
		PreparedStatement pps = connection.prepareStatement(deleteStatement);
		
		pps.setInt(1,1);
		int rows = pps.executeUpdate();
		
		System.out.println(rows);
	}
	
}