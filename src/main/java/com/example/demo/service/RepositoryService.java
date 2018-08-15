package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;

import java.util.List;

public interface RepositoryService {

    Team addTeam(Team team);

    Team findTeamBySeq(int seq);

    Member addMember(Member member, int seq);

    void removeTeam(int id);

    Member removeMember(Member member);

    List<Team> findTeamAll();
}
