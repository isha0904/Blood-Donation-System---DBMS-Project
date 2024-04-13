package oops;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
//import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.*;

@WebServlet("/LoginServlet")
public class loginserv extends HttpServlet {
	private static final String query="insert into login(login,password) values (?,?)";
	private static final long serialVersionUID = 1L;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
  	   PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			String name=request.getParameter("txtName");
			String pass=request.getParameter("txtPwd");
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				
			}
			try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prac","root","CHaitu@54");
					PreparedStatement ps=con.prepareStatement(query);){
				ps.setString(1, name);
				ps.setString(2, pass);
				int count =ps.executeUpdate();
				if(count==1) {
				out.println("<h2>REGISTERED SUCESSFULLY</h2>");
				}else {
					out.println("<h2>NOT REGISTERED SUCESSFULLY</h2>");
				}
			} catch (SQLException se) {
				
				se.printStackTrace();
				out.println("<h1>"+se.getMessage()+"</h1>");
				
			
			}catch(Exception e) {
				e.printStackTrace();
				out.println("<h1>"+e.getMessage()+"</h1>");

			}
     
	 }
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}
}
