package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Process {
    private UUID pid;
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private int quantum;
    private int priority;

    public Process(int quantum, int processNumber, int priority) {
        this.quantum = quantum;
        this.priority = priority;
        this.name = "P" + processNumber;
        this.pid = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Process process = (Process) o;

        return pid.equals(process.pid);
    }

    @Override
    public int hashCode() {
        return pid.hashCode();
    }
}
