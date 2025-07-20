package com.example.Assignment.dtos;

import java.util.List;

public class DashboardResponse {
    private double completionPercentage;
    private int totalTasks;
    private int completedTasks;
    private int overdueTasks;

    private List<MemberWorkload> teamSummary;

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getOverdueTasks() {
        return overdueTasks;
    }

    public void setOverdueTasks(int overdueTasks) {
        this.overdueTasks = overdueTasks;
    }

    public List<MemberWorkload> getTeamSummary() {
        return teamSummary;
    }

    public void setTeamSummary(List<MemberWorkload> teamSummary) {
        this.teamSummary = teamSummary;
    }
}
