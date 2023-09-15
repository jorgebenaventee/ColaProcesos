import models.Process;
import models.ProcessQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProcessQueueTest {
    private ProcessQueue processQueue;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        int capacity = 50;
        Optional<Method> testMethodOptional = testInfo.getTestMethod();
        if (testMethodOptional.isPresent()) {
            Method testMethod = testMethodOptional.get();
            if (testMethod.isAnnotationPresent(ValueSource.class)) {
                ValueSource valueSource = testMethod.getAnnotation(ValueSource.class);
                int[] ints = valueSource.ints();
                capacity = ints[0];
            }
        }
        processQueue = new ProcessQueue(capacity);
    }

    @Test
    public void testRun() {
        Process process = new Process(30, 1, 4);
        processQueue.push(process);
        processQueue.run();
        assertEquals(0, process.getQuantum());
    }

    @Test
    public void testOrderQueue() {
        processQueue.orderQueue();
        List<Process> processes = processQueue.getProcesses();
        int firstPriority = processes.get(0).getPriority();
        int secondPriority = processes.get(1).getPriority();
        assertTrue(firstPriority <= secondPriority);
    }


    @Test
    public void testPush() {
        Process process = new Process(50, 50, 5);
        List<Process> processes = processQueue.getProcesses();
        int beforeSize = processes.size();
        processQueue.push(process);
        int afterSize = processes.size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    public void testPop(int capacity) {
        Process process = new Process(50, 1, 0);
        processQueue.push(process);
        Process poppedProcess = processQueue.pop();
        assertEquals(process, poppedProcess);
    }
}
