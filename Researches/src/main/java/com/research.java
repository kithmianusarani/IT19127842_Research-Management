package com;
import java.sql.*;
public class research
{ //A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
 
		return con;
 
	}

	public String insertresearch(String researchName, String researchContactNo, String researchEmail)
 
	{
		String output = "";
 
		try
		{
	 
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; }
 // create a prepared statement
			
	String query = " insert into research (`researchID`,`researchName`,`researchContactNo`,`researchEmail`)" + " values (?, ?, ?, ?)";
 
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
 // binding values
 
 	preparedStmt.setInt(1, 0);
 	preparedStmt.setString(2, researchName);
 	preparedStmt.setString(3, researchContactNo);
 	preparedStmt.setString(4, researchEmail);

 	
// execute the statement

 	preparedStmt.execute();
 	con.close();
 	
 	String newresearch = readresearch(); 
 	 output = "{\"status\":\"success\", \"data\": \"" + 
 	 newresearch + "\"}"; 
 }
 
 catch (Exception e)
 
 {
	 
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the researcher.\"}";
	 System.err.println(e.getMessage());
 
 }
 
 return output;
 
 }

public String readresearch()

 {
	String output = "";
	
	try
	{
		
		Connection con = connect();
		if (con == null)
		{
			return "Error while connecting to the database for reading."; 
		}
 
 // Prepare the html table to be displayed
 
 output = "<table border='1'><tr><th>Research Name</th>"
 		+ "<th>Research ContactNo</th>" +
 		"<th>Research Email</th>" +
 		"<th>Update</th><th>Remove</th></tr>";

 String query = "select * from research";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 
 // iterate through the rows in the result set
 
 while (rs.next())
	 
 {
	 
	 String researchID = Integer.toString(rs.getInt("researchID"));
	 String researchName = rs.getString("researchName");
	 String researchContactNo = rs.getString("researchContactNo");
	 String researchEmail = rs.getString("researchEmail");
 
 // Add into the html table
	 
	 output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + researchID
			 + "'>" + researchName + "</td>"; 
			 output += "<td>" + researchContactNo + "</td>"; 
			 output += "<td>" + researchEmail + "</td>";

 
 // buttons
 
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-secondary'data-researchID='" + researchID + "'</td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-researchID='" + researchID + "'></td></tr>";
 	}
 
 	con.close();
 	
 	// Complete the html table
 	
 	output += "</table>";
 }
	
	catch (Exception e)
	{
	 
	 output = "Error while reading the researcher.";
	 System.err.println(e.getMessage());
 
	}
	
	return output;
 
 }

public String updateresearch(String researchID, String researchName, String researchContactNo, String researchEmail)

 {
	String output = "";
	
	try
	{
		Connection con = connect();
		
		if (con == null)
		{
			return "Error while connecting to the database for updating.";
		}
		
		// create a prepared statement
 
			String query = "UPDATE research SET researchName=?,researchContactNo=?,researchEmail=? WHERE researchID=?";
 
			PreparedStatement preparedStmt = con.prepareStatement(query);
 
			// binding values
 
			preparedStmt.setString(1, researchName);
			preparedStmt.setString(2, researchContactNo);
			preparedStmt.setString(3, researchEmail);
			preparedStmt.setInt(4, Integer.parseInt(researchID));
 
			// execute the statement
 
			preparedStmt.execute();
			con.close();
			String newresearch = readresearch(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newresearch + "\"}"; 
 
	}
 
	catch (Exception e)
 
	{
	 
		output = "{\"status\":\"error\", \"data\":  \"Error while updating the researcher.\"}"; 
		System.err.println(e.getMessage());
 
	}
 
	return output;
 
 }

	public String deleteresearch(String researchID)

 {
	
		String output = "";
		
		try
 
		{
	 
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
	 
			// create a prepared statement
	 
			String query = "delete from research where researchID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
 
			// binding values
 
			preparedStmt.setString(1, researchID);
 
			// execute the statement
 
			preparedStmt.execute();
			con.close();
			
			String newresearch = readresearch(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newresearch + "\"}";
 
		}
		catch (Exception e)
		{
	 
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the researcher.\"}"; 
			System.err.println(e.getMessage());
 
		}
		return output;
 
 	}
} 