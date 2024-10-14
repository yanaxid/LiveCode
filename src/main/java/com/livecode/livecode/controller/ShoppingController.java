package com.livecode.livecode.controller;

import com.livecode.livecode.models.Item;
import com.livecode.livecode.services.ShoppingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    // Endpoint untuk input uang dan mendapatkan barang yang optimal
    @GetMapping("/purchase")
    public ResponseEntity<List<Item>> purchaseItems(@RequestParam int budget) {
        List<Item> items = shoppingService.findOptimalItems(budget);
        return ResponseEntity.ok(items);
    }
}