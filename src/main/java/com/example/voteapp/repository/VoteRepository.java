package com.example.voteapp.repository;

import com.example.voteapp.domain.User;
import com.example.voteapp.domain.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {
}
