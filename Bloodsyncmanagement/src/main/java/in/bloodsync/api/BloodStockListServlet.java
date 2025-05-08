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
import in.bloodsync.dao.BloodStockDao;
import in.bloodsync.pojo.BloodDonorPojo;
import in.bloodsync.pojo.BloodRequestPojo;
import in.bloodsync.pojo.BloodStockPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BloodStockListServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 resp.setContentType("application/json");
    	 
    	 PrintWriter pw = resp.getWriter();
    	
    	 try {
    		List<BloodStockPojo> list =  BloodStockDao.getAllBloodStock();
    		Map<String, List<Map<String, String>>> mapalldata = new HashMap<>();
    		List<Map<String, String>> donorList = new ArrayList<>();

    		for (BloodStockPojo data : list) {
    		    Map<String, String> map = new HashMap<>();
    		    map.put("bloodType", data.getBloodType() + "");
    		    map.put("availableUnits", data.getAvailableUnits()+"");
    		    map.put("donatedUnits", data.getDonatedUnits()+"");
    		    map.put("totalUnits", data.getTotalUnits()+"");
    		    donorList.add(map);
    		}
    		mapalldata.put("allStockList", donorList);
    		
    	    Gson g = new Gson();
    	    String data  = g.toJson(mapalldata);
    	    pw.print(data);
    	 }catch(SQLException ex) {
         	String data = "{\"status\":404,\"error\":"+ex.getMessage()+"}";
     	    pw.print(data);
    	 }
     }
}
