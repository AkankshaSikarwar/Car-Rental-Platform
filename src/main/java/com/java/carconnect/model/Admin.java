package com.java.carconnect.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Admin {
	private int adminId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String username; 
	private String password;
	private String role;
	private Date joinDate;
	
	public void show()
	{
		System.out.println();
		System.out.println("Admin Id : "+this.adminId);
		System.out.println("First Name : "+this.firstName);
		System.out.println("Last Name : "+this.lastName);
		System.out.println("Email : "+this.email);
		System.out.println("Phone : "+this.phone);
		System.out.println("Username : "+this.username);
		System.out.println("Password : "+this.password);
		System.out.println("Role : "+this.role);
		System.out.println("Join Date : "+this.joinDate);
		System.out.println("-----------------------------------");
	}
	
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", joinDate=" + sdf.format(joinDate) + "]";
	}
	
	public Admin() {
	
		// TODO Auto-generated constructor stub
	}
	public Admin(int adminId, String firstName, String lastName, String email, String phone, String username,
			String password, String role, Date joinDate) {
		
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.role = role;
		this.joinDate = joinDate;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(adminId, firstName, lastName, email, phone, username, password, role, joinDate);
    }
	
	@Override
    public boolean equals(Object obj) {
        Admin admin = (Admin) obj;
        return adminId == admin.adminId &&
                Objects.equals(firstName, admin.firstName) &&
                Objects.equals(lastName, admin.lastName) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(phone, admin.phone) &&
                Objects.equals(username, admin.username) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(role, admin.role) &&
                Objects.equals(joinDate, admin.joinDate);
    }
}
