package in.bloodsync.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import in.bloodsync.dbutil.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DonorRecord   {
    
    public static Map<String,Integer> AdminDesktopRecord() throws SQLException{
    	
    	Map<String,Integer> map = new HashMap<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        
    	ResultSet rs1 = st.executeQuery(" select count(*) from blood_donors");
    	if(rs1.next()) {
    	 map.put("totalDonor",rs1.getInt(1));
    	}
    	
    	ResultSet rs2 = st.executeQuery("select count(*) from blood_requests where status='Pending'");
    	if(rs2.next()) {
    	 map.put("totalRequest",rs2.getInt(1));
    	}
    	
    	ResultSet rs3 = st.executeQuery("select sum('available_units') from blood_requests");
    	if(rs3.next()) {
    	 map.put("totalStock",rs3.getInt(1));
    	}
    	return map;	
    }
}
