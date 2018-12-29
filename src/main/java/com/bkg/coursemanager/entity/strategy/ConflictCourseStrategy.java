package com.bkg.coursemanager.entity.strategy;

import com.bkg.coursemanager.entity.Team;

public class ConflictCourseStrategy implements TeamStrategy {

    int id;
    int course;
    int courseId;

    @Override
    public int handleTeamStrategy(Team team)
    {
        return 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
