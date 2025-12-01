import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import client.StorageClient;
/*
 * Smoke test for the StorageClient to test the network based implementation of DataStorageAPI
 */
public class TestStorageClient {
    private StorageClient storageClient;
    // Sets up the test environment before each test method run
    @BeforeEach
    public void setUp() {
        storageClient = new StorageClient("localhost",50052);
    }

    // Smoke test to check if client object is created
    @Test
    public void testClient() {	
        assertNotNull(storageClient,"The client shouldn't be null");
        storageClient.shutdown();
    }
}