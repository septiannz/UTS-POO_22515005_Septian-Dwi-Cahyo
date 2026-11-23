package hotel;

/**
 * Merepresentasikan data tamu hotel beserta informasi kontak dan riwayat jumlah kunjungannya.
 */
public class Tamu {
    private String idTamu;
    private String nama;
    private String noTelepon;
    private int jumlahKunjungan;

    /**
     * Konstruktor default dengan nilai awal aman.
     */
    public Tamu() {
        // Memanggil setter agar nilai awal tetap melewati aturan validasi
        setIdTamu("TM-000");
        setNama("Tamu Umum");
        setNoTelepon("0800000000");
        setJumlahKunjungan(0);
    }

    /**
     * Konstruktor berparameter untuk membuat objek tamu dengan data tervalidasi.
     *
     * @param idTamu ID unik tamu
     * @param nama nama lengkap tamu
     * @param noTelepon nomor telepon aktif tamu
     * @param jumlahKunjungan jumlah kunjungan tamu sebelumnya
     */
    public Tamu(String idTamu, String nama, String noTelepon, int jumlahKunjungan) {
        // Menggunakan setter di konstruktor agar validasi selalu diterapkan
        setIdTamu(idTamu);
        setNama(nama);
        setNoTelepon(noTelepon);
        setJumlahKunjungan(jumlahKunjungan);
    }

    public String getIdTamu() {
        return idTamu;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public int getJumlahKunjungan() {
        return jumlahKunjungan;
    }

    public void setIdTamu(String idTamu) {
        // Validasi: ID tamu tidak boleh kosong atau null
        if (idTamu == null || idTamu.trim().isEmpty()) {
            System.out.println("Peringatan: ID tamu tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        this.idTamu = idTamu.trim();
    }

    public void setNama(String nama) {
        // Validasi: nama tamu tidak boleh kosong atau null
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Peringatan: Nama tamu tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        this.nama = nama.trim();
    }

    public void setNoTelepon(String noTelepon) {
        // Validasi: nomor telepon harus ada dan minimal 6 karakter digit
        if (noTelepon == null || noTelepon.trim().length() < 6) {
            System.out.println("Peringatan: Nomor telepon minimal 6 karakter. Nilai tidak diubah.");
            return;
        }
        this.noTelepon = noTelepon.trim();
    }

    public void setJumlahKunjungan(int jumlahKunjungan) {
        // Validasi: jumlah kunjungan tidak boleh negatif
        if (jumlahKunjungan < 0) {
            System.out.println("Peringatan: Jumlah kunjungan tidak boleh negatif. Nilai tidak diubah.");
            return;
        }
        this.jumlahKunjungan = jumlahKunjungan;
    }

    /**
     * Menambah jumlah kunjungan tamu sebanyak 1 kali.
     */
    public void tambahKunjungan() {
        this.jumlahKunjungan++;
    }

    /**
     * Memeriksa apakah nomor telepon tamu terdiri dari angka yang valid.
     *
     * @return true jika nomor telepon berisi digit numerik, false jika tidak
     */
    public boolean punyaKontakValid() {
        if (this.noTelepon == null || this.noTelepon.isEmpty()) {
            return false;
        }
        return this.noTelepon.matches("\\d+");
    }

    /**
     * Menghasilkan ringkasan singkat profil tamu.
     *
     * @return string ringkasan data tamu
     */
    public String ringkasanProfil() {
        return "[" + idTamu + "] " + nama + " | Telp: " + noTelepon + " | Kunjungan: " + jumlahKunjungan;
    }
}