package com.example.voteapp.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public class VoteParams {
    @Id
    private @Getter @Setter String id;
    private @Getter @Setter String vote_id;
    private @Getter @Setter ArrayList<String> user_ids;
    private @Getter @Setter  String option;

    public VoteParams(){}
    public VoteParams(String id,String vote_id, String option){
        setId(id);
        setVote_id(vote_id);
        setOption(option);
    }
}
