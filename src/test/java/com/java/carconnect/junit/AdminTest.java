package com.java.carconnect.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.java.carconnect.model.Admin;

public class AdminTest {

    @Test
    public void testDefaultConstructor() {
        Admin admin = new Admin();
        assertNotNull(admin);
    }

    @Test
    public void testParameterizedConstructor() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Admin admin = null;
        try {
            Date joinDate = sdf.parse("2024-01-01");
            admin = new Admin(1, "John", "Doe", "john.doe@example.com", "1234567890",
                    "johndoe", "password123", "Admin", joinDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertNotNull(admin);
        assertEquals(1, admin.getAdminId());
        assertEquals("John", admin.getFirstName());
        assertEquals("Doe", admin.getLastName());
        assertEquals("john.doe@example.com", admin.getEmail());
        assertEquals("1234567890", admin.getPhone());
        assertEquals("johndoe", admin.getUsername());
        assertEquals("password123", admin.getPassword());
        assertEquals("Admin", admin.getRole());
        assertEquals("2024-01-01", new SimpleDateFormat("yyyy-MM-dd").format(admin.getJoinDate()));
    }

    @Test
    public void testGettersAndSetters() {
        Admin admin = new Admin();
        admin.setAdminId(2);
        admin.setFirstName("Jane");
        admin.setLastName("Smith");
        admin.setEmail("jane.smith@example.com");
        admin.setPhone("0987654321");
        admin.setUsername("janesmith");
        admin.setPassword("securepassword");
        admin.setRole("User");
        try {
            admin.setJoinDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-02-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(2, admin.getAdminId());
        assertEquals("Jane", admin.getFirstName());
        assertEquals("Smith", admin.getLastName());
        assertEquals("jane.smith@example.com", admin.getEmail());
        assertEquals("0987654321", admin.getPhone());
        assertEquals("janesmith", admin.getUsername());
        assertEquals("securepassword", admin.getPassword());
        assertEquals("User", admin.getRole());
        assertEquals("2024-02-01", new SimpleDateFormat("yyyy-MM-dd").format(admin.getJoinDate()));
    }

    @Test
    public void testToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Admin admin = null;
        try {
            Date joinDate = sdf.parse("2024-03-01 04:00:00");
            admin = new Admin(4, "Rajesh", "Kumar", "rajesh.kumar@example.com", "9998887777",
                    "rajeshkumar", "securepassword", "Manager", joinDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String expected = "Admin [adminId=4, firstName=Rajesh, lastName=Kumar, email=rajesh.kumar@example.com, " +
                "phone=9998887777, username=rajeshkumar, password=securepassword, role=Manager, " +
                "joinDate=2024-03-01 04:00:00]";

        assertEquals(expected, admin.toString());
    }



    @Test
    public void testHashCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Admin admin1 = null;
        Admin admin2 = null;
        try {
            Date joinDate = sdf.parse("2024-05-11");
            admin1 = new Admin(20, "Akanksha", "Sikarwar", "as@gmail.com", "7539864512",
                    "akanksha123", "akasik123", "fleet manager", joinDate);
            admin2 = new Admin(20, "Akanksha", "Sikarwar", "as@gmail.com", "7539864512",
                    "akanksha123", "akasik123", "fleet manager", joinDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(admin1.hashCode(), admin2.hashCode());
    }

    @Test
    public void testEquals() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Admin admin1 = null;
        Admin admin2 = null;
        Admin admin3 = null;
        try {
            Date joinDate1 = sdf.parse("2024-05-11");
            Date joinDate2 = sdf.parse("2024-05-11");
            Date joinDate3 = sdf.parse("2024-05-11");
            admin1 = new Admin(20, "Akanksha", "Sikarwar", "as@gmail.com", "7539864512",
                    "akanksha123", "akasik123", "fleet manager", joinDate1);
            admin2 = new Admin(20, "Akanksha", "Sikarwar", "as@gmail.com", "7539864512",
                    "akanksha123", "akasik123", "fleet manager", joinDate2);
            admin3 = new Admin(20, "Akanksha", "Chauhan", "as@gmail.com", "7539864512",
                    "akanksha123", "akasik123", "fleet manager", joinDate3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(admin1.equals(admin2));
        assertFalse(admin1.equals(admin3));
    }
}
