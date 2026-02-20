package nl.quintor.workshop.booking.adapter.outbound.manager.rest;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerManager;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
@RequiredArgsConstructor
public class RestClientCustomerManager implements CustomerManager {
    private final RestClient restClient;
    private final RestCustomerDtoMapper dtoMapper;

    @Override
    public GetOrCreateCustomerResponse getOrCreateCustomer(GetOrCreateCustomerRequest request) {
        try {
            var responseDto = restClient.put()
                    .uri("http://localhost:8080/customers/{phoneNumber}", request.phoneNumber())
                    .retrieve()
                    .body(RestCustomerResponseDto.class);

            return dtoMapper.toGetOrCreateCustomerResponse(responseDto);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred on the Customer API side", e);
        }
    }
}
