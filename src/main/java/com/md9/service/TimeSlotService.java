package com.md9.service;

import com.md9.model.DisabledTimeslot;
import com.md9.model.Timeslot;
import com.md9.repository.DisabledTimeslotRepository;
import com.md9.repository.ReservationRepository;
import com.md9.repository.TimeSlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TimeslotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private DisabledTimeslotRepository disabledTimeslotRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // public TimeslotService(TimeSlotRepository timeSlotRepository, DisabledTimeslotRepository disabledTimeSlotRepository, ReservationRepository reservationRepository) {
    //     this.timeSlotRepository = timeSlotRepository;
    //     this.reservationRepository = reservationRepository;
    //     this.disabledTimeSlotRepository = disabledTimeSlotRepository;
    // }

    @Transactional
    public void addTimeSlot(String from, String to) {
        LocalTime startTime = LocalTime.parse(from);
        LocalTime endTime = LocalTime.parse(to);
       

        // Fetch existing time slots and add the new one in order
        List<Timeslot> timeSlots = timeSlotRepository.findAll();
        Integer timeSlotsLength = timeSlots.size();
        timeSlots.add(new Timeslot((timeSlotsLength).toString(), startTime, endTime, true));
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
        DisabledTimeslot disabledTimeslot = new DisabledTimeslot(String.format("%d", disabledTimeslotRepository.findAll().size() + 1), timeSlotId, date, "blocked");
        disabledTimeslotRepository.save(disabledTimeslot);
    }

    @Transactional
    public void blockAllTimeSlots(LocalDate date) {
        List<Timeslot> timeSlots = timeSlotRepository.findAll();
        for (Timeslot timeSlot : timeSlots) {
            DisabledTimeslot disabledTimeslot = new DisabledTimeslot(String.format("%d", disabledTimeslotRepository.findAll().size() + 1), timeSlot.getId(), date, "blocked");
            disabledTimeslotRepository.save(disabledTimeslot);
        }
    }

    // Fetch all time slots
    @Transactional
    public List<Timeslot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    // Delete a time slot by ID
    @Transactional
    public void deleteTimeSlot(String id) {
        timeSlotRepository.deleteById(id);
    }
}
