package com.md9.service;

import com.md9.model.DisabledTimeSlot;
import com.md9.model.TimeSlot;
import com.md9.repository.DisabledTimeSlotRepository;
import com.md9.repository.ReservationRepository;
import com.md9.repository.TimeSlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private DisabledTimeSlotRepository disabledTimeSlotRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // public TimeSlotService(TimeSlotRepository timeSlotRepository, DisabledTimeSlotRepository disabledTimeSlotRepository, ReservationRepository reservationRepository) {
    //     this.timeSlotRepository = timeSlotRepository;
    //     this.reservationRepository = reservationRepository;
    //     this.disabledTimeSlotRepository = disabledTimeSlotRepository;
    // }

    @Transactional
    public void addTimeSlot(String from, String to) {
        LocalTime startTime = LocalTime.parse(from);
        LocalTime endTime = LocalTime.parse(to);
       

        // Fetch existing time slots and add the new one in order
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        Integer timeSlotsLength = timeSlots.size();
        timeSlots.add(new TimeSlot((timeSlotsLength).toString(), startTime, endTime, true));
        timeSlots.sort((ts1, ts2) -> ts1.getStartTime().compareTo(ts2.getStartTime()));

        // Save updated time slots and update reservations
        for (int i = 0; i < timeSlots.size(); i++) {
            timeSlots.get(i).setId(String.format("%d", i + 1));
            timeSlotRepository.save(timeSlots.get(i));
        }

        // Update reservations collection
        for (int i = 0; i < timeSlots.size(); i++) {
            reservationRepository.updateTimeSlotIds(timeSlots.get(i).getId(), String.format("%d", i + 1));
        }

        // for (int i = 0; i < timeSlots.size(); i++) {
        //     timeSlots.get(i).setId(String.valueOf(i + 1));
        //     timeSlotRepository.save(timeSlots.get(i));
        // }
    }

    @Transactional
    public void blockTimeSlot(String timeSlotId, LocalDate date) {
        DisabledTimeSlot disabledTimeSlot = new DisabledTimeSlot(String.format("%d", disabledTimeSlotRepository.findAll().size() + 1), timeSlotId, date, "blocked");
        disabledTimeSlotRepository.save(disabledTimeSlot);
    }

    @Transactional
    public void blockAllTimeSlots(LocalDate date) {
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        for (TimeSlot timeSlot : timeSlots) {
            DisabledTimeSlot disabledTimeSlot = new DisabledTimeSlot(String.format("%d", disabledTimeSlotRepository.findAll().size() + 1), timeSlot.getId(), date, "blocked");
            disabledTimeSlotRepository.save(disabledTimeSlot);
        }
    }

    // Fetch all time slots
    @Transactional
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    // Delete a time slot by ID
    @Transactional
    public void deleteTimeSlot(String id) {
        timeSlotRepository.deleteById(id);
    }

    // // Fetch time slots by availability
    // public List<TimeSlot> getAvailableTimeSlots(boolean available) {
    //     return timeSlotRepository.findByAvailable(available);
    // }

    //  // Fetch time slots within a date range
    // public List<TimeSlot> getTimeSlotsBetween(LocalDateTime start, LocalDateTime end) {
    //     return timeSlotRepository.findByStartTimeBetween(start, end);
    // }

    // // Create or update a time slot
    // public TimeSlot saveTimeSlot(TimeSlot timeSlot) {
    //     return timeSlotRepository.save(timeSlot);
    // }

    // public TimeSlot updateTimeSlot(String id, TimeSlot updatedTimeSlot) {
    //     return timeSlotRepository.findById(id).map(existing -> {
    //         existing.setStartTime(updatedTimeSlot.getStartTime());
    //         existing.setEndTime(updatedTimeSlot.getEndTime());
    //         return timeSlotRepository.save(existing);
    //     }).orElseThrow(() -> new RuntimeException("TimeSlot not found"));
    // }

    // // Fetch a single time slot by ID
    // public Optional<TimeSlot> getTimeSlotById(String id) {
    //     return timeSlotRepository.findById(id);
    // }
}
