package Api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Entity.UserEntity;
import Repository.UserRepository;
import Service.UserService;
import payload.response.BaseResponse;

@WebServlet(name="apiUserController", urlPatterns = {"/api/user", "/api/user/add", "/api/user/delete", "/api/user/update", "/api/user/user"})
public class ApiUserController extends HttpServlet {
	private UserService userService = new UserService();
	private Gson gson = new Gson();
	private BaseResponse response = new BaseResponse();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserEntity> listUser = userService.getAllUsers();
		response.setStatusCode(200);
		response.setMessage("");
		response.setData(listUser);
		
		String dataJson = gson.toJson(response);
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("application/json,charset=UTF-8");
//		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		out.print(dataJson);
		out.flush();						
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		String dataJson;
		PrintWriter out;
		switch (path) {
		case "/api/user/update":
			UserEntity userUpdate = new UserEntity();
			userUpdate.setId(Integer.parseInt(req.getParameter("id")));
			userUpdate.setFullName(req.getParameter("fullName").trim());
			userUpdate.setEmail(req.getParameter("email"));
			userUpdate.setPwd(req.getParameter("pwd"));
			userUpdate.setPhone(req.getParameter("phone"));
			userUpdate.setIdRole(Integer.parseInt(req.getParameter("idRole")));
			
			boolean isSuccess = userService.updateUser(userUpdate);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Sửa Thành công" : "Sửa Thất Bại");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			
			break;
		case "/api/user/delete":
			int id = Integer.parseInt(req.getParameter("id"));
			isSuccess = userService.deleteUser(id);
			
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Xóa thành công!" : "Xóa thất bại!!!");
			response.setData(isSuccess);
			
			dataJson = gson.toJson(response);
			out = resp.getWriter();
						
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			out.print(dataJson);
			out.flush();
			
			break;
		case "/api/user/add":
			UserEntity userAdd = new UserEntity();
			userAdd.setFullName(req.getParameter("fullName").trim());
			userAdd.setEmail(req.getParameter("email"));
			userAdd.setPwd(req.getParameter("pwd"));
			userAdd.setPhone(req.getParameter("phone"));
			userAdd.setIdRole(Integer.parseInt(req.getParameter("idRole")));
			
			isSuccess = userService.addUser(userAdd);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Thêm Thành công" : "Thêm Thất Bại");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			
			break;
		case "/api/user/user":
			int idUser = Integer.parseInt(req.getParameter("id"));
			UserEntity getUser = userService.getUser(idUser);
			
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(getUser);

			dataJson = gson.toJson(response);
			out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			
			
			break;
		default:
			break;
		}
		
		
	}
	
}
