import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Sign_up")
public class Sign_up extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String username = request.getParameter("username");
        String dob = request.getParameter("dob");
        String password = request.getParameter("password");
        String place = request.getParameter("place");

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_new", "root", "root1234");
			Statement st = conn.createStatement();

            final String insert_query = "insert into login_new.login(name, username, dob, password, place) values(?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(insert_query);
				ps.setString(1, name);
				ps.setString(2, username);
                ps.setString(3, dob);
                ps.setString(4, password);
                ps.setString(5, place);
				ps.executeUpdate();
				response.sendRedirect("home.html");
			st.close();
			conn.close();
		} catch (Exception e) {
            out.print(e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
