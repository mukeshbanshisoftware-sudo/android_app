package com.example.bossly.data.model.response;

public class PlanModel {
    private int planId;
    private String code;
    private String name;
    private String description;
    private int maxBusinesses;
    private int maxTeamMembers;
    private double monthlyPriceUsd;

    // Getters and Setters
    public int getPlanId() { return planId; }
    public void setPlanId(int planId) { this.planId = planId; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getMaxBusinesses() { return maxBusinesses; }
    public void setMaxBusinesses(int maxBusinesses) { this.maxBusinesses = maxBusinesses; }
    public int getMaxTeamMembers() { return maxTeamMembers; }
    public void setMaxTeamMembers(int maxTeamMembers) { this.maxTeamMembers = maxTeamMembers; }
    public double getMonthlyPriceUsd() { return monthlyPriceUsd; }
    public void setMonthlyPriceUsd(double monthlyPriceUsd) { this.monthlyPriceUsd = monthlyPriceUsd; }
}
