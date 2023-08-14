package com.amin.ojrat.entity;

import com.amin.ojrat.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "expert_table")


public class Expert extends User {

    @ManyToMany
    @JoinTable(
            name = "expert_branch",
            joinColumns = @JoinColumn(name = "expert_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    private List<Branch> branches;

    @OneToMany(mappedBy = "expert")
     private List<ExpertBranchRequest> branchRequests;

    public Expert() {
    }

    public Expert(Long id, String firstName, String lastName,
                  String email, String password, String nationalCode,
                  String address, String phoneNumber, List<Message> sentMessages,
                  List<Message> receivedMessages, LocalTime createdAt, List<Branch> branches, List<ExpertBranchRequest> branchRequests) {
        super(id, firstName, lastName, email, password, nationalCode,
                address, phoneNumber, sentMessages, receivedMessages, createdAt);
        this.branches = branches;
        this.branchRequests = branchRequests;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<ExpertBranchRequest> getBranchRequests() {
        return branchRequests;
    }

    public void setBranchRequests(List<ExpertBranchRequest> branchRequests) {
        this.branchRequests = branchRequests;
    }
}
