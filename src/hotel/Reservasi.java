package hotel;

/**
 * Merepresentasikan transaksi reservasi yang menghubungkan objek Tamu dan Kamar
 * beserta perhitungan biaya menginap dan status keaktifannya.
 */
public class Reservasi {
    private String kodeReservasi;
    private Tamu tamu;
    private Kamar kamar;
    private int jumlahMalam;
    private double totalBiaya;
    private boolean statusAktif;

    /**
     * Konstruktor default dengan nilai awal aman.
     */
    public Reservasi() {
        // Menginisialisasi nilai default secara konsisten
        setKodeReservasi("RSV-000");
        this.tamu = new Tamu();
        this.kamar = new Kamar();
        setJumlahMalam(1);
        this.totalBiaya = hitungTotalBiaya();
        this.statusAktif = false;
    }

    /**
     * Konstruktor berparameter untuk membuat reservasi baru dan mengunci status kamar
     * apabila kamar masih tersedia.
     *
     * @param kodeReservasi kode unik reservasi
     * @param tamu objek tamu yang melakukan pemesanan
     * @param kamar objek kamar yang dipesan
     * @param jumlahMalam lama menginap dalam malam
     */
    public Reservasi(String kodeReservasi, Tamu tamu, Kamar kamar, int jumlahMalam) {
        // Memanggil setter untuk validasi atribut dasar
        setKodeReservasi(kodeReservasi);
        setTamu(tamu);
        setJumlahMalam(jumlahMalam);

        // Aturan bisnis: kamar terisi tidak bisa dipesan
        if (kamar == null) {
            System.out.println("Gagal membuat reservasi: Data kamar tidak valid.");
            this.kamar = null;
            this.statusAktif = false;
            this.totalBiaya = 0.0;
            return;
        }

        if (kamar.isTerisi()) {
            System.out.println("Gagal membuat reservasi: Kamar " + kamar.getNomorKamar() + " sedang terisi.");
            this.kamar = kamar;
            this.statusAktif = false;
            this.totalBiaya = 0.0;
            return;
        }

        // Kamar tersedia: hubungkan relasi, tandai terisi, dan hitung biaya (tarif * malam)
        this.kamar = kamar;
        this.kamar.tandaiTerisi();
        this.statusAktif = true;
        this.totalBiaya = hitungTotalBiaya();
        if (this.tamu != null) {
            this.tamu.tambahKunjungan();
        }
    }

    public String getKodeReservasi() {
        return kodeReservasi;
    }

    public Tamu getTamu() {
        return tamu;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public int getJumlahMalam() {
        return jumlahMalam;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public boolean isStatusAktif() {
        return statusAktif;
    }

    public void setKodeReservasi(String kodeReservasi) {
        // Validasi: kode reservasi tidak boleh kosong
        if (kodeReservasi == null || kodeReservasi.trim().isEmpty()) {
            System.out.println("Peringatan: Kode reservasi tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        this.kodeReservasi = kodeReservasi.trim();
    }

    public void setTamu(Tamu tamu) {
        // Validasi: objek tamu tidak boleh null
        if (tamu == null) {
            System.out.println("Peringatan: Data tamu tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        this.tamu = tamu;
    }

    public void setKamar(Kamar kamar) {
        // Validasi: kamar tidak boleh null dan tidak boleh sedang terisi
        if (kamar == null) {
            System.out.println("Peringatan: Data kamar tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        if (kamar.isTerisi()) {
            System.out.println("Peringatan: Kamar " + kamar.getNomorKamar() + " sedang terisi. Nilai tidak diubah.");
            return;
        }
        this.kamar = kamar;
        this.totalBiaya = hitungTotalBiaya();
    }

    public void setJumlahMalam(int jumlahMalam) {
        // Validasi: jumlah malam minimal 1
        if (jumlahMalam <= 0) {
            System.out.println("Peringatan: Jumlah malam harus lebih dari 0. Nilai tidak diubah.");
            return;
        }
        this.jumlahMalam = jumlahMalam;
        this.totalBiaya = hitungTotalBiaya();
    }

    /**
     * Menghitung total biaya menginap dengan aturan biaya = tarif per malam * jumlah malam.
     *
     * @return total biaya reservasi
     */
    public double hitungTotalBiaya() {
        if (this.kamar == null || this.jumlahMalam <= 0) {
            return 0.0;
        }
        return this.kamar.hitungBiaya(this.jumlahMalam);
    }

    /**
     * Membatalkan reservasi aktif dan mengosongkan kembali kamar terkait.
     *
     * @return true jika pembatalan berhasil, false jika reservasi sudah tidak aktif
     */
    public boolean batalkan() {
        if (!this.statusAktif) {
            System.out.println("Info: Reservasi " + this.kodeReservasi + " sudah tidak aktif.");
            return false;
        }
        this.statusAktif = false;
        if (this.kamar != null) {
            this.kamar.kosongkanKamar();
        }
        return true;
    }

    /**
     * Menghasilkan ringkasan informasi transaksi reservasi.
     *
     * @return string ringkasan reservasi
     */
    public String ringkasanTransaksi() {
        String namaTamu = (tamu != null) ? tamu.getNama() : "-";
        String noKamar = (kamar != null) ? kamar.getNomorKamar() : "-";
        String statusStr = statusAktif ? "AKTIF" : "DIBATALKAN/TIDAK AKTIF";
        return "[" + kodeReservasi + "] Tamu: " + namaTamu
                + " | Kamar: " + noKamar
                + " | Malam: " + jumlahMalam
                + " | Total: Rp" + totalBiaya
                + " | Status: " + statusStr;
    }
}