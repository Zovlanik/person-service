package com.example.person_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("merchant_members_invitations")
public class MerchantMemberInvitation  implements Persistable<UUID> {
    @Id
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime expires;
    private UUID merchantId;
    private String firstName;
    private String lastName;
    private String email;
    private String status;


    @Override
    public boolean isNew() {
        return id == null;
    }
    @Override
    public UUID getId() {
        return id;
    }
}
