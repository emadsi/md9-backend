package com.md9.service;

import com.md9.model.TimeSlot;
import com.md9.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public TimeSlot addTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot updateTimeSlot(Long id, TimeSlot updatedTimeSlot) {
        return timeSlotRepository.findById(id).map(existing -> {
            existing.setStartTime(updatedTimeSlot.getStartTime());
            existing.setEndTime(updatedTimeSlot.getEndTime());
            return timeSlotRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("TimeSlot not found"));
    }

    public void deleteTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }
}
