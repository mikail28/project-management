package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projRepository ;

    @Autowired
    EmployeeRepository emplRepository ;

    @GetMapping("/")
    public String displayHome(Model model){
        List<Project> projects = projRepository.findAll();
        List<EmployeeProject> employeesProjectCnt = emplRepository.employeeProjects();
        model.addAttribute("projects" , projects ) ;
        model.addAttribute("employeesProjectCnt",employeesProjectCnt);

        return "/main/home" ;
    }

}
