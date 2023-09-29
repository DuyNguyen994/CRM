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

import Entity.JobEntity;
import Entity.StatusEntity;
import Entity.UserEntity;
import Service.JobService;
import payload.response.BaseResponse;

@WebServlet(name="apiJobController", urlPatterns = {"/api/task", "/api/task/add", "/api/task/delete", "/api/task/update", "/api/task/task"})
public class ApiJobController extends HttpServlet {
	private JobService jobService = new JobService();
	private BaseResponse response = new BaseResponse();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<JobEntity> listJobs = jobService.getAllJob();
		response.setStatusCode(200);
		response.setMessage("chay");
		response.setData(listJobs);

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
		case "/api/task/add":
			JobEntity jobAdd = new JobEntity();
			jobAdd.setName(req.getParameter("nameJob"));
			jobAdd.setStartDate(req.getParameter("startDate"));
			jobAdd.setEndDate(req.getParameter("endDate"));
			jobAdd.setIdProject(Integer.parseInt(req.getParameter("idProject")));

			UserEntity userAdd = new UserEntity();
			userAdd.setId(Integer.parseInt(req.getParameter("idUser")));
			jobAdd.setUser(userAdd);
			
			StatusEntity statusAdd = new StatusEntity();
			statusAdd.setId(Integer.parseInt(req.getParameter("idStatus")));
			jobAdd.setStatus(statusAdd);
			
			isSuccess = jobService.addJob(jobAdd);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			break;
		
		case "/api/task/delete":
			
			int idDelete = Integer.parseInt(req.getParameter("id"));
			isSuccess = jobService.delJob(idDelete);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Xóa Thành Công" : "Xóa Thất Bại!!!");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			break;
		case "/api/task/update":
			
			JobEntity jobUpdate = new JobEntity();
			jobUpdate.setId(Integer.parseInt(req.getParameter("idJob")));
			jobUpdate.setName(req.getParameter("nameJob"));
			jobUpdate.setStartDate(req.getParameter("startDate"));
			jobUpdate.setEndDate(req.getParameter("endDate"));
			jobUpdate.setIdProject(Integer.parseInt(req.getParameter("idProject")));

			UserEntity userUpdate = new UserEntity();
			userUpdate.setId(Integer.parseInt(req.getParameter("idUser")));
			jobUpdate.setUser(userUpdate);
			
			StatusEntity statusUpdate = new StatusEntity();
			statusUpdate.setId(Integer.parseInt(req.getParameter("idStatus")));
			jobUpdate.setStatus(statusUpdate);
			
			isSuccess = jobService.updateJob(jobUpdate);

			response.setStatusCode(200);
			response.setMessage(isSuccess ? "update thanh cong" : " update that bai");
			response.setData(isSuccess);

			dataJson = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
			out.flush();
			break;
			
		case "/api/task/task":
			int id = Integer.parseInt(req.getParameter("id"));
			JobEntity job = jobService.getJob(id);
			
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(job);
			
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
