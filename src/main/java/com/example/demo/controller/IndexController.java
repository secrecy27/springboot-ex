package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.example.demo.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/index")
@Controller
public class IndexController {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    public IndexController(RepositoryService repositoryService) {
        this.repositoryService=repositoryService;
    }


    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("title", "create your team");
        model.addAttribute("team", new Team());
        return "form";
    }

    @GetMapping
    public String index(){
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Team team, Model model) {
        Team entity = repositoryService.addTeam(team);

        model.addAttribute("result", entity);
        return "result";
    }

    @GetMapping("/addUser/{seq}")
    public String addUser(@PathVariable int seq, Model model) {
        Team team = repositoryService.findTeamBySeq(seq);
        model.addAttribute("title", "add user");
        model.addAttribute("team", team);
        model.addAttribute("member", new Member());

        return "addUser";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Member member, @RequestParam int seq,  Model model) {
        Member entity = repositoryService.addMember(member, seq);

        model.addAttribute("result", entity);
        return "user";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Team> teamList = repositoryService.findTeamAll();
        model.addAttribute("teamList", teamList);

        return "list";
    }

    @DeleteMapping(value = "/{id:[\\d]+}")
    @ResponseBody
    public String remove(@PathVariable int id, @ModelAttribute Team team, @ModelAttribute Member member){
        repositoryService.removeTeam(id);
        return "list";
    }

}
