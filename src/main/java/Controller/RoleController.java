package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.RoleService;

@WebServlet(name= "roleController", urlPatterns = {"/role", "/role-add"})
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();	
		req.setAttribute("listRole", roleService.getAllRole());
		switch (path) {
		case "/role":
			
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		case "/role-add":
			
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}
