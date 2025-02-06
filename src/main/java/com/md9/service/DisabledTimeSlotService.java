package com.md9.service;

import com.md9.model.DisabledTimeslot;
import com.md9.repository.DisabledTimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DisabledTimeslotService {
    @Autowired
    private DisabledTimeslotRepository disabledTimeSlotRepository;

    public List<DisabledTimeslot> getDisabledTimeslotsByDate(LocalDate date) {
        return disabledTimeSlotRepository.findByDate(date);
    }

    public DisabledTimeslot addDisabledTimeslot(DisabledTimeslot disabledTimeslot) {
        return disabledTimeSlotRepository.save(disabledTimeslot);
    }

    public List<DisabledTimeslot> getAllDisabledTimeslots() {
        return disabledTimeSlotRepository.findAll();
    }

    public Optional<DisabledTimeslot> getDisabledTimeslotById(String id) {
        return disabledTimeSlotRepository.findById(id);
    }

    public DisabledTimeslot updateDisabledTimeslot(String id, DisabledTimeslot updatedTimeSlot) {
        Optional<DisabledTimeslot> optionalSlot = disabledTimeSlotRepository.findById(id);
    
        if (optionalSlot.isPresent()) {
            DisabledTimeslot slot = optionalSlot.get();
            slot.setTimeSlotId(updatedTimeSlot.getTimeSlotId());
            slot.setReason(updatedTimeSlot.getReason());
            return disabledTimeSlotRepository.save(slot);
        } else {
            throw new RuntimeException("Timeslot not found with id: " + id);
        }
    }

    public void deleteDisabledTimeslot(String id) {
        disabledTimeSlotRepository.deleteById(id);
    }

    public List<DisabledTimeslot> getTimeSlotsByReason(String reason) {
        return disabledTimeSlotRepository.findByReason(reason);
    }
}
