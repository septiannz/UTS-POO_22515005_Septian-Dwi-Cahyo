package hotel;

public class UjiHotel {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel Kendari Asri", 5);
        hotel.setNamaHotel(""); // harus ditolak validasi

        Kamar k1 = new Kamar("KM-201", "Superior", 300000.0, false);
        Tamu t1 = new Tamu("TM-101", "Rina Maharani", "081122334455", 0);
        Tamu t2 = new Tamu("TM-102", "Dedi Kurniawan", "082233445566", 0);

        hotel.tambahKamar(k1);
        hotel.tambahTamu(t1);
        hotel.tambahTamu(t2);

        // Reservasi sukses: 2 malam * 300000 = 600000
        Reservasi r1 = hotel.buatReservasi("RSV-101", "TM-101", "KM-201", 2);
        if (r1 != null) {
            System.out.println("Berhasil: " + r1.ringkasanTransaksi());
        }

        // Gagal karena kamar sudah terisi
        hotel.buatReservasi("RSV-102", "TM-102", "KM-201", 1);

        // Uji pencarian
        System.out.println("--- Hasil Cari 'Rina' ---");
        System.out.println(hotel.cariData("Rina"));

        // Total pendapatan aktif
        System.out.println("Total pendapatan aktif: Rp" + hotel.hitungTotalPendapatanAktif());
    }
}