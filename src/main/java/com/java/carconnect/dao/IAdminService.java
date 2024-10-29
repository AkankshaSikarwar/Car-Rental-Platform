package com.java.carconnect.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.carconnect.exception.AdminNotFoundException;
import com.java.carconnect.model.Admin;

public interface IAdminService {
	List<Admin> showAdmin() throws SQLException, ClassNotFoundException;
	Admin getAdminById(int adminId) throws SQLException, ClassNotFoundException;
	Admin getAdminByUsername(String username) throws ClassNotFoundException, SQLException;
	Boolean validateAdmin(String username,String password) throws ClassNotFoundException, SQLException;
	String  updateAdmin(Admin admin4) throws ClassNotFoundException, SQLException;
	String  deleteAdmin(int adminId ) throws ClassNotFoundException, SQLException, AdminNotFoundException;
	public String registerAdmin(Admin admin) throws ClassNotFoundException, SQLException;
}
