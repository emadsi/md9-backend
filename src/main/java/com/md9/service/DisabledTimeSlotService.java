package com.md9.service;

import com.md9.model.DisabledTimeSlot;
import com.md9.repository.DisabledTimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DisabledTimeSlotService {
    @Autowired
    private DisabledTimeSlotRepository disabledTimeSlotRepository;

    public List<DisabledTimeSlot> getDisabledTimeSlotsByDate(LocalDate date) {
        return disabledTimeSlotRepository.findByDate(date);
    }

    public DisabledTimeSlot addDisabledTimeSlot(DisabledTimeSlot disabledTimeSlot) {
        return disabledTimeSlotRepository.save(disabledTimeSlot);
    }

    public List<DisabledTimeSlot> getAllDisabledTimeSlots() {
        return disabledTimeSlotRepository.findAll();
    }

    public Optional<DisabledTimeSlot> getDisabledTimeSlotById(String id) {
        return disabledTimeSlotRepository.findById(id);
    }

    public DisabledTimeSlot updateDisabledTimeSlot(String id, DisabledTimeSlot updatedTimeSlot) {
        Optional<DisabledTimeSlot> optionalSlot = disabledTimeSlotRepository.findById(id);
    
        if (optionalSlot.isPresent()) {
            DisabledTimeSlot slot = optionalSlot.get();
            slot.setTimeSlotId(updatedTimeSlot.getTimeSlotId());
            slot.setReason(updatedTimeSlot.getReason());
            return disabledTimeSlotRepository.save(slot);
        } else {
            throw new RuntimeException("TimeSlot not found with id: " + id);
        }
    }

    public void deleteDisabledTimeSlot(String id) {
        disabledTimeSlotRepository.deleteById(id);
    }

    public List<DisabledTimeSlot> getTimeSlotsByReason(String reason) {
        return disabledTimeSlotRepository.findByReason(reason);
    }
}
