package com.bkg.coursemanager.entity.strategy;

import com.bkg.coursemanager.entity.Team;

public class TeamOrStrategy implements TeamStrategy {

    @Override
    public int handleTeamStrategy(Team team)
    {
        return 1;
    }
}
