package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

public class AdminRepository {
	
	Connection con = null; 
	
	public AdminRepository(){
		
		String db = "jdbc:mysql://localhost:3306/pafdb?serverTimezone=UTC";
		String username ="root";
		String password ="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

/*--------------------------------------------------------------------hospital------------------------------------------*/
	public String getHospitals(){
		
		String output = "";
		 output = "<table class=\"table\" >"
				+"<thead>"
		 		+ "<tr>"
		 		+ "<th>hospital Id</th>"
		 		+ "<th>hospital Name</th>"
		 		+ "<th>Address</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th>"
		 		+ "</tr>"
		 		+ "</thead>";
		
	
		String sql = "select * from hospital";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				
				
				int id =rs.getInt(1);
				String Name = rs.getString(2);
				String Location = rs.getString(3);
				
				output += "<tr><td><input id=\"hidItemIdUpdate\" value=\"" + id + "\" name=\"hidItemIdUpdate\" type=\"hidden\"> "+ id +" </td>";
				 output += "<td>" + Name + "</td>";
				 output += "<td>" + Location + "</td>";
				 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btnUpdate btn btn-secondary\"></td>"
							+ "<td><button name=\"btnRemove\" data-itemid='"+id+"' type=\"submit\" value=\""+id+"\"class=\"btnRemove btn btn-danger\">Remove</button></td>"
									+ "</tr>";
			}
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		output += "</table>";
		System.out.println(output);
		return output;
	}
	
	public Admin getHospital(int hospitalID) {
		String sql = "select * from hospital where hospitalID="+hospitalID;
		Admin h = new Admin();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				
				h.setHospitalID(rs.getInt(1));
				h.setHospitalName(rs.getString(2));
				h.setLocation(rs.getString(3));
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return h;
	}
	
	public String add(Admin h1) {
		String sql = "insert into hospital (hospitalName, location, adminID) values (?,?,?)";
		String output="";
		if(con == null) {
			output = "Error while connect db";
		}
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,  h1.getHospitalName());
			st.setString(2, h1.getLocation());
			st.setInt(3, h1.getAdminID());
			
			st.executeUpdate();
		} 
		catch (Exception e)
		{
			System.out.println("Erroe B " +e);
			//e.printStackTrace();
		}
		
		String newRead = getHospitals();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		jsonObject.addProperty("data", newRead);
		return jsonObject.toString();
	}

	public String update(Admin h1) {
		String sql = "update hospital set hospitalName=?, location=? where hospitalID=?";
		String output="";
		if(con == null) {
			output = "Error while connect db";
		}
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,  h1.getHospitalName());
			st.setString(2, h1.getLocation());
			st.setInt(3,  h1.getHospitalID());
			st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		String newRead = getHospitals();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		jsonObject.addProperty("data", newRead);
		return jsonObject.toString();
	}
	
	public String delete(Admin  h1) {
		String sql = "delete from hospital where hospitalID=? ";
		String output="";
		if(con == null) {
			output = "Error while connect db";
		}
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, h1.getHospitalID());
			st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		String newRead = getHospitals();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		jsonObject.addProperty("data", newRead);
		return jsonObject.toString();
	}
	
/*--------------------------------------------------------------------Doctor----------------------------------------*/
	public List<Admin> getDoctors(){
		List<Admin> doctors = new ArrayList<>();
		String sql = "select * from doctor where doctorStatus = 'work' ";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin d = new Admin();
				
				d.setDoctorID(rs.getInt(1));
				d.setDNIC(rs.getString(2));
				d.setDgender(rs.getString(3));
				d.setDgender(rs.getString(3));
				d.setDfirstName(rs.getString(4));
				d.setDlastName(rs.getString(5));
				d.setDemail(rs.getString(6));
				d.setSpecification(rs.getString(7));
				d.setDcontact(rs.getInt(8));
				d.setWorkDate(rs.getString(9));
				d.setWorkTime(rs.getString(10));
				d.setDoctorStatus(rs.getString(11));
				
				doctors.add(d);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return doctors;
	}

	public List<Admin> getNewDoctors(){
		List<Admin> doctors = new ArrayList<>();
		String sql = "select * from doctor where doctorStatus = 'new' ";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin d = new Admin();
				
				d.setDoctorID(rs.getInt(1));
				d.setDNIC(rs.getString(2));
				d.setDgender(rs.getString(3));
				d.setDgender(rs.getString(3));
				d.setDfirstName(rs.getString(4));
				d.setDlastName(rs.getString(5));
				d.setDemail(rs.getString(6));
				d.setSpecification(rs.getString(7));
				d.setDcontact(rs.getInt(8));
				d.setWorkDate(rs.getString(9));
				d.setWorkTime(rs.getString(10));
				d.setDoctorStatus(rs.getString(11));
				
				doctors.add(d);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return doctors;
	}
	
	public void doctorValidate(Admin d1) {
		String sql = "update doctor set doctorStatus=? where DoctorID=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,  d1.getDoctorStatus());
			st.setInt(2, d1.getDoctorID());
			
			st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
/*--------------------------------------------------------------------Appointment---------------------------- ----------*/
	public List<Admin> getAppointments(){
		List<Admin> appointments = new ArrayList<>();
		String sql = "select * from appointment";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin a = new Admin();
				
				a.setAppointmentID(rs.getInt(1));
				a.setAPdate(rs.getString(2));
				a.setAPtime(rs.getString(3));
				
				appointments.add(a);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return appointments;
	}
	
/*------------------------------------------------------------------Patients--------------------------------------*/
	public List<Admin> getPatientDetails(){
		List<Admin> patients = new ArrayList<>();
		String sql = "select * from patient";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin p = new Admin();
				
				p.setPatientID(rs.getInt(1));
				p.setPNIC(rs.getString(2));
				p.setPfirstName(rs.getString(3));
				p.setPlastName(rs.getString(4));
				p.setPemail(rs.getString(5));
				p.setPaddress(rs.getString(6));
				//p.setPcity(rs.getString(7));
				p.setPcontact(rs.getInt(7));
				
				patients.add(p);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return patients;
	}


	
/*------------------------------------------------------------------Payment--------------------------------------*/
	public List<Admin> getPayemtDetails(){
		List<Admin> payment = new ArrayList<>();
		String sql = "select * from payment";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin p = new Admin();
				
				p.setPaymentID(rs.getInt(1));
				p.setType(rs.getString(2));
				p.setDateAndTime(rs.getString(3));
				p.setPamount(rs.getDouble(4));
				p.setPaymentStatus(rs.getString(5));
				
				payment.add(p);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return payment;
	}

}
