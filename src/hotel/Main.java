package hotel;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kelas antarmuka konsol utama (Main) yang menyediakan 7 menu interaktif
 * untuk mengelola sistem hotel dan struktur data StackReservasi secara aman.
 */
public class Main {
    private Hotel hotel;
    private Scanner scanner;

    /**
     * Konstruktor default yang menginisialisasi objek Hotel dan Scanner.
     */
    public Main() {
        // Menggunakan setter atau inisialisasi aman untuk atribut private
        this.hotel = new Hotel("Hotel Nusantara", 10);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Titik masuk utama program Java.
     *
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {
        Main aplikasi = new Main();
        aplikasi.jalankanMenu();
    }

    /**
     * Menjalankan perulangan menu utama konsol sampai pengguna memilih keluar.
     */
    public void jalankanMenu() {
        boolean berjalan = true;

        while (berjalan) {
            tampilkanHeaderMenu();
            int pilihan = bacaIntValid("Pilih menu (1-7): ");

            switch (pilihan) {
                case 1:
                    menuTambahDanTampilKamar();
                    break;
                case 2:
                    menuTambahDanTampilTamu();
                    break;
                case 3:
                    menuBuatReservasi();
                    break;
                case 4:
                    menuCariData();
                    break;
                case 5:
                    menuOperasiStack();
                    break;
                case 6:
                    menuRingkasanLaporan();
                    break;
                case 7:
                    System.out.println("Terima kasih telah menggunakan sistem " + hotel.getNamaHotel() + ". Sampai jumpa!");
                    berjalan = false;
                    break;
                default:
                    System.out.println("Peringatan: Pilihan menu harus antara 1 sampai 7.");
                    break;
            }
        }

        scanner.close();
    }

    private void tampilkanHeaderMenu() {
        System.out.println("\n==========================================");
        System.out.println("   SISTEM MANAJEMEN HOTEL & STACK (LIFO)");
        System.out.println("==========================================");
        System.out.println("1. Tambah dan Tampilkan Kamar");
        System.out.println("2. Tambah dan Tampilkan Tamu");
        System.out.println("3. Buat Reservasi (Tamu & Kamar)");
        System.out.println("4. Cari Data (Kode / ID / Nomor / Nama)");
        System.out.println("5. Operasi Stack & Tampilan Isi");
        System.out.println("6. Ringkasan / Laporan Hotel");
        System.out.println("7. Keluar");
        System.out.println("==========================================");
    }

    private void menuTambahDanTampilKamar() {
        System.out.println("\n--- [1] Tambah dan Tampilkan Kamar ---");
        String nomor = bacaStringTidakKosong("Nomor kamar (misal KM-101): ");
        String tipe = bacaStringTidakKosong("Tipe kamar (Standard/Deluxe/Suite): ");
        double tarif = bacaDoublePositif("Tarif per malam (Rp): ");

        Kamar kamarBaru = new Kamar(nomor, tipe, tarif, false);
        if (hotel.tambahKamar(kamarBaru)) {
            System.out.println("Kamar berhasil ditambahkan.");
        }

        tampilkanDaftarKamar();
    }

    private void tampilkanDaftarKamar() {
        ArrayList<Kamar> kamars = hotel.getDaftarKamar();
        System.out.println("Daftar Kamar (" + kamars.size() + "):");
        if (kamars.isEmpty()) {
            System.out.println("  (Belum ada data kamar)");
            return;
        }
        for (int i = 0; i < kamars.size(); i++) {
            Kamar k = kamars.get(i);
            String status = k.isTerisi() ? "TERISI" : "KOSONG";
            System.out.println("  " + (i + 1) + ". [" + k.getNomorKamar() + "] " + k.getTipe()
                    + " | Tarif: Rp" + k.getTarifPerMalam() + " | Status: " + status);
        }
    }

    private void menuTambahDanTampilTamu() {
        System.out.println("\n--- [2] Tambah dan Tampilkan Tamu ---");
        String idTamu = bacaStringTidakKosong("ID Tamu (misal TM-001): ");
        String nama = bacaStringTidakKosong("Nama lengkap tamu: ");
        String noTelp = bacaStringTidakKosong("Nomor telepon (min. 6 digit): ");
        int kunjungan = bacaIntNonNegatif("Jumlah kunjungan awal (0 jika baru): ");

        Tamu tamuBaru = new Tamu(idTamu, nama, noTelp, kunjungan);
        if (hotel.tambahTamu(tamuBaru)) {
            System.out.println("Data tamu berhasil ditambahkan.");
        }

        tampilkanDaftarTamu();
    }

    private void tampilkanDaftarTamu() {
        ArrayList<Tamu> tamus = hotel.getDaftarTamu();
        System.out.println("Daftar Tamu (" + tamus.size() + "):");
        if (tamus.isEmpty()) {
            System.out.println("  (Belum ada data tamu)");
            return;
        }
        for (int i = 0; i < tamus.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tamus.get(i).ringkasanProfil());
        }
    }

    private void menuBuatReservasi() {
        System.out.println("\n--- [3] Buat Reservasi ---");
        String kode = bacaStringTidakKosong("Kode reservasi (misal RSV-001): ");
        String idTamu = bacaStringTidakKosong("ID Tamu: ");
        String nomorKamar = bacaStringTidakKosong("Nomor Kamar: ");
        int malam = bacaIntPositif("Jumlah malam menginap: ");

        Reservasi hasil = hotel.buatReservasi(kode, idTamu, nomorKamar, malam);
        if (hasil != null) {
            System.out.println("Reservasi berhasil dibuat!");
            System.out.println("Detail: " + hasil.ringkasanTransaksi());
        } else {
            System.out.println("Reservasi tidak dapat diproses. Periksa kembali data atau status kamar.");
        }
    }

    private void menuCariData() {
        System.out.println("\n--- [4] Cari Data ---");
        String kunci = bacaStringTidakKosong("Masukkan kata kunci (kode/ID/nomor kamar/nama): ");
        System.out.println(hotel.cariData(kunci));
    }

    private void menuOperasiStack() {
        StackReservasi stack = hotel.getRiwayatBatal();
        System.out.println("\n--- [5] Operasi Stack Riwayat Pembatalan ---");
        System.out.println("1. Batalkan Reservasi Terakhir (Push ke Stack)");
        System.out.println("2. Pop (Ambil & Hapus Teratas dari Stack)");
        System.out.println("3. Peek (Lihat Teratas Stack)");
        System.out.println("4. Cek Status (isEmpty / isFull / size)");
        System.out.println("5. Tampilkan Seluruh Isi Stack");
        System.out.println("0. Kembali ke Menu Utama");

        int sub = bacaIntValid("Pilih operasi Stack (0-5): ");
        switch (sub) {
            case 1:
                Reservasi dibatalkan = hotel.batalkanReservasiTerakhir();
                if (dibatalkan != null) {
                    System.out.println("Reservasi dibatalkan & masuk Stack: " + dibatalkan.getKodeReservasi());
                }
                break;
            case 2:
                Reservasi popped = stack.pop();
                if (popped != null) {
                    System.out.println("Data di-pop dari Stack: " + popped.ringkasanTransaksi());
                }
                break;
            case 3:
                Reservasi top = stack.peek();
                if (top != null) {
                    System.out.println("Data teratas (TOP): " + top.ringkasanTransaksi());
                }
                break;
            case 4:
                System.out.println("isEmpty: " + stack.isEmpty()
                        + " | isFull: " + stack.isFull()
                        + " | size: " + stack.size() + "/" + stack.getKapasitas());
                break;
            case 5:
                break;
            case 0:
                return;
            default:
                System.out.println("Peringatan: Pilihan operasi Stack tidak valid.");
                break;
        }

        // Selalu tampilkan isi Stack setelah operasi agar status LIFO mudah dipantau
        System.out.println("\nStatus Stack Saat Ini (size: " + stack.size() + "/" + stack.getKapasitas() + "):");
        System.out.println(stack.tampilkanIsi());
    }

    private void menuRingkasanLaporan() {
        System.out.println("\n--- [6] Ringkasan / Laporan Hotel ---");
        int jumlahKamar = hotel.getDaftarKamar().size();
        int kamarTerisi = 0;
        for (Kamar k : hotel.getDaftarKamar()) {
            if (k.isTerisi()) {
                kamarTerisi++;
            }
        }

        int jumlahTamu = hotel.getDaftarTamu().size();
        int totalReservasi = hotel.getDaftarReservasi().size();
        int reservasiAktif = 0;
        for (Reservasi r : hotel.getDaftarReservasi()) {
            if (r.isStatusAktif()) {
                reservasiAktif++;
            }
        }

        System.out.println("Nama Hotel       : " + hotel.getNamaHotel());
        System.out.println("Total Kamar      : " + jumlahKamar + " (Terisi: " + kamarTerisi + ", Kosong: " + (jumlahKamar - kamarTerisi) + ")");
        System.out.println("Total Tamu       : " + jumlahTamu);
        System.out.println("Total Reservasi  : " + totalReservasi + " (Aktif: " + reservasiAktif + ")");
        System.out.println("Isi Stack Batal  : " + hotel.getRiwayatBatal().size() + "/" + hotel.getRiwayatBatal().getKapasitas());
        System.out.println("Total Biaya Aktif: Rp" + hotel.hitungTotalPendapatanAktif());
    }

    /**
     * Membaca input string yang tidak boleh kosong atau hanya spasi.
     *
     * @param pesan prompt untuk pengguna
     * @return string yang sudah di-trim dan tidak kosong
     */
    public String bacaStringTidakKosong(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("Peringatan: Input tidak boleh kosong. Silakan coba lagi.");
        }
    }

    /**
     * Membaca input bilangan bulat (int) secara aman tanpa crash jika diberi huruf/simbol.
     *
     * @param pesan prompt untuk pengguna
     * @return nilai integer valid
     */
    public int bacaIntValid(String pesan) {
        while (true) {
            System.out.print(pesan);
            String baris = scanner.nextLine().trim();
            try {
                return Integer.parseInt(baris);
            } catch (NumberFormatException e) {
                System.out.println("Peringatan: Masukkan harus berupa angka bulat valid (bukan huruf atau kosong).");
            }
        }
    }

    private int bacaIntPositif(String pesan) {
        while (true) {
            int nilai = bacaIntValid(pesan);
            if (nilai > 0) {
                return nilai;
            }
            System.out.println("Peringatan: Nilai harus lebih besar dari 0.");
        }
    }

    private int bacaIntNonNegatif(String pesan) {
        while (true) {
            int nilai = bacaIntValid(pesan);
            if (nilai >= 0) {
                return nilai;
            }
            System.out.println("Peringatan: Nilai tidak boleh negatif.");
        }
    }

    private double bacaDoublePositif(String pesan) {
        while (true) {
            System.out.print(pesan);
            String baris = scanner.nextLine().trim().replace(",", ".");
            try {
                double nilai = Double.parseDouble(baris);
                if (nilai > 0) {
                    return nilai;
                }
                System.out.println("Peringatan: Angka harus lebih besar dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Peringatan: Masukkan harus berupa angka valid (bukan huruf atau kosong).");
            }
        }
    }
}
