package com.example.person_service.mapper.profilehistorymapper;

import com.example.person_service.entity.Individual;
import org.javers.core.diff.Diff;

import java.time.LocalDateTime;
import java.util.UUID;

public class IndividualHistoryMapper {

    public Individual map(Diff diff) {

        Individual.IndividualBuilder builder = Individual.builder();

        if (!diff.getPropertyChanges("userId").isEmpty()) {
            builder.userId(UUID.fromString(diff.getPropertyChanges("userId").getFirst().getRight().toString()));
        }
        if (!diff.getPropertyChanges("created").isEmpty()) {
            builder.created((LocalDateTime) diff.getPropertyChanges("created").getFirst().getRight());
        }
        if (!diff.getPropertyChanges("updated").isEmpty()) {
            builder.updated((LocalDateTime) diff.getPropertyChanges("updated").getFirst().getRight());
        }
        if (!diff.getPropertyChanges("passportNumber").isEmpty()) {
            builder.passportNumber((String) diff.getPropertyChanges("passportNumber").getFirst().getRight());
        }
        if (!diff.getPropertyChanges("phoneNumber").isEmpty()) {
            builder.phoneNumber((String) diff.getPropertyChanges("phoneNumber").getFirst().getRight());
        }
        if (!diff.getPropertyChanges("email").isEmpty()) {
            builder.email((String) diff.getPropertyChanges("email").getFirst().getRight());
        }
        if (!diff.getPropertyChanges("verifiedAt").isEmpty()) {
            builder.verifiedAt((LocalDateTime) diff.getPropertyChanges("verifiedAt").getFirst().getRight());
        }
        if (!diff.getPropertyChanges("archivedAt").isEmpty()) {
            builder.archivedAt((LocalDateTime) diff.getPropertyChanges("archivedAt").getFirst().getRight());
        }
        if (!diff.getPropertyChanges("status").isEmpty()) {
            builder.status((String) diff.getPropertyChanges("status").getFirst().getRight());
        }

        return builder.build();
    }
}

