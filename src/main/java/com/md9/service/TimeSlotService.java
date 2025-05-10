package com.md9.service;

import com.md9.model.DisabledTimeslot;
import com.md9.model.Timeslot;
import com.md9.repository.DisabledTimeslotRepository;
// import com.md9.repository.ReservationRepository;
import com.md9.repository.TimeslotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeslotService {
    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    private DisabledTimeslotRepository disabledTimeslotRepository;

    // @Autowired
    // private ReservationRepository reservationRepository;

    @Transactional
    public void addTimeslot(String startTime, String endTime, String fieldId) {

        // Fetch existing time slots and add the new one in order
        List<Timeslot> timeslots = timeslotRepository.findAll();
        Integer timeslotsLength = timeslots.size();
        timeslots.add(new Timeslot((timeslotsLength).toString(), startTime, endTime, fieldId));
        timeslots.sort((ts1, ts2) -> ts1.getStartTime().compareTo(ts2.getStartTime()));

        // Save updated time slots and update reservations
        for (int i = 0; i < timeslots.size(); i++) {
            timeslots.get(i).setId(String.format("%d", i + 1));
            timeslotRepository.save(timeslots.get(i));
        }

        // Update reservations collection
        for (int i = 0; i < timeslots.size(); i++) {
            timeslotRepository.updateTimeslotIds(timeslots.get(i).getId(), String.format("%d", i + 1));
        }
    }

    @Transactional
    public void blockTimeslot(String timeslotId, String date, String fieldId) {
        DisabledTimeslot disabledTimeslot = new DisabledTimeslot(
                String.format("%d", disabledTimeslotRepository.findAll().size() + 1), timeslotId, date, "blocked",
                fieldId);
        disabledTimeslotRepository.save(disabledTimeslot);
    }

    @Transactional
    public void blockAllTimeslots(String date, String fieldId) {
        List<Timeslot> timeslots = timeslotRepository.findAll();
        for (Timeslot timeSlot : timeslots) {
            DisabledTimeslot disabledTimeslot = new DisabledTimeslot(
                    String.format("%d", disabledTimeslotRepository.findAll().size() + 1), timeSlot.getId(), date,
                    "blocked", fieldId);
            disabledTimeslotRepository.save(disabledTimeslot);
        }
    }

    // Fetch all time slots
    @Transactional
    public List<Timeslot> getAllTimeslots() {
        return timeslotRepository.findAll();
    }

    // Delete a time slot by ID
    @Transactional
    public void deleteTimeslot(String id) {
        timeslotRepository.deleteById(id);
    }
}