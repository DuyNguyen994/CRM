package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.ProjectEntity;
import Entity.UserEntity;
import Service.ProjectService;

@WebServlet(name="projectController", urlPatterns = {"/groupwork","/groupwork-add"})
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
//		req.setAttribute("listProject", projectService.getAllProject());
		
		switch (path) {
		case "/groupwork":
			List<ProjectEntity> listProject = projectService.getAllProject();
			
			req.setAttribute("listProject", listProject);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			break;
		case "/groupwork-add":
			
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;

		default:
			break;
		}
		
		
	}
	
}
