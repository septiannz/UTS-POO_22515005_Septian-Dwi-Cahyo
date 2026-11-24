package hotel;

import java.util.ArrayList;

/**
 * Merepresentasikan entitas hotel yang mengelola koleksi kamar, tamu, reservasi,
 * serta riwayat pembatalan reservasi terakhir menggunakan struktur data Stack.
 */
public class Hotel {
    private String namaHotel;
    private ArrayList<Kamar> daftarKamar;
    private ArrayList<Tamu> daftarTamu;
    private ArrayList<Reservasi> daftarReservasi;
    private StackReservasi riwayatBatal;

    /**
     * Konstruktor default dengan nama hotel bawaan dan kapasitas Stack standar.
     */
    public Hotel() {
        // Menginisialisasi koleksi dan Stack melalui setter agar aman dari null
        setNamaHotel("Hotel Nusantara");
        this.daftarKamar = new ArrayList<>();
        this.daftarTamu = new ArrayList<>();
        this.daftarReservasi = new ArrayList<>();
        this.riwayatBatal = new StackReservasi(10);
    }

    /**
     * Konstruktor berparameter untuk menentukan nama hotel dan kapasitas Stack pembatalan.
     *
     * @param namaHotel nama hotel
     * @param kapasitasStack kapasitas maksimal Stack pembatalan reservasi
     */
    public Hotel(String namaHotel, int kapasitasStack) {
        // Memanggil setter untuk validasi nama hotel
        setNamaHotel(namaHotel);
        this.daftarKamar = new ArrayList<>();
        this.daftarTamu = new ArrayList<>();
        this.daftarReservasi = new ArrayList<>();
        this.riwayatBatal = new StackReservasi(kapasitasStack);
    }

    public String getNamaHotel() {
        return namaHotel;
    }

    public ArrayList<Kamar> getDaftarKamar() {
        return daftarKamar;
    }

    public ArrayList<Tamu> getDaftarTamu() {
        return daftarTamu;
    }

    public ArrayList<Reservasi> getDaftarReservasi() {
        return daftarReservasi;
    }

    public StackReservasi getRiwayatBatal() {
        return riwayatBatal;
    }

    public void setNamaHotel(String namaHotel) {
        // Validasi: nama hotel tidak boleh kosong atau null
        if (namaHotel == null || namaHotel.trim().isEmpty()) {
            System.out.println("Peringatan: Nama hotel tidak boleh kosong. Nilai tidak diubah.");
            return;
        }
        this.namaHotel = namaHotel.trim();
    }

    /**
     * Menambahkan kamar baru ke daftar kamar hotel jika nomor kamarnya belum terdaftar.
     *
     * @param kamar objek Kamar yang akan ditambahkan
     * @return true jika berhasil ditambahkan, false jika gagal/duplikat
     */
    public boolean tambahKamar(Kamar kamar) {
        if (kamar == null) {
            System.out.println("Gagal: Objek kamar tidak boleh kosong.");
            return false;
        }
        if (cariKamarByNomor(kamar.getNomorKamar()) != null) {
            System.out.println("Gagal: Kamar dengan nomor " + kamar.getNomorKamar() + " sudah terdaftar.");
            return false;
        }
        daftarKamar.add(kamar);
        return true;
    }

    /**
     * Menambahkan data tamu baru ke daftar tamu hotel jika ID tamunya belum terdaftar.
     *
     * @param tamu objek Tamu yang akan ditambahkan
     * @return true jika berhasil ditambahkan, false jika gagal/duplikat
     */
    public boolean tambahTamu(Tamu tamu) {
        if (tamu == null) {
            System.out.println("Gagal: Objek tamu tidak boleh kosong.");
            return false;
        }
        if (cariTamuById(tamu.getIdTamu()) != null) {
            System.out.println("Gagal: Tamu dengan ID " + tamu.getIdTamu() + " sudah terdaftar.");
            return false;
        }
        daftarTamu.add(tamu);
        return true;
    }

    /**
     * Membuat reservasi baru dengan menghubungkan Tamu dan Kamar serta menerapkan aturan bisnis.
     *
     * @param kodeReservasi kode unik reservasi
     * @param idTamu ID tamu pemesan
     * @param nomorKamar nomor kamar yang dipesan
     * @param jumlahMalam jumlah malam menginap
     * @return objek Reservasi jika berhasil dibuat, atau null jika gagal
     */
    public Reservasi buatReservasi(String kodeReservasi, String idTamu, String nomorKamar, int jumlahMalam) {
        if (kodeReservasi == null || kodeReservasi.trim().isEmpty()) {
            System.out.println("Gagal: Kode reservasi tidak boleh kosong.");
            return null;
        }
        if (cariReservasiByKode(kodeReservasi.trim()) != null) {
            System.out.println("Gagal: Kode reservasi " + kodeReservasi + " sudah digunakan.");
            return null;
        }
        if (jumlahMalam <= 0) {
            System.out.println("Gagal: Jumlah malam harus lebih dari 0.");
            return null;
        }

        Tamu tamuDitemukan = cariTamuById(idTamu);
        if (tamuDitemukan == null) {
            System.out.println("Gagal: Tamu dengan ID " + idTamu + " tidak ditemukan.");
            return null;
        }

        Kamar kamarDitemukan = cariKamarByNomor(nomorKamar);
        if (kamarDitemukan == null) {
            System.out.println("Gagal: Kamar dengan nomor " + nomorKamar + " tidak ditemukan.");
            return null;
        }

        // Aturan bisnis: kamar terisi tidak bisa dipesan
        if (kamarDitemukan.isTerisi()) {
            System.out.println("Gagal: Kamar " + kamarDitemukan.getNomorKamar() + " sedang terisi dan tidak bisa dipesan.");
            return null;
        }

        Reservasi reservasiBaru = new Reservasi(kodeReservasi.trim(), tamuDitemukan, kamarDitemukan, jumlahMalam);
        if (!reservasiBaru.isStatusAktif()) {
            return null;
        }

        daftarReservasi.add(reservasiBaru);
        return reservasiBaru;
    }

    /**
     * Membatalkan reservasi aktif terakhir, mengosongkan kamarnya, dan mendorongnya ke Stack riwayat batal.
     *
     * @return objek Reservasi yang dibatalkan, atau null jika tidak ada reservasi aktif
     */
    public Reservasi batalkanReservasiTerakhir() {
        for (int i = daftarReservasi.size() - 1; i >= 0; i--) {
            Reservasi rsv = daftarReservasi.get(i);
            if (rsv.isStatusAktif()) {
                if (riwayatBatal.isFull()) {
                    System.out.println("Gagal: Stack riwayat pembatalan sudah penuh.");
                    return null;
                }
                rsv.batalkan();
                riwayatBatal.push(rsv);
                return rsv;
            }
        }
        System.out.println("Info: Tidak ada reservasi aktif yang dapat dibatalkan.");
        return null;
    }

    /**
     * Mencari data kamar, tamu, atau reservasi berdasarkan satu kunci (kode/ID/nomor atau nama).
     *
     * @param kataKunci kata kunci pencarian
     * @return hasil pencarian dalam bentuk teks ringkas
     */
    public String cariData(String kataKunci) {
        if (kataKunci == null || kataKunci.trim().isEmpty()) {
            return "Kata kunci pencarian tidak boleh kosong.";
        }
        String kunci = kataKunci.trim().toLowerCase();
        StringBuilder hasil = new StringBuilder();

        for (Kamar k : daftarKamar) {
            if (k.getNomorKamar().toLowerCase().contains(kunci) || k.getTipe().toLowerCase().contains(kunci)) {
                hasil.append("[Kamar] ").append(k.getNomorKamar())
                        .append(" | Tipe: ").append(k.getTipe())
                        .append(" | Tarif: Rp").append(k.getTarifPerMalam())
                        .append(" | Terisi: ").append(k.isTerisi()).append("\n");
            }
        }

        for (Tamu t : daftarTamu) {
            if (t.getIdTamu().toLowerCase().contains(kunci) || t.getNama().toLowerCase().contains(kunci)) {
                hasil.append("[Tamu] ").append(t.ringkasanProfil()).append("\n");
            }
        }

        for (Reservasi r : daftarReservasi) {
            boolean cocokKode = r.getKodeReservasi().toLowerCase().contains(kunci);
            boolean cocokNama = r.getTamu() != null && r.getTamu().getNama().toLowerCase().contains(kunci);
            if (cocokKode || cocokNama) {
                hasil.append("[Reservasi] ").append(r.ringkasanTransaksi()).append("\n");
            }
        }

        if (hasil.length() == 0) {
            return "Data dengan kata kunci '" + kataKunci.trim() + "' tidak ditemukan.";
        }
        return hasil.toString().trim();
    }

    /**
     * Menghitung total pendapatan dari seluruh reservasi yang masih aktif.
     *
     * @return total pendapatan aktif
     */
    public double hitungTotalPendapatanAktif() {
        double total = 0.0;
        for (Reservasi r : daftarReservasi) {
            if (r.isStatusAktif()) {
                total += r.getTotalBiaya();
            }
        }
        return total;
    }

    private Kamar cariKamarByNomor(String nomorKamar) {
        if (nomorKamar == null) {
            return null;
        }
        for (Kamar k : daftarKamar) {
            if (k.getNomorKamar().equalsIgnoreCase(nomorKamar.trim())) {
                return k;
            }
        }
        return null;
    }

    private Tamu cariTamuById(String idTamu) {
        if (idTamu == null) {
            return null;
        }
        for (Tamu t : daftarTamu) {
            if (t.getIdTamu().equalsIgnoreCase(idTamu.trim())) {
                return t;
            }
        }
        return null;
    }

    private Reservasi cariReservasiByKode(String kodeReservasi) {
        if (kodeReservasi == null) {
            return null;
        }
        for (Reservasi r : daftarReservasi) {
            if (r.getKodeReservasi().equalsIgnoreCase(kodeReservasi.trim())) {
                return r;
            }
        }
        return null;
    }
}