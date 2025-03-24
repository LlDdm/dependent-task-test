package edu.boun.edgecloudsim.applications.ll;

import java.util.ArrayList;
import java.util.List;

class DAG {
    private List<Task> tasks;

    DAG() {
        this.tasks = new ArrayList<>();
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    List<Task> getTasks() {
        return tasks;
    }
    Task getTask(int id) {
        for (Task task : tasks) {
            if (task.id == id) {
                return task;
            }
        }
        // 如果没有找到匹配的Task, 返回null或者抛出异常
        return null; // 或者 throw new NoSuchElementException("No Task with such ID found");
    }
}
