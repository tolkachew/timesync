package org.itstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountdownService {
    @Autowired
    private CountdownRepo countdownRepo;

    public List<Countdown> findAll() {
        return countdownRepo.findAll();
    }

    public Countdown save(Countdown block) {
        return
                countdownRepo.save(block);
    }

    public Countdown findById(Long id) {
        return countdownRepo.findById(id).get();
    }

    public void deleteById(Long id) {
        countdownRepo.deleteById(id);
    }
}