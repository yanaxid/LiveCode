package com.livecode.livecode.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.livecode.livecode.models.Chicken;
import com.livecode.livecode.services.EggInputService;


@RestController
@RequestMapping("/chickens")
public class ChickenController {

    @Autowired
    private EggInputService eggInputService;


    // Tambahkan ayam baru
    @PostMapping
    public Chicken addChicken(@RequestBody Chicken chicken) {
        return eggInputService.addNewChicken(chicken);
    }

    // Input jumlah telur yang dihasilkan oleh ayam
    @PostMapping("/{id}/eggs")
    public ResponseEntity<Chicken> inputEggsProduced(@PathVariable Long id, @RequestParam int eggs) {
        Chicken chicken = eggInputService.inputEggsProduced(id, eggs);
        if (chicken != null) {
            return ResponseEntity.ok(chicken);
        }
        return ResponseEntity.notFound().build();
    }

    // Melihat total telur yang dihasilkan oleh semua ayam
    @GetMapping("/recap")
    public int getTotalEggsProduced() {
        return eggInputService.getTotalEggsProduced();
    }
}