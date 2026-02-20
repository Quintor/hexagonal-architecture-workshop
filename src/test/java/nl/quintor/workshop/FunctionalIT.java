package nl.quintor.workshop;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class FunctionalIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @BeforeEach
    void setUp() {
        if (mockMvc == null) {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }
        clearDatabase();
    }

    @Test
    void createNewBooking_ValidBooking_StoresBookingInDB() throws Exception {
        // Arrange
        String bookingJson = """
                {
                    "customerPhoneNumber": "0612345678",
                    "dateTime": "2026-01-01T00:00:00",
                    "fromLocation": "Amersfoort",
                    "toLocation": "Apeldoorn",
                    "numberOfPassengers": 2
                }
                """;

        // Act
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookingJson))
                .andExpect(status().isCreated());

        // Assert
        Object[] bookingData = (Object[]) transactionTemplate.execute(status -> entityManager.createQuery(
                "SELECT b.fromLocation, b.toLocation, b.numberOfPassengers FROM BookingEntity b")
                .getSingleResult());

        assertEquals("Amersfoort", bookingData[0]);
        assertEquals("Apeldoorn", bookingData[1]);
        assertEquals((byte) 2, bookingData[2]);
    }

    @Test
    void createNewBooking_InvalidBooking_StoresNoBookingInDB() throws Exception {
        // Arrange
        // From location incorrectly the same as to location
        String bookingJson = """
                {
                    "customerPhoneNumber": "0612345678",
                    "dateTime": "2026-01-01T00:00:00",
                    "fromLocation": "Amersfoort",
                    "toLocation": "Amersfoort",
                    "numberOfPassengers": 2
                }
                """;

        // Act
        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().is4xxClientError());

        // Assert
        Long bookingCount = transactionTemplate.execute(status -> (Long) entityManager.createQuery(
                "SELECT COUNT(b) FROM BookingEntity b").getSingleResult());
        assertEquals(0L, bookingCount);

    }


    @Test
    void getAllCustomers_afterCreatedAValidBooking_ReturnsMatchingCustomer() throws Exception {
        // Arrange
        String bookingJson = """
                {
                    "customerPhoneNumber": "0612345678",
                    "dateTime": "2026-01-01T00:00:00",
                    "fromLocation": "Amersfoort",
                    "toLocation": "Apeldoorn",
                    "numberOfPassengers": 1
                }
                """;

        // Act
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookingJson))
                .andExpect(status().isCreated());

        // Assert
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].phoneNumber").value("0612345678"));
    }

    @Test
    void getAllCustomers_afterCreatingTwoBookingsForSameCustomer_ReturnsMatchingCustomer() throws Exception {
        // Arrange
        String bookingJson1 = """
                {
                    "customerPhoneNumber": "0611111111",
                    "dateTime": "2026-01-01T00:00:00",
                    "fromLocation": "Amersfoort",
                    "toLocation": "Apeldoorn",
                    "numberOfPassengers": 1
                }
                """;

        String bookingJson2 = """
                {
                    "customerPhoneNumber": "0611111111",
                    "dateTime": "2026-01-01T00:00:00",
                    "fromLocation": "Amersfoort",
                    "toLocation": "Apeldoorn",
                    "numberOfPassengers": 2
                }
                """;

        // Act
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookingJson1))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookingJson2))
                .andExpect(status().isCreated());

        // Assert
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].phoneNumber").value("0611111111"));
    }

    @Test
    void getAllCustomers_afterCreatingTwoBookingsForDifferentCustomers_ReturnsTwoMatchingCustomers() throws Exception {
        // Arrange
        String bookingJson1 = """
                {
                    "customerPhoneNumber": "0622222222",
                    "dateTime": "2026-01-01T00:00:00",
                    "fromLocation": "Amersfoort",
                    "toLocation": "Apeldoorn",
                    "numberOfPassengers": 1
                }
                """;

        String bookingJson2 = """
                {
                    "customerPhoneNumber": "0633333333",
                    "dateTime": "2026-01-01T00:00:00",
                    "fromLocation": "Amersfoort",
                    "toLocation": "Apeldoorn",
                    "numberOfPassengers": 2
                }
                """;

        // Act
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookingJson1))
                .andExpect(status().isCreated());
        
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookingJson2))
                .andExpect(status().isCreated());

        // Assert
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].phoneNumber").value("0622222222"))
                .andExpect(jsonPath("$[1].phoneNumber").value("0633333333"));
    }

    @Test
    void clearDatabase_OnBeforeEach_CustomersAndBookingsShouldBeEmpty() {
        Long bookingCount = transactionTemplate.execute(status -> (Long) entityManager.createQuery(
                "SELECT COUNT(b) FROM BookingEntity b").getSingleResult());

        Long customerCount = transactionTemplate.execute(status -> (Long) entityManager.createQuery(
                "SELECT COUNT(c) FROM CustomerEntity c").getSingleResult());

        assertEquals(0L, bookingCount);
        assertEquals(0L, customerCount);
    }

    private void clearDatabase() {
        transactionTemplate.executeWithoutResult(status -> {
            entityManager.createQuery("DELETE FROM BookingEntity").executeUpdate();
            entityManager.createQuery("DELETE FROM CustomerEntity").executeUpdate();
            entityManager.flush();
        });
    }
}
