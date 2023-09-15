package models;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessQueue {
    private List<Process> processes;


    public ProcessQueue(int maxCapacity) {
        processes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < maxCapacity; i++) {
            int quantum = random.nextInt(0, 101);
            int priority = random.nextInt(0, 9);
            Process process = new Process(quantum, i, priority);
            processes.add(process);
        }
        orderQueue();
    }

    public void run() {
        while (processes.size() > 0) {
            Process process = pop();
            int newQuantum = process.getQuantum() - 1;
            process.setQuantum(newQuantum);
            if (newQuantum > 0) {
                process.setModificationDate(LocalDateTime.now());
                push(process);
            }
        }
    }

    public void orderQueue() {
        Comparator<Process> comparator = Comparator.comparingInt(Process::getPriority).thenComparing((Process::getModificationDate));
        processes = processes.stream().sorted(comparator).collect(Collectors.toList());
    }

    public Process pop() {
        Process process = processes.get(0);
        processes.remove(0);
        return process;
    }

    public void push(Process process) {
        processes.add(process);
        orderQueue();
    }

    public List<Process> getProcesses() {
        return Collections.unmodifiableList(processes);
    }
}
