package nl.quintor.workshop.booking.adapter.outbound.manager.spring;

import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerCommand;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerReply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpringCustomerMapper {

    GetOrCreateCustomerCommand toCommand(GetOrCreateCustomerRequest request);

    GetOrCreateCustomerResponse fromReply(GetOrCreateCustomerReply reply);
}
