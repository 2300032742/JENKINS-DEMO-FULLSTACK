package com.klef.devops.service;

import java.util.List;
import com.klef.devops.model.Watch;

public interface WatchService
{
    Watch addWatch(Watch watch);
    List<Watch> getAllWatches();
    Watch getWatchById(int id);
    Watch updateWatch(Watch watch);
    void deleteWatchById(int id);
}
