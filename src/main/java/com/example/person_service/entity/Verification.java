package com.example.person_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("verification_statuses")
public class Verification implements Persistable<UUID> {
    @Id
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private UUID profileId;
    private String profileType;
    private String details;
    private String verificationStatus;


    @Override
    public boolean isNew() {
        return id == null;
    }
    @Override
    public UUID getId() {
        return id;
    }
}
