package com.example.demo.service.impl;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.example.demo.repository.board.MemberRepository;
import com.example.demo.repository.board.TeamRepository;
import com.example.demo.service.RepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    public RepositoryServiceImpl(){

    }

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team findTeamBySeq(int seq) {
        return teamRepository.getOne(seq);
    }

    @Override
    public Member addMember(Member member, int seq) {
        return memberRepository.save(member);
    }

    @Override
    public List<Team> findTeamAll() {
        return teamRepository.findAll();
    }

    public void removeTeam(int id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Member removeMember(Member member) {
        return null;
    }
}
