package hotel;

public class UjiTamu {
    public static void main(String[] args) {
        // 1. Uji konstruktor default
        Tamu tamuDefault = new Tamu();
        System.out.println("Default: " + tamuDefault.ringkasanProfil());

        // 2. Uji konstruktor berparameter
        Tamu tamuBudi = new Tamu("TM-001", "Budi Santoso", "081234567890", 1);
        System.out.println("Tamu awal: " + tamuBudi.ringkasanProfil());

        // 3. Uji validasi setter (nilai tidak valid harus ditolak tanpa crash)
        tamuBudi.setNama("");
        tamuBudi.setNoTelepon("123");
        tamuBudi.setJumlahKunjungan(-2);
        System.out.println("Setelah input tidak valid: " + tamuBudi.ringkasanProfil());

        // 4. Uji method perilaku
        System.out.println("Kontak valid? " + tamuBudi.punyaKontakValid());
        tamuBudi.tambahKunjungan();
        System.out.println("Setelah tambah kunjungan: " + tamuBudi.ringkasanProfil());
    }
}
