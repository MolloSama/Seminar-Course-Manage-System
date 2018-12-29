package com.bkg.coursemanager.entity.strategy;

import com.bkg.coursemanager.entity.Team;

public class MemberLimitStrategy implements TeamStrategy {

    int id;
    int courseId;
    int min_member;
    int max_member;

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

    public int getMin_member() {
        return min_member;
    }

    public void setMin_member(int min_member) {
        this.min_member = min_member;
    }

    public int getMax_member() {
        return max_member;
    }

    public void setMax_member(int max_member) {
        this.max_member = max_member;
    }
}
