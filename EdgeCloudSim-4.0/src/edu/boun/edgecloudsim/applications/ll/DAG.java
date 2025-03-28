package edu.boun.edgecloudsim.applications.ll;

import java.util.*;

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
        HashMap<Integer, Integer> outDegree = new HashMap<>();
        Queue<Task> queue = new LinkedList<>();
        for (Task task : tasks) {
            outDegree.put(task.id, task.getSuccessors() .size());
            if(task.getSuccessors().isEmpty()){
                queue.offer(task);
                task.R = 0;
            }
        }

        while(!queue.isEmpty()){
            Task task = queue.poll();
            sortedTasks.add(task);
            for(Task pre : task.getPredecessors()) {
                if (pre != null) {
                    queue.offer(pre);
                    long recent_max = 0;
                    for (Task suc : pre.getSuccessors()) {
                        long tra = pre.successors.get(suc.id);
                        if (tra + suc.R > recent_max)
                            recent_max = tra + suc.R;
                    }
                    pre.R = recent_max + pre.size;
                }
            }
        }

        sortedTasks.sort(Comparator.comparingLong(Task -> Task.R));
        Collections.reverse(sortedTasks);

        if (sortedTasks.size() != tasks.size()) {
            throw new IllegalArgumentException("The graph has at least one cycle, cannot perform topological sort.");
        }
        return sortedTasks;
    }
}
