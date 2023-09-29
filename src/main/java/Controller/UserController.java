package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Entity.RoleEntity;
import Entity.UserEntity;
import Service.RoleService;
import Service.UserService;
import payload.response.BaseResponse;

@WebServlet(name="userController", urlPatterns = {"/user","/user-add","/user-detail"})
public class UserController extends HttpServlet {
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private BaseResponse response = new BaseResponse();
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
	
		req.setAttribute("listUser", userService.getAllUsers());
		req.setAttribute("listRole", roleService.getAllRole());
		
		switch (path) {
		case "/user":
			List<UserEntity> listUser = userService.getAllUsers();
			
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			
			break;
			
		case "/user-add":
			
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			
			break;	
			
		case "/user-detail":
			
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
			
			break;
			
		default:
			
			break;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/user":
			
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isDelete = userService.deleteUser(id);
			
			req.setAttribute("isDelete", isDelete);
			
			doGet(req, resp);
			
			break;
		case "/user-add":
			UserEntity userAdd = new UserEntity();
			userAdd.setFullName(req.getParameter("fullname").trim());
			userAdd.setEmail(req.getParameter("email"));
			userAdd.setPwd(req.getParameter("pwd"));
			userAdd.setPhone(req.getParameter("phone"));
			userAdd.setIdRole(Integer.parseInt(req.getParameter("role")));
			
			boolean isSuccess = userService.addUser(userAdd);
			
			List<RoleEntity> list = new ArrayList<RoleEntity>();
			list = userService.getAllRole();
			
			req.setAttribute("isSuccess", isSuccess);
			req.setAttribute("listRole", list);				
			doGet(req, resp);
			break;
		
			
		default:
			break;
		}
		
		
	}
}
