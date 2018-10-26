package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.example.demo.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/index")
@Controller
public class IndexController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    public IndexController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }


    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("title", "create your team");
        model.addAttribute("team", new Team());
        return "form";
    }

    @GetMapping
    public String index() {
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
    public String insert(@ModelAttribute Member member, @RequestParam int seq, Model model) {
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

    @GetMapping(value = "/read/{id:[\\d]+}")
    public String detail(HttpServletResponse response, @PathVariable int id, Model model) throws Exception {
        Team team = repositoryService.findTeamBySeq(id);

        model.addAttribute("result", team);

        return "detail";
    }

    @PutMapping(value = "/update/{id:[\\d]+}")
    @ResponseBody
    public Boolean update(@RequestBody MultiValueMap<String, String> teamName, @PathVariable int id) {
        Team teamContent = repositoryService.findTeamBySeq(id);
        System.out.println("team content : " + teamContent.getTeamName());
        System.out.println("team seq : " + teamName.getFirst("teamName"));
        teamContent.setTeamName(teamName.getFirst("teamName"));
        repositoryService.addTeam(teamContent);
        return Boolean.TRUE;
    }
//    @PutMapping(value = "/update/{id:[\\d]+}")
//    @ResponseBody
//    public Boolean update(@RequestBody MultiValueMap<String, String> team, @PathVariable int id) {
//        Team teamContent = repositoryService.findTeamBySeq(id);
//        System.out.println("team content : " + teamContent.getTeamName());
//        System.out.println("team seq : " + team.getFirst("teamName"));
////        System.out.println("team name : " + team.getTeamName());
//        teamContent.setTeamName(team.getFirst("teamName"));
//        repositoryService.addTeam(teamContent);
//        return Boolean.TRUE;
//    }

    @DeleteMapping(value = "/delete/{id:[\\d]+}")
    @ResponseBody
    public Boolean remove(@PathVariable int id, @ModelAttribute Team team, @ModelAttribute Member member) {
        System.out.println("team ; "+team.getTeamName());
        repositoryService.removeTeam(id);
        return Boolean.TRUE;
    }

}
