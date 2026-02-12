package nl.quintor.workshop.customer.domain.port.inbound;

public record GetOrCreateCustomerCommand(String email, String phoneNumber) {
}
