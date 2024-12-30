package com.md9.service;

import com.md9.model.DisabledTimeSlot;
import com.md9.repository.DisabledTimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
}
