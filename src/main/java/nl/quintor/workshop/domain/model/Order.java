package nl.quintor.workshop.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {
    long id;
    String omschrijving;
}
