package nl.quintor.workshop.infrastructure.adapters.outbound.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "ORDERS")
@Entity
public class OrderEntity {
    @Id
    long id;
    String omschrijving;
}

