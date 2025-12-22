package fr.sidranie.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class MockedTestCase {

    AutoCloseable autoCloseable;

    @BeforeEach
    public void setupMocks() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void cleanMocks() throws Exception {
        autoCloseable.close();
    }
}
