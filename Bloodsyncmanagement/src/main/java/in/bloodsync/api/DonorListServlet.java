package in.bloodsync.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.BloodDonorDao;
import in.bloodsync.pojo.BloodDonorPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DonorListServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 resp.setContentType("application/json");
    	 
    	 PrintWriter pw = resp.getWriter();
    	
    	 try {
    		List<BloodDonorPojo> list =  BloodDonorDao.getAllDonors();
    		
    		
    		Map<String, List<Map<String, String>>> mapalldata = new HashMap<>();
    		List<Map<String, String>> donorList = new ArrayList<>();

    		for (BloodDonorPojo data : list) {
    		    Map<String, String> map = new HashMap<>();
    		    map.put("donorId", data.getDonorId() + "");
    		    map.put("donorName", data.getName());
    		    map.put("donorBloodType", data.getBloodType());
    		    map.put("donorCity", data.getCity());
    		    map.put("donorContact", data.getContact());
    		    map.put("donorBloodUnit", data.getBloodUnit() + "");
    		    donorList.add(map);
    		}
    		mapalldata.put("allDonorList", donorList);
    		
    	       	    Gson g = new Gson();
    	    String data  = g.toJson(mapalldata);
    	    pw.print(data);
    	 }catch(SQLException ex) {
         	String data = "{\"status\":404,\"error\":"+ex.getMessage()+"}";
     	    pw.print(data);
    	 }
     }
}
