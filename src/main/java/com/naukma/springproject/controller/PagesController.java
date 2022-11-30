package com.naukma.springproject.controller;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.entity.ProjectEntity;
import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.model.Organization;
import com.naukma.springproject.model.Pair;
import com.naukma.springproject.model.User;
import com.naukma.springproject.service.OrganizationService;
import com.naukma.springproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Controller
public class PagesController {

    private final StudentService studentService;
    private final OrganizationService organizationService;

    @Autowired
    public PagesController(StudentService studentService, OrganizationService organizationService) {
        this.studentService = studentService;
        this.organizationService = organizationService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        UserEntity user = studentService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Organization orgToCreate = new Organization();
        Pair<String, String> studentToOrgPair = new Pair<>();
        Pair<String, String> projectToOrgPair = new Pair<>();
        Pair<String, String> studentToProjPair = new Pair<>();

        model.addAttribute("user", user);
        model.addAttribute("orgToCreate", orgToCreate);
        model.addAttribute("studentToOrgPair", studentToOrgPair);
        model.addAttribute("projectToOrgPair", projectToOrgPair);
        model.addAttribute("studentToProjPair", studentToProjPair);

        return "home";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        UserEntity user = studentService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Set<OrganizationEntity> orgs = studentService.getOrganizations(user);
        Map<OrganizationEntity, Set<Pair<ProjectEntity, Long>>> orgsMap = new TreeMap<>();
        for (OrganizationEntity org : orgs) {
            orgsMap.put(org, organizationService.getProjectsWithStudent(org, user));
        }
        model.addAttribute("user", user);
        model.addAttribute("orgsMap", orgsMap);

        return "profile";
    }
}
