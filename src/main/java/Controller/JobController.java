package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.JobEntity;
import Service.JobService;
import Service.ProjectService;
import Service.UserService;

@WebServlet(name="jobController", urlPatterns = {"/task", "/task-add"})
public class JobController extends HttpServlet{
	private JobService jobService = new JobService();
	private ProjectService projectService = new ProjectService();
	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setAttribute("listJob", jobService.getAllJob());
		req.setAttribute("listProject", projectService.getAllProject());
		req.setAttribute("listUser", userService.getAllUsers());
		
		switch (path) {
		case "/task":
			
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			break;
		case"/task-add":
			
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		break;

		default:
			break;
		}
		
		
	}
	
	
	
}
