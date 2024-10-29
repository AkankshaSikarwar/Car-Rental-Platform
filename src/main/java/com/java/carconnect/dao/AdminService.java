package com.java.carconnect.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.carconnect.model.Admin;
import com.java.carconnect.util.DBConnUtil;
import com.java.carconnect.util.DBPropertyUtil;

public class AdminService  implements IAdminService{
	String connectionString;
	Connection connection;
	PreparedStatement ps;
	
	@Override
	public List<Admin> showAdmin() throws SQLException, ClassNotFoundException {
		
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection=DBConnUtil.getConnection(connectionString);
		
		String command ="SELECT * FROM Admin";
		
		ps = connection.prepareStatement(command);
		ResultSet rs = ps.executeQuery(); 
		
		List<Admin> adminList = new ArrayList<Admin>();
		Admin  admin = null;
		
		while(rs.next())
		{ 
			 admin=new Admin();
			 
			 admin.setAdminId(rs.getInt("adminId"));
			 admin.setFirstName(rs.getString("firstName"));
			 admin.setLastName(rs.getString("lastName"));
			 admin.setEmail(rs.getString("email"));
			 admin.setPhone(rs.getString("phoneNumber"));
			 admin.setUsername(rs.getString("username"));
			 admin.setPassword(rs.getString("password"));
			 admin.setRole(rs.getString("role"));
			 admin.setJoinDate(rs.getDate("joinDate"));
			 
			 adminList.add(admin);
		}
		return adminList;
	}

	@Override
	public Admin getAdminById(int adminID) throws SQLException, ClassNotFoundException 
	{
		String connectionString=DBPropertyUtil.getConnectionString("db");
		connection=DBConnUtil.getConnection(connectionString);
		
		String command="select * from Admin where AdminID= ?";
		ps=connection.prepareStatement(command);
		
		ps.setInt(1,adminID);
		ResultSet rs = ps.executeQuery();
		Admin admin=null;
		
		if(rs.next())
		{
			admin=new Admin();
			
			admin.setAdminId(rs.getInt("adminId"));
			 admin.setFirstName(rs.getString("firstName"));
			 admin.setLastName(rs.getString("lastName"));
			 admin.setEmail(rs.getString("Email"));
			 admin.setPhone(rs.getString("PhoneNumber"));
			 admin.setUsername(rs.getString("Username"));
			 admin.setPassword(rs.getString("Password"));
			 admin.setRole(rs.getString("Role"));
			 admin.setJoinDate(rs.getDate("JoinDate"));
		}
		return admin;
	}
	
	public Admin getAdminByUsername(String username) throws ClassNotFoundException, SQLException
	{
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command="SELECT * FROM Admin WHERE username = ?";
		
		ps = connection.prepareStatement(command);
		ps.setString(1,username);
		
		ResultSet rs = ps.executeQuery();
		Admin admin=null;
		if(rs.next())
		{
			 admin=new Admin();
				
			 admin.setAdminId(rs.getInt("adminId"));
			 admin.setFirstName(rs.getString("firstName"));
			 admin.setLastName(rs.getString("lastName"));
			 admin.setEmail(rs.getString("email"));
			 admin.setPhone(rs.getString("phoneNumber"));
			 admin.setUsername(rs.getString("username"));
			 admin.setPassword(rs.getString("password"));
			 admin.setRole(rs.getString("role"));
			 admin.setJoinDate(rs.getDate("joinDate"));
		}
		return admin;
	}
	
	public String registerAdmin(Admin admin) throws ClassNotFoundException, SQLException
	{
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "INSERT INTO Admin(AdminID, FirstName,LastName,Email,PhoneNumber,UserName, Password,Role,JoinDate) "
				+ " VALUES(?,?,?,?,?,?,?,?,?)";
		
		ps = connection.prepareStatement(command);
		
		ps.setInt(1,admin.getAdminId());
		ps.setString(2,admin.getFirstName());
		ps.setString(3,admin.getLastName());
		ps.setString(4,admin.getEmail());
		ps.setString(5,admin.getPhone());
		ps.setString(6,admin.getUsername());
		ps.setString(7,admin.getPassword());
		ps.setString(8,admin.getRole());
		java.sql.Date sqlJoinDate = new java.sql.Date(admin.getJoinDate().getTime());
	    ps.setDate(9, sqlJoinDate);
		
		ps.executeUpdate();
		return "Admin Registered Successfully.";
		
	}
	
	public Boolean validateAdmin(String username,String password) throws ClassNotFoundException, SQLException
	{
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);

		String command = "SELECT COUNT(*) rowCount FROM Admin WHERE username = ? AND password = ?";
		
		ps = connection.prepareStatement(command);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		int rowAffected = rs.getInt("rowCount");
//		System.out.println(rowAffected);
		if(rowAffected>0)
		{
			return true;
		}
		return false;
	}
	
	
	public String updateAdmin(Admin admin) throws ClassNotFoundException, SQLException
	{
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "UPDATE Admin SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?,"
				+ " Username = ?, Password = ?, Role = ?, JoinDate = ? WHERE AdminID = ?";

		ps=connection.prepareStatement(command);
		
		ps.setString(1,admin.getFirstName());
		ps.setString(2,admin.getLastName());
		ps.setString(3,admin.getEmail());
		ps.setString(4,admin.getPhone());
		ps.setString(5,admin.getUsername());
		ps.setString(6,admin.getPassword());
		ps.setString(7,admin.getRole());
		java.sql.Date sqlJoinDate = new java.sql.Date(admin.getJoinDate().getTime());
	    ps.setDate(8, sqlJoinDate);
		ps.setInt(9,admin.getAdminId());
		
		int rowAffected = ps.executeUpdate();
		
		if(rowAffected>0) {
			return "Admin data updated successfully.";
		}
		return "Failed to Update Admin data.";
						
	}
	
	public String  deleteAdmin(int adminID ) throws ClassNotFoundException, SQLException
	{
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "DELETE FROM Admin WHERE AdminId = ?"; 
		
		ps = connection.prepareStatement(command);
		ps.setInt(1,adminID);
		
		int rowsAffected = ps.executeUpdate();
		
		if(rowsAffected > 0)
		{
			return "Admin Record Deleted successfully.";
		}
		return "Unable to delete Admin Record.";
	}

	}

	


