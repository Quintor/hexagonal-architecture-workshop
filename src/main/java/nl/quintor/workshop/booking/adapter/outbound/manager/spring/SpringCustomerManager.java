//package nl.quintor.workshop.booking.adapter.outbound.manager.spring;
//
//import lombok.RequiredArgsConstructor;
//import nl.quintor.workshop.booking.domain.port.outbound.CustomerManager;
//import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
//import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
//import nl.quintor.workshop.customer.domain.port.inbound.CustomerApi;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class SpringCustomerManager implements CustomerManager {
//    private final CustomerApi customerApi;
//    private final SpringCustomerMapper springCustomerMapper;
//
//    @Override
//    public GetOrCreateCustomerResponse getOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest) {
//        var command = springCustomerMapper.toCommand(getOrCreateCustomerRequest);
//        var reply = customerApi.getOrCreateCustomer(command);
//
//        return springCustomerMapper.fromReply(reply);
//    }
//}
