package in.bloodsync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.bloodsync.dbutil.DBConnection;
import in.bloodsync.pojo.BloodRequestPojo;

public class BloodRequestDao {
	
	public static boolean addBloodRequest(BloodRequestPojo request) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO blood_requests (hospital_name, blood_type, urgency, requested_units) VALUES(?,?,?,?)");
 
		ps.setString(1, request.getHospitalName());
		ps.setString(2, request.getBloodType());
		ps.setString(3, request.getUrgency());
		ps.setInt(4, request.getRequestedUnits());

		int ans = ps.executeUpdate();
		ps.close();
		return ans > 0;
	}
	
  public static List<BloodRequestPojo> getAllBloodRequest() throws SQLException{
	  
	  Connection conn=DBConnection.getConnection();
	  String query = "SELECT * from blood_requests";
	  Statement st = conn.createStatement();
	  ResultSet rs = st.executeQuery(query);
	 
	  List<BloodRequestPojo> requests = new ArrayList<>();
	  BloodRequestPojo request;
	  
	  while(rs.next()) {
		  request = new BloodRequestPojo();
		  request.setRequestId(rs.getInt("request_id"));
		  request.setHospitalName(rs.getString("hospital_name"));
		  request.setBloodType(rs.getString("blood_type"));
		  request.setUrgency(rs.getString("urgency"));
		  request.setStatus(rs.getString("status"));
		  request.setRequestDate(rs.getDate("request_date"));
		  request.setRequestedUnits(rs.getInt("requested_units"));
		  requests.add(request);
	  }
	  rs.close();
	  st.close();
	  return requests;
  }
}
