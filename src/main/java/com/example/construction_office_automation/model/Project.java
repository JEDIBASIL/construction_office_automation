package com.example.construction_office_automation.model;

import java.time.LocalDate;

public class Project {
    private long projectId;
    private String projectName;
    private String projectOwner;
    private String projectLocation;

    private String projectManager;
    private String projectMonitor;
    private String projectDescription;
    private String projectStatus;
    private int projectProgress;

    private LocalDate startingDate;
    private LocalDate finishingDate;

    public  Project(){}

    public Project(long projectId,String projectName,String projectOwner,LocalDate startingDate,LocalDate finishingDate,String projectLocation,String projectManager,String projectStatus,int projectProgress){
        this.projectId =projectId;
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.startingDate = startingDate;
        this.finishingDate = finishingDate;
        this.projectLocation = projectLocation;
        this.projectManager = projectManager;
        this.projectStatus = projectStatus;
        this.projectProgress = projectProgress;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectOwner='" + projectOwner + '\'' +
                ", projectLocation='" + projectLocation + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", projectMonitor='" + projectMonitor + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", projectProgress=" + projectProgress +
                ", startingDate=" + startingDate +
                ", finishingDate=" + finishingDate +
                '}';
    }

    public boolean checkFields() {
        if (
                getProjectName() != null &&
                        getProjectOwner() != null &&
                        getProjectLocation() != null &&
                        getProjectManager() != null &&
                        getProjectMonitor() != null &&
                        getProjectDescription() != null &&
                        getProjectStatus() != null &&
                        getStartingDate() != null &&
                        getFinishingDate() != null
        ) return true;
    return false;
    }

    public boolean checkFields(String checkType){
        if(
            getProjectName() !=null &&
            getProjectOwner() !=null &&
            getProjectLocation() !=null &&
            getProjectManager() !=null &&
            getStartingDate() !=null &&
            getFinishingDate() !=null
        )return true;

        return false;
    }


    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(LocalDate finishingDate) {
        this.finishingDate = finishingDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public int getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(int projectProgress) {
        this.projectProgress = projectProgress;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectMonitor() {
        return projectMonitor;
    }

    public void setProjectMonitor(String projectMonitor) {
        this.projectMonitor = projectMonitor;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }


}
