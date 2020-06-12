package com.example.voteapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.voteapp.domain.PriorityVoteParams;
import com.example.voteapp.domain.User;
import com.example.voteapp.domain.Vote;
import com.example.voteapp.domain.VoteParams;
import com.example.voteapp.repository.PriorityVoteParamsRepository;
import com.example.voteapp.repository.UserRepository;
import com.example.voteapp.repository.VoteParamsRepository;
import com.example.voteapp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class VoteController{

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    PriorityVoteParamsRepository priorityVoteParamsRepository;

    @Autowired
    VoteParamsRepository voteParamsRepository;

    @GetMapping("/vote")
    public ArrayList<Vote> getAllVotes(){
        ArrayList<Vote> votes = (ArrayList<Vote>) voteRepository.findAll();

        return votes;
    }

    @PutMapping("/make_vote/{vote_id}")
    public Optional<VoteParams> updateEmployee(@RequestBody VoteParams newVote, @PathVariable String vote_id){
        Optional<VoteParams> optional = voteParamsRepository.findById(vote_id);
        if (optional.isPresent()) {
            VoteParams vote = optional.get();
            vote.setUser_ids(newVote.getUser_ids());
            voteParamsRepository.save(vote);
        }
        return optional;
    }

    @GetMapping("/vote/priority_params")
    public ArrayList<PriorityVoteParams> getPriorityVoteParams(){
        ArrayList<PriorityVoteParams> params = (ArrayList<PriorityVoteParams>) priorityVoteParamsRepository.findAll();
        return params;
    }

    @GetMapping("/vote/params")
    public ArrayList<VoteParams> getVoteParams(){
        ArrayList<VoteParams> params = (ArrayList<VoteParams>) voteParamsRepository.findAll();
        return params;
    }

    @PostMapping("/vote")
    public Vote newVote(@RequestBody Vote newVote){
        String id = String.valueOf(new Random().nextInt());
        Vote vote = new Vote(
                id,
                newVote.getTitle(),
                newVote.getType(),
                newVote.getStartDate(),
                newVote.getEndDate(),
                newVote.getDescription(),
                newVote.isCanChangeVote(),
                newVote.isAnonym(),
                newVote.isVoteWithPriority(),
                newVote.getCreatedBy());
        voteRepository.insert(vote);

        if(vote.isVoteWithPriority())  {
            savePriotiyVoteOptions(newVote.getVoteOptions(),vote.getId());
        } else {
            saveVoteOptions(vote.getId());
        }

        return vote;
    }


    private void savePriotiyVoteOptions(ArrayList<String> voteOptions , String voteId){
        voteOptions.forEach(option -> {
            String id = String.valueOf(new Random().nextInt());
            PriorityVoteParams newPriorityVoteOption = new PriorityVoteParams(id,voteId,option);
            priorityVoteParamsRepository.insert(newPriorityVoteOption);
        });
    }


    private void saveVoteOptions(String voteId){
        VoteParams yesOption= new VoteParams(String.valueOf(new Random().nextInt()), voteId , "Підтримую");
        VoteParams noOption= new VoteParams(String.valueOf(new Random().nextInt()), voteId , "Не підтримую");
        voteParamsRepository.insert(yesOption);
        voteParamsRepository.insert(noOption);

    }
}