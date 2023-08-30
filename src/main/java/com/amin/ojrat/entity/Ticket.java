package com.amin.ojrat.entity;
import com.amin.ojrat.base.BaseEntity;
import com.amin.ojrat.enums.TicketStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket extends BaseEntity<Long> {

    @OneToOne
    private Subject subject;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status=TicketStatus.OPEN;

    @ManyToOne
    private Expert expert;

    @ManyToOne
    private Branch branch;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    private Timestamp lastReadTimestampBranch;

    private Timestamp lastReadTimestampExpert;


    public Ticket() {
    }

    public Timestamp getLastReadTimestampBranch() {
        return lastReadTimestampBranch;
    }

    public void setLastReadTimestampBranch(Timestamp lastReadTimestampBranch) {
        this.lastReadTimestampBranch = lastReadTimestampBranch;
    }

    public Timestamp getLastReadTimestampExpert() {
        return lastReadTimestampExpert;
    }

    public void setLastReadTimestampExpert(Timestamp lastReadTimestampExpert) {
        this.lastReadTimestampExpert = lastReadTimestampExpert;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
