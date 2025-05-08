package in.bloodsync.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import com.google.gson.Gson;

import in.bloodsync.dao.DonorRecord;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRecord extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson g = new Gson();
        try {
        Map<String,Integer> map = DonorRecord.AdminDesktopRecord();
    	String jsonData = g.toJson(map);
    	out.print(jsonData);

        }catch(SQLException ex) {
        	
        	String jsonData = "{\"status\":404,\"error\":"+ex.getMessage()+"}";
        	out.print(jsonData);
        }
        finally {
        out.flush();
        }
    }
}
