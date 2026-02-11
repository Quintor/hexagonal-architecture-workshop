package nl.quintor.workshop.booking.outbound.service;

import nl.quintor.workshop.booking.domain.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.outbound.GetOrCreateCustomerResponse;
import nl.quintor.workshop.customer.domain.inbound.GetOrCreateCustomerCommand;
import nl.quintor.workshop.customer.domain.inbound.GetOrCreateCustomerReply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerInternalMapper {

    GetOrCreateCustomerCommand toCommand(GetOrCreateCustomerRequest request);

    GetOrCreateCustomerResponse fromReply(GetOrCreateCustomerReply reply);
}
