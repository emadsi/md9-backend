package com.md9.controller;

import com.md9.model.Cancellation;
import com.md9.repository.CancellationRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/cancellations")
public class CancellationController {
    private final CancellationRepository cancellationRepository;

    public CancellationController(CancellationRepository cancellationRepository) {
        this.cancellationRepository = cancellationRepository;
    }

    @GetMapping("/report")
    public List<Cancellation> generateCancellationsReport() {
        return cancellationRepository.findAll();
    }
}
