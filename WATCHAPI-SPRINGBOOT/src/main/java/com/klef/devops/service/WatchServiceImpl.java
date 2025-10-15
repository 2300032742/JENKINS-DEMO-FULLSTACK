package com.klef.devops.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.devops.model.Watch;
import com.klef.devops.repository.WatchRepository;

@Service
public class WatchServiceImpl implements WatchService {

    @Autowired
    private WatchRepository watchRepository;

    @Override
    public Watch addWatch(Watch watch) {
        return watchRepository.save(watch);
    }

    @Override
    public List<Watch> getAllWatches() {
        return watchRepository.findAll();
    }

    @Override
    public Watch getWatchById(int id) {
        Optional<Watch> opt = watchRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Watch updateWatch(Watch watch) {
        return watchRepository.save(watch);
    }

    @Override
    public void deleteWatchById(int id) {
        watchRepository.deleteById(id);
    }
}
