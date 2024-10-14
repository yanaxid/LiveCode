package com.livecode.livecode.services;

import com.livecode.livecode.models.Chicken;
import com.livecode.livecode.repository.ChickenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EggInputService {

    @Autowired
    private ChickenRepository chickenRepository;

    public Chicken inputEggsProduced(Long chickenId, int eggs) {
        Chicken chicken = chickenRepository.findById(chickenId).orElse(null);
        if (chicken != null) {
            chicken.setEggsProduced(chicken.getEggsProduced() + eggs);
            return chickenRepository.save(chicken);
        }
        return null;
    }

    public Chicken addNewChicken(Chicken chicken) {
        return chickenRepository.save(chicken);
    }




    public int getTotalEggsProduced() {
      return chickenRepository.findAll().stream()
              .mapToInt(Chicken::getEggsProduced)
              .sum();
  }
}