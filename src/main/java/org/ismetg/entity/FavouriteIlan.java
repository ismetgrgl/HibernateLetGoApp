package org.ismetg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ismetg.entity.enums.Status;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblfavouriteilan")
public class FavouriteIlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Ilan ilan;
    @Temporal(TemporalType.DATE)
    LocalDate createat;
    @Enumerated(EnumType.STRING)
    Status status;
}
