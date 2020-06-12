package com.example.voteapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@Document(collection = "votes")
public class Vote {
    @Id
    private @Getter @Setter String id;
    private @NonNull  @Getter @Setter String title;
    private @NonNull @Getter @Setter String type;
    private @NonNull @Getter @Setter Date startDate;
    private @NonNull @Getter @Setter Date endDate;
    private @NonNull @Getter @Setter String description;
    private @NonNull @Getter @Setter boolean canChangeVote;
    private @NonNull @Getter @Setter boolean anonym;
    private @NonNull @Getter @Setter boolean voteWithPriority;
    private @NonNull @Getter @Setter String createdBy;
    private @NonNull @Getter @Setter  ArrayList<String> voteOptions;


    public Vote(){}

    public Vote(
            String id,
            String title,
            String type,
            Date startDate,
            Date endDate,
            String description ,
            boolean canChangeVote ,
            boolean anonym ,
            boolean voteWithPriority,
            String createdBy){
        setId(id);
        setTitle(title);
        setType(type);
        setStartDate(startDate);
        setEndDate(endDate);
        setDescription(description);
        setCanChangeVote(canChangeVote);
        setAnonym(anonym);
        setVoteWithPriority(voteWithPriority);
        setCreatedBy(createdBy);

    }

}
