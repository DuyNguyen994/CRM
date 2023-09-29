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

import Entity.ProjectEntity;
import Service.ProjectService;
import payload.response.BaseResponse;

@WebServlet(name="apiProject", urlPatterns = {"/api/groupwork", "/api/groupwork/add", "/api/groupwork/update","/api/groupwork/delete", "/api/groupwork/groupwork"})
public class ApiProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectService();
	private Gson gson = new Gson();
	private BaseResponse response = new BaseResponse();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<ProjectEntity> listProject = projectService.getAllProject();
		response.setStatusCode(200);
		response.setMessage("");
		response.setData(listProject);

		String dataJson = gson.toJson(response);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		out.print(dataJson);
		out.flush();		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		PrintWriter out = resp.getWriter();
		String dataJson;
		boolean isSuccess;
		
		switch (path) {
		case "/api/groupwork/add":
			ProjectEntity projectAdd = new ProjectEntity();
			projectAdd.setName(req.getParameter("name"));
			projectAdd.setStartDate(req.getParameter("startDate"));
			projectAdd.setEndDate(req.getParameter("endDate"));
			isSuccess = projectService.addProject(projectAdd);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Thêm Thành công" : "Thêm Thất Bại");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			
			break;
			
		case "/api/groupwork/update":
			ProjectEntity projectUpdate = new ProjectEntity();
			projectUpdate.setId(Integer.parseInt(req.getParameter("id")));
			projectUpdate.setName(req.getParameter("name"));
			projectUpdate.setStartDate(req.getParameter("startDate"));
			projectUpdate.setEndDate(req.getParameter("endDate"));
			isSuccess = projectService.updateProject(projectUpdate);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Sửa Thành công" : "Sửa Thất Bại");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
				
			break;
		
		case "/api/groupwork/delete":
			
			int idDelete = Integer.parseInt(req.getParameter("id"));
			isSuccess = projectService.deleteProject(idDelete);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Xóa Thành công" : "Xóa Thất Bại");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			
			break;
			
		case "/api/groupwork/groupwork":
			int id = Integer.parseInt(req.getParameter("id"));
			ProjectEntity project = projectService.getProject(id);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(project);

			dataJson = gson.toJson(response);
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
