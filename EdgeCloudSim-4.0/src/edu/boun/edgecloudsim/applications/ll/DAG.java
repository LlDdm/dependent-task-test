package edu.boun.edgecloudsim.applications.ll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

    List<Task> topologicalSort() {
        List<Task> sortedTasks = new ArrayList<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        Queue<Task> queue = new LinkedList<>();
        for (Task task : tasks) {
            inDegree.put(task.id, task.getPredecessors().size());
            if(task.getPredecessors().isEmpty()){
                queue.add(task);
            }
        }

        while(!queue.isEmpty()){
            Task task = queue.poll();
            sortedTasks.add(task);
        }

        if (sortedTasks.size() != tasks.size()) {
            throw new IllegalArgumentException("The graph has at least one cycle, cannot perform topological sort.");
        }
        return new ArrayList<>(sortedTasks);
    }
}
