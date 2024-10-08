package com.example.person_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("profile_history")
@Builder
public class ProfileHistory implements Persistable<UUID> {
    @Id
    private UUID id;
    private LocalDateTime created;
    private UUID profileId; //person.users.id
    private String reason;
    private String comment;
    private String changedValues;


    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
    @Override
    public UUID getId() {
        return id;
    }

}
