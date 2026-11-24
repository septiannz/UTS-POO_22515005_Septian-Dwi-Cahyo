package hotel;

import java.util.ArrayList;

/**
 * Struktur data Stack (LIFO) buatan sendiri untuk menyimpan riwayat pembatalan
 * reservasi dengan kapasitas tetap menggunakan ArrayList secara internal.
 */
public class StackReservasi {
    private ArrayList<Reservasi> data;
    private int kapasitas;

    /**
     * Konstruktor default dengan kapasitas standar 10.
     */
    public StackReservasi() {
        // Memanggil setter agar kapasitas awal tervalidasi
        setKapasitas(10);
        this.data = new ArrayList<>();
    }

    /**
     * Konstruktor berparameter untuk menentukan kapasitas maksimal Stack.
     *
     * @param kapasitas kapasitas maksimal elemen dalam Stack (minimal 1)
     */
    public StackReservasi(int kapasitas) {
        // Validasi dan set kapasitas melalui setter
        setKapasitas(kapasitas);
        this.data = new ArrayList<>();
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        // Validasi: kapasitas minimal 1; jika tidak valid gunakan default 10
        if (kapasitas <= 0) {
            System.out.println("Peringatan: Kapasitas Stack harus lebih dari 0. Diatur ke default 10.");
            this.kapasitas = 10;
            return;
        }
        this.kapasitas = kapasitas;
    }

    /**
     * Memasukkan objek Reservasi ke puncak Stack jika belum penuh.
     *
     * @param item objek Reservasi yang ingin ditambahkan
     * @return true jika berhasil push, false jika penuh atau item null
     */
    public boolean push(Reservasi item) {
        if (item == null) {
            System.out.println("Gagal push: Data reservasi tidak boleh kosong (null).");
            return false;
        }
        if (isFull()) {
            System.out.println("Gagal push: Stack sudah penuh (kapasitas maksimal " + kapasitas + ").");
            return false;
        }
        data.add(item);
        return true;
    }

    /**
     * Mengambil dan menghapus elemen teratas dari Stack (LIFO).
     *
     * @return objek Reservasi teratas, atau null jika Stack kosong
     */
    public Reservasi pop() {
        if (isEmpty()) {
            System.out.println("Gagal pop: Stack masih kosong, tidak ada data untuk diambil.");
            return null;
        }
        return data.remove(data.size() - 1);
    }

    /**
     * Melihat elemen teratas Stack tanpa menghapusnya.
     *
     * @return objek Reservasi teratas, atau null jika Stack kosong
     */
    public Reservasi peek() {
        if (isEmpty()) {
            System.out.println("Info peek: Stack sedang kosong.");
            return null;
        }
        return data.get(data.size() - 1);
    }

    /**
     * Mengecek apakah Stack kosong.
     *
     * @return true jika kosong, false jika ada isinya
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Mengecek apakah Stack sudah mencapai kapasitas maksimal.
     *
     * @return true jika penuh, false jika masih ada ruang
     */
    public boolean isFull() {
        return data.size() >= kapasitas;
    }

    /**
     * Mengembalikan jumlah elemen saat ini di dalam Stack.
     *
     * @return ukuran Stack saat ini
     */
    public int size() {
        return data.size();
    }

    /**
     * Mencari reservasi di dalam Stack berdasarkan kode reservasi.
     *
     * @param kode kode reservasi yang dicari
     * @return objek Reservasi jika ditemukan, atau null jika tidak ada
     */
    public Reservasi cariBerdasarkanKode(String kode) {
        if (kode == null || kode.trim().isEmpty()) {
            System.out.println("Info cari: Kode reservasi tidak boleh kosong.");
            return null;
        }
        String target = kode.trim();
        for (int i = data.size() - 1; i >= 0; i--) {
            Reservasi r = data.get(i);
            if (r.getKodeReservasi().equalsIgnoreCase(target)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Menampilkan seluruh isi Stack dari elemen teratas (TOP) ke terbawah.
     *
     * @return representasi teks isi Stack
     */
    public String tampilkanIsi() {
        if (isEmpty()) {
            return "[Stack Kosong]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== Isi Stack Reservasi (Top -> Bottom) ===\n");
        for (int i = data.size() - 1; i >= 0; i--) {
            String posisi = (i == data.size() - 1) ? " [TOP]" : "";
            sb.append((data.size() - i)).append(". ")
                    .append(data.get(i).ringkasanTransaksi())
                    .append(posisi).append("\n");
        }
        return sb.toString().trim();
    }
}