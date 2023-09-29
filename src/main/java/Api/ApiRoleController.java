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

import Entity.RoleEntity;
import Service.RoleService;

import payload.response.BaseResponse;

@WebServlet(name="apiRoleController", urlPatterns = {"/api/role", "/api/role/add", "/api/role/delete", "/api/role/update", "/api/role/role"})
public class ApiRoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	private Gson gson = new Gson();
	private BaseResponse response = new BaseResponse();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<RoleEntity> listRoles = roleService.getAllRole();
		response.setStatusCode(200);
		response.setMessage("");
		response.setData(listRoles);

		String data = gson.toJson(response);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		out.print(data);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		PrintWriter out = resp.getWriter();
		String data;
		boolean isSuccess;
		switch (path) {
		case "/api/role/add":
			RoleEntity roleAdd = new RoleEntity();
			roleAdd.setName(req.getParameter("name"));
			roleAdd.setDescription(req.getParameter("desc"));
			isSuccess = roleService.addRole(roleAdd);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Thêm Thành công" : "Thêm Thất Bại");
			response.setData(isSuccess);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/role/delete":
			int idDelete = Integer.parseInt(req.getParameter("id"));
			isSuccess = roleService.deleteRole(idDelete);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Xóa Thành công" : "Xóa Thất Bại");
			response.setData(isSuccess);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/role/update":
			RoleEntity roleUpdate = new RoleEntity();
			roleUpdate.setId(Integer.parseInt(req.getParameter("id")));
			roleUpdate.setName(req.getParameter("name"));
			roleUpdate.setDescription(req.getParameter("desc"));

			isSuccess = roleService.updateRole(roleUpdate);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Sửa Thành công" : "Sửa Thất Bại");
			response.setData(isSuccess);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/role/role":
			int id = Integer.parseInt(req.getParameter("id"));
			RoleEntity role = roleService.getRole(id);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(role);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;

		default:
			break;
		}
	}
}
