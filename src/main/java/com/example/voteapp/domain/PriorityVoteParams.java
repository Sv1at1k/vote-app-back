package com.example.voteapp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;

public class PriorityVoteParams {
    @Id
    private @Getter @Setter String id;
    private @Getter @Setter String vote_id;
    private @Getter @Setter HashMap<String,String> user_priorities;
    private @Getter @Setter  String option;

    public PriorityVoteParams(){}
    public PriorityVoteParams(String id,String vote_id, String option){
        setId(id);
        setVote_id(vote_id);
        setOption(option);
    }

}
