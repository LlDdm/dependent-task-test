package edu.boun.edgecloudsim.applications.ll;
import java.util.*;

class Task {
    int id; // 任务ID
    long size; // 任务大小
    int APPid;
    double startime;
    double completetime;
    Map<Task, Long> predecessors; // 前驱任务列表及边的大小
    Map<Task, Long> successors;   // 后继任务列表及边的大小

    Task(int id, long size, int APPid) {
        this.id = id;
        this.size = size;
        this.APPid = APPid;
        this.startime = 0;
        this.completetime = 0;
        this.predecessors = new HashMap<>();
        this.successors = new HashMap<>();
    }

    // 添加前驱任务并记录边的大小
    public void addPredecessor(Task task, long edgeSize) {
        this.predecessors.put(task, edgeSize);
    }

    // 添加后继任务并记录边的大小
    public void addSuccessor(Task task, long edgeSize) {
        this.successors.put(task, edgeSize);
    }

    public List<Task> getPredecessors() {
        return new ArrayList<>(predecessors.keySet());
    }

    public List<Task> getSuccessors() {
        return new ArrayList<>(successors.keySet());
    }

    public void set_starttime(double starttime) {this.startime = starttime;}
    public void set_completetime(double completetime) {this.completetime = completetime;}

    @Override
    public String toString() {
        return "Task{id=" + id + ", size=" + size + "}";
    }
}
