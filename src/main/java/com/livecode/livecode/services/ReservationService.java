package com.livecode.livecode.services;

import com.livecode.livecode.models.Reservation;
import com.livecode.livecode.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Mengecek apakah hari libur (Rabu dan Jumat)
    private boolean isHoliday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.FRIDAY;
    }

    // Menambah reservasi jika hari tidak libur dan masih ada slot
    public String addReservation(String customerName, LocalDate date) {
        if (isHoliday(date)) {
            return "Tidak bisa reservasi pada hari Rabu atau Jumat";
        }

        List<Reservation> reservations = reservationRepository.findByReservationDate(date);
        if (reservations.size() >= 2) {
            return "Kuota reservasi untuk hari ini sudah penuh!";
        }

        Reservation reservation = new Reservation();
        reservation.setCustomerName(customerName);
        reservation.setReservationDate(date);
        reservationRepository.save(reservation);
        return "Reservasi berhasil ditambahkan untuk tanggal: " + date;
    }

    // Mengambil semua reservasi dalam satu minggu
    public List<Reservation> getReservationsForWeek() {
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.plusDays(6);  // 7 hari ke depan
        return reservationRepository.findAll().stream()
                .filter(reservation -> !isHoliday(reservation.getReservationDate()))
                .filter(reservation -> reservation.getReservationDate().isAfter(today.minusDays(1)) &&
                        reservation.getReservationDate().isBefore(endOfWeek.plusDays(1)))
                .toList();
    }
}
