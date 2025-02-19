package com.md9.service;

import com.md9.model.DisabledTimeslot;
import com.md9.repository.DisabledTimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisabledTimeslotService {
    @Autowired
    private DisabledTimeslotRepository disabledTimeslotRepository;

    public List<DisabledTimeslot> getDisabledTimeslotsByDate(String date) {
        return disabledTimeslotRepository.findByDate(date);
    }

    public DisabledTimeslot addDisabledTimeslot(DisabledTimeslot disabledTimeslot) {
        return disabledTimeslotRepository.save(disabledTimeslot);
    }

    public List<DisabledTimeslot> getAllDisabledTimeslots() {
        return disabledTimeslotRepository.findAll();
    }

    public Optional<DisabledTimeslot> getDisabledTimeslotById(String id) {
        return disabledTimeslotRepository.findById(id);
    }

    public DisabledTimeslot updateDisabledTimeslot(String id, DisabledTimeslot updatedTimeslot) {
        Optional<DisabledTimeslot> optionalSlot = disabledTimeslotRepository.findById(id);
    
        if (optionalSlot.isPresent()) {
            DisabledTimeslot slot = optionalSlot.get();
            slot.setTimeslotId(updatedTimeslot.getTimeslotId());
            slot.setReason(updatedTimeslot.getReason());
            return disabledTimeslotRepository.save(slot);
        } else {
            throw new RuntimeException("Timeslot not found with id: " + id);
        }
    }

    public void deleteDisabledTimeslot(String id) {
        disabledTimeslotRepository.deleteById(id);
    }

    public List<DisabledTimeslot> getTimeslotsByReason(String reason) {
        return disabledTimeslotRepository.findByReason(reason);
    }
}
