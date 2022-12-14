package magar.atul.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import magar.atul.model.Employee;
import magar.atul.util.HibernateSessionUtil;




@WebServlet("/list-employees")
public class ListEmployee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//set up content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// include nav bar
		request.getRequestDispatcher("index.jsp").include(request, response);

		// build hibernate session
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();

			// 2. create a session
			Session session = factory.openSession();
			
			// 3. read employee
			List<Employee> listOfEmps = session.createQuery("from Employee as e").list();
			
			out.print("<h1> Employee List :- </h1>");
			out.print("<style> table,td,th {"
					+ "border:2px solid red;"
					+ "padding: 10px; "
					+ "}</style>");
			
				out.print("<table >");
					out.print("<tr>");
					out.print("<th> Id</th>");
					out.print("<th> First Name</th>");
					out.print("<th> Last Name </th>");
					out.print("<th> Salary</th>");
					out.print("<th> Department</th>");
					out.print("<th> Paroll</th>");
				out.print("</tr>");
			for(Employee emp :listOfEmps) {
				out.print("<tr>");
					out.print("<td>"+emp.getId()+"</td>");
					out.print("<td>"+emp.getFirstName()+"</td>");
					out.print("<td>"+emp.getLastName()+"</td>");
					out.print("<td>"+emp.getSalary()+"</td>");
					out.print("<td>"+emp.getDept()+"</td>");
					out.print("<td>"+emp.getPayroll().toString()+"</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			// close session.
			session.close();
		} catch (Exception e) {
			out.print("Exception Occurs !"+ e.toString());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
