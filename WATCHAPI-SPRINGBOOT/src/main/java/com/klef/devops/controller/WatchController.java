package com.klef.devops.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.devops.model.Watch;
import com.klef.devops.service.WatchService;

@RestController
@RequestMapping("/watchapi")
@CrossOrigin(origins = "*")
public class WatchController {

    @Autowired
    private WatchService watchService;

    @GetMapping("/")
    public String home() {
        return "Jenkins Full Stack Deployment Demo - Watch API";
    }

    @PostMapping("/add")
    public ResponseEntity<Watch> addWatch(@RequestBody Watch watch) {
        Watch savedWatch = watchService.addWatch(watch);
        return new ResponseEntity<>(savedWatch, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Watch>> getAllWatches() {
        List<Watch> watches = watchService.getAllWatches();
        return new ResponseEntity<>(watches, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getWatchById(@PathVariable int id) {
        Watch watch = watchService.getWatchById(id);
        if (watch != null) {
            return new ResponseEntity<>(watch, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Watch with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateWatch(@RequestBody Watch watch) {
        Watch existing = watchService.getWatchById(watch.getId());
        if (existing != null) {
            Watch updatedWatch = watchService.updateWatch(watch);
            return new ResponseEntity<>(updatedWatch, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Watch with ID " + watch.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWatch(@PathVariable int id) {
        Watch existing = watchService.getWatchById(id);
        if (existing != null) {
            watchService.deleteWatchById(id);
            return new ResponseEntity<>("Watch with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Watch with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
