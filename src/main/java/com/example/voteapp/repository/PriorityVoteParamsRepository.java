package com.example.voteapp.repository;

import com.example.voteapp.domain.PriorityVoteParams;
import com.example.voteapp.domain.User;
import com.example.voteapp.domain.VoteParams;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface PriorityVoteParamsRepository extends MongoRepository<PriorityVoteParams, String> {
}
