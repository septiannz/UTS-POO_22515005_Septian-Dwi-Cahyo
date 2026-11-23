package hotel;

/**
 * Merepresentasikan unit kamar pada sistem manajemen hotel beserta tarif dan status ketersediaannya.
 */
public class Kamar {
    private String nomorKamar;
    private String tipe;
    private double tarifPerMalam;
    private boolean terisi;

    /**
     * Konstruktor default dengan nilai awal aman agar objek siap digunakan.
     */
    public Kamar() {
        // Mengisi nilai awal default melalui setter
        setNomorKamar("KM-000");
        setTipe("Standard");
        setTarifPerMalam(150000.0);
        setTerisi(false);
    }

    /**
     * Konstruktor berparameter untuk menginisialisasi data kamar secara tervalidasi.
     *
     * @param nomorKamar nomor atau kode unik kamar
     * @param tipe tipe kamar (misalnya Standard, Deluxe, Suite)
     * @param tarifPerMalam tarif sewa per malam dalam rupiah
     * @param terisi status awal kamar (true jika terisi, false jika kosong)
     */
    public Kamar(String nomorKamar, String tipe, double tarifPerMalam, boolean terisi) {
        // Memanggil setter agar validasi tetap berjalan saat konstruksi objek
        setNomorKamar(nomorKamar);
        setTipe(tipe);
        setTarifPerMalam(tarifPerMalam);
        setTerisi(terisi);
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public String getTipe() {
        return tipe;
    }

    public double getTarifPerMalam() {
        return tarifPerMalam;
    }

    public boolean isTerisi() {
        return terisi;
    }

    public void setNomorKamar(String nomorKamar) {
        // Validasi: nomor kamar tidak boleh kosong atau null
        if (nomorKamar == null || nomorKamar.trim().isEmpty()) {
            System.out.println("Peringatan: Nomor kamar tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        this.nomorKamar = nomorKamar.trim();
    }

    public void setTipe(String tipe) {
        // Validasi: tipe kamar tidak boleh kosong atau null
        if (tipe == null || tipe.trim().isEmpty()) {
            System.out.println("Peringatan: Tipe kamar tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        this.tipe = tipe.trim();
    }

    public void setTarifPerMalam(double tarifPerMalam) {
        // Validasi: tarif per malam harus lebih besar dari 0
        if (tarifPerMalam <= 0) {
            System.out.println("Peringatan: Tarif per malam harus lebih dari 0. Nilai tidak diubah.");
            return;
        }
        this.tarifPerMalam = tarifPerMalam;
    }

    public void setTerisi(boolean terisi) {
        this.terisi = terisi;
    }

    /**
     * Menandai kamar sebagai terisi apabila saat ini masih kosong.
     *
     * @return true jika berhasil ditandai terisi, false jika kamar sudah terisi
     */
    public boolean tandaiTerisi() {
        if (this.terisi) {
            System.out.println("Gagal: Kamar " + this.nomorKamar + " sedang terisi dan tidak bisa dipesan.");
            return false;
        }
        this.terisi = true;
        return true;
    }

    /**
     * Mengosongkan kembali status kamar (misalnya saat check-out atau pembatalan reservasi).
     */
    public void kosongkanKamar() {
        this.terisi = false;
    }

    /**
     * Menghitung biaya menginap berdasarkan tarif per malam dikalikan jumlah malam.
     *
     * @param jumlahMalam durasi menginap dalam malam
     * @return total biaya (0 jika jumlah malam tidak valid)
     */
    public double hitungBiaya(int jumlahMalam) {
        if (jumlahMalam <= 0) {
            System.out.println("Peringatan: Jumlah malam harus lebih dari 0.");
            return 0.0;
        }
        return this.tarifPerMalam * jumlahMalam;
    }
}