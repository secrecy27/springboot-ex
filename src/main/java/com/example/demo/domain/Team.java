package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @Column(name="seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;

    @Column(name="team_name")
    private String teamName;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "team", fetch=FetchType.LAZY)
    private Collection<Member> member;

    public Team(String teamName){
        this.teamName=teamName;
    }

    public Team() {

    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Collection<Member> getMember() {
        return member;
    }

    public void setMember(Collection<Member> member) {
        this.member = member;
    }
}