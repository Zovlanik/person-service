package com.example.person_service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("individuals")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Individual implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID userId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String passportNumber;
    private String phoneNumber;
    private String email;
    private LocalDateTime verifiedAt;
    private LocalDateTime archivedAt;
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
