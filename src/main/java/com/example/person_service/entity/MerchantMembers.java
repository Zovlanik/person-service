package com.example.person_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("merchant_members")
public class MerchantMembers implements Persistable<java.util.UUID> {
    @Id
    private UUID id;
    private UUID userId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private UUID merchantId;
    private String memberRole;
    private String status;


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
