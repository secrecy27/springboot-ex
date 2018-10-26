package com.example.demo.controller;

import com.example.demo.domain.Team;
import com.example.demo.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ApiController(RepositoryService repositoryService){
        this.repositoryService=repositoryService;
    }

    @GetMapping("/list")
    public List<Team> list(){
        return repositoryService.findTeamAll();
    }
}
