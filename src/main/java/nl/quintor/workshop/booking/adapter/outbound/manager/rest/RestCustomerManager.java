package nl.quintor.workshop.booking.adapter.outbound.manager.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerManager;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestCustomerManager implements CustomerManager {
    private final RestClient restClient;
    private final RestCustomerDtoMapper dtoMapper;

    @Override
    public GetOrCreateCustomerResponse getOrCreateCustomer(GetOrCreateCustomerRequest request) {
        try {
            log.info("[RestCustomerManager] Call naar customer API met phoneNumber: {}", request.phoneNumber());

            var requestDto = new CustomerPostRequestDto(request.name());
            var responseDto = restClient.put()
                    .uri("http://localhost:8080/customers/{phoneNumber}", request.phoneNumber())
                    .body(requestDto)
                    .retrieve()
                    .body(RestCustomerResponseDto.class);

            log.info("[RestCustomerManager] Customer ontvangen van API, ID: {}", responseDto.id());

            return dtoMapper.toGetOrCreateCustomerResponse(responseDto);
        } catch (Exception e) {
            log.error("[RestCustomerManager] Fout bij call naar Customer API", e);
            throw new RuntimeException("An error occurred on the Customer API side", e);
        }
    }
}
