package routerProtocol.testScripts;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestRunner {

    @Test
    void testParallel() {
        Results results = Runner.path("classpath:routerProtocol/testScripts/testCases.feature")
                .outputCucumberJson(true)
                .parallel(50);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
