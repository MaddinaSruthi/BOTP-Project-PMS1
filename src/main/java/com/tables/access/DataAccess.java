package com.tables.access;
import com.cg.pms.project.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
	
	 public List<Patient> getPatients()
	   {
		   List<Patient> list =new ArrayList<Patient>();
		   try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/patientonboarding", "postgres", "root")) {
	           Statement statement = connection.createStatement();
	           ResultSet resultSet = statement.executeQuery("SELECT * from patient");
	          
	           
	           while (resultSet.next()) {
	        	   Patient obj=new Patient();
	        	   obj.setId(resultSet.getInt("Id"));
	        	   obj.setName(resultSet.getString("name"));
	        	   obj.setDateOfBirth(resultSet.getDate("dateOfBirth"));
	        	   obj.setAddress(resultSet.getString("address"));
	        	   obj.setIdProofDetails(resultSet.getString("idProofDetails"));
	        	   obj.setContactNumber(resultSet.getString("contactNumber"));
	        	   obj.setEmergencyContactNumber(resultSet.getString("emergencyContactNumber"));
	        	   obj.setPrimaryIllness(resultSet.getString("primaryIllness"));
	        	   obj.setSymptoms(resultSet.getString("symptoms"));
	        	   obj.setHealthInsurance(resultSet.getString("healthInsurance"));
	        	   obj.setPreferredDoctorGender(resultSet.getString("preferredDoctorGender"));
	        	   list.add(obj);
	        	   
	           }
	           
	          
	        }
	   catch (SQLException e) {
	           System.out.println("Connection failure.");
	           e.printStackTrace();
	       }
		return list;
		   
	   }
	public Connection connect() throws SQLException {
	    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/patientonboarding", "postgres", "root");
	}

	public int insertPatient(Patient patient) {
	        String SQL = "INSERT INTO patient(id,name,dateOfBirth,address,idProofDetails,contactNumber,emergencyContactNumber,primaryIllness,symptoms,healthInsurance,preferredDoctorGender) "
	                + "VALUES(?,?,?,?,?,?,?,?,?,?,?) RETURNING patient_id";
	 
	        int id=0;
	 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(SQL))  {
	        	
	        	pstmt.setLong(1,patient.getId());
	            pstmt.setString(2,patient.getName());
	            pstmt.setDate(3,(Date) patient.getDateOfBirth());
	            pstmt.setString(4,patient.getAddress());
	            pstmt.setString(5,patient.getIdProofDetails());
	            pstmt.setString(6, patient.getContactNumber());
	            pstmt.setString(7,patient.getEmergencyContactNumber());
	            pstmt.setString(8,patient.getPrimaryIllness());
	            pstmt.setString(9,patient.getSymptoms());
	            pstmt.setString(10,patient.getHealthInsurance());
	            pstmt.setString(11,patient.getPreferredDoctorGender());
	            
	            
	            ResultSet rs= pstmt.executeQuery();
	            if(rs.next())
	            {
	            	id=rs.getInt(1);
	            }
	            
	          
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
	        return id;
	}
}