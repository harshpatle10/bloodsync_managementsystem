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
import in.bloodsync.dao.BloodRequestDao;
import in.bloodsync.pojo.BloodDonorPojo;
import in.bloodsync.pojo.BloodRequestPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HospitalRequestListServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 resp.setContentType("application/json");
    	 
    	 PrintWriter pw = resp.getWriter();
    	
    	 try {
    		List<BloodRequestPojo> list =  BloodRequestDao.getAllBloodRequest();
    		
    		
    		Map<String, List<Map<String, String>>> mapalldata = new HashMap<>();
    		List<Map<String, String>> donorList = new ArrayList<>();

    		for (BloodRequestPojo data : list) {
    		    Map<String, String> map = new HashMap<>();
    		    map.put("HospitalName", data.getHospitalName() + "");
    		    map.put("requestId", data.getRequestId()+"");
    		    map.put("bloodType", data.getBloodType());
    		    map.put("urgency", data.getUrgency());
    		    map.put("requestDate", data.getRequestDate()+"");
    		    map.put("status", data.getStatus() + "");
    		    map.put("requestedUnits", data.getRequestedUnits()+"");
    		    donorList.add(map);
    		}
    		mapalldata.put("allRequestList", donorList);
    		
    	    Gson g = new Gson();
    	    String data  = g.toJson(mapalldata);
    	    pw.print(data);
    	 }catch(SQLException ex) {
         	String data = "{\"status\":404,\"error\":"+ex.getMessage()+"}";
     	    pw.print(data);
    	 }
     }
}
