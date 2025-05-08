package in.bloodsync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.bloodsync.dao.BloodRequestDao;
import in.bloodsync.pojo.BloodRequestPojo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HospitalRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        String hospital_name = req.getParameter("hospital_name");
        String blood_type  = req.getParameter("blood_type");
        int blood_units = Integer.parseInt(req.getParameter("blood_units"));
        String urgency = req.getParameter("urgency");
        
        BloodRequestPojo br = new BloodRequestPojo();
        br.setHospitalName(hospital_name);
        br.setBloodType(blood_type);
        br.setRequestedUnits(blood_units);
        br.setUrgency(urgency);
       RequestDispatcher reqdis = req.getRequestDispatcher("html/response_handling.html");
       BloodRequestDao brd = new BloodRequestDao();
       try {
    	  boolean b =  brd.addBloodRequest(br);
    	  if(b) {
    		pw.println("<title>Hospital Request !</title>");
  			pw.println("<div class='wrapper'>");
  			pw.println("<div class='container'>");
  			pw.println("<h1 class='green'>✅ Blood Request Submitted Successfully!</h1>");
  			pw.println("<p>Thank you for submitting your blood request.! ♥ </p>");
  			pw.println("<p>Our team will review the details and notify nearby donors as soon as possible.</p>");
  			pw.println("<div>Together, let’s save lives — one drop at a time. 🩸❤️</div>");
  			pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
    	  }else {
    		  pw.println("<title>Registration Fail!</title>");
  			pw.println("<div class='wrapper'>");
  			pw.println("<div class='container'>");
  			pw.println("<h1 class='red'>Registration Fail!</h1>");
  			pw.println("<p>Something went wrong. Please try later. </p>");
  			pw.println("<p>If the problem persists, contact support.</p>");
  			pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
  			pw.println("</div></div>");
    	  }
       }catch(SQLException ex) {
    	   pw.println("<title>Registration Fail!</title>");
			pw.println("<div class='wrapper'>");
			pw.println("<div class='container'>");
			pw.println("<h1 class='red'>Registration Fail!</h1>");
			pw.println("<p>Something went wrong. Please try later. </p>");
			pw.println("<p>If the problem persists, contact support.</p>");
			pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
			pw.println("</div></div>");
       }finally{
    	   pw.flush();
    	   reqdis.include(req, resp);
    	   
       }
        
    }
}
