package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository ;

    @GetMapping("")
    public String displayProjectList(Model model){
        List<Project> projectList = projectRepository.findAll() ;
        model.addAttribute("projects" , projectList );
        return "project/list-project";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project project = new Project();
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("project", project);
        model.addAttribute("AllEmployees" , employees);
        return "project/new-project";
    }

    @PostMapping("/save")
    public String saveProject(Project project, Model model) {
        projectRepository.save(project);

        return "redirect:/projects/new";
    }
}
