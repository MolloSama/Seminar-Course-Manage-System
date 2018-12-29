package com.bkg.coursemanager.entity.strategy;

import com.bkg.coursemanager.entity.Team;

public class TeamAndStrategy implements TeamStrategy {

    @Override
    public int handleTeamStrategy(Team team)
    {
        return 1;
    }
}
