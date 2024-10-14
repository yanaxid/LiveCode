package com.livecode.livecode.services;

import com.livecode.livecode.models.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingService {

    // Daftar barang dan harganya
    private List<Item> availableItems;

    public ShoppingService() {
        availableItems = new ArrayList<>();
        // Tambahkan harga-harga untuk keyboard
        availableItems.add(new Item("Keyboard", 25000));
        availableItems.add(new Item("Keyboard", 40000));
        availableItems.add(new Item("Keyboard", 55000));

        // Tambahkan harga-harga untuk mouse
        availableItems.add(new Item("Mouse", 12000));
        availableItems.add(new Item("Mouse", 20000));
        availableItems.add(new Item("Mouse", 35000));
    }

    // Metode untuk mencari kombinasi barang yang mendekati atau menghabiskan uang
    public List<Item> findOptimalItems(int budget) {
        List<Item> result = new ArrayList<>();
        List<Item> tempResult = new ArrayList<>();
        findItemsRecursive(budget, 0, tempResult, result);
        return result;
    }

    // Metode rekursif untuk mencari kombinasi terbaik
    private void findItemsRecursive(int budget, int start, List<Item> tempResult, List<Item> result) {
        int currentSum = tempResult.stream().mapToInt(Item::getPrice).sum();

        // Jika sudah melebihi anggaran, berhenti
        if (currentSum > budget) {
            return;
        }

        // Jika total barang saat ini lebih mendekati anggaran, simpan hasilnya
        if (currentSum > result.stream().mapToInt(Item::getPrice).sum()) {
            result.clear();
            result.addAll(tempResult);
        }

        // Loop melalui semua barang yang tersedia
        for (int i = start; i < availableItems.size(); i++) {
            tempResult.add(availableItems.get(i));
            findItemsRecursive(budget, i + 1, tempResult, result);  // Rekursif dengan barang selanjutnya
            tempResult.remove(tempResult.size() - 1);  // Backtrack
        }
    }
}