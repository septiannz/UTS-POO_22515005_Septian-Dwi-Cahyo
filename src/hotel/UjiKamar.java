package hotel;

public class UjiKamar {
    public static void main(String[] args) {
        // 1. Uji konstruktor default
        Kamar kamarDefault = new Kamar();
        System.out.println("Default: " + kamarDefault.getNomorKamar() + " - " + kamarDefault.getTarifPerMalam());

        // 2. Uji konstruktor berparameter
        Kamar kamar101 = new Kamar("KM-101", "Deluxe", 350000.0, false);
        System.out.println("Kamar 101: " + kamar101.getNomorKamar() + ", tarif: " + kamar101.getTarifPerMalam());

        // 3. Uji validasi setter (nilai tidak valid harus ditolak tanpa crash)
        kamar101.setTarifPerMalam(-50000.0);
        kamar101.setNomorKamar("");
        System.out.println("Setelah input tidak valid -> nomor: " + kamar101.getNomorKamar() + ", tarif: " + kamar101.getTarifPerMalam());

        // 4. Uji method perilaku & aturan bisnis
        System.out.println("Biaya 3 malam: " + kamar101.hitungBiaya(3));
        System.out.println("Pesan pertama kali: " + kamar101.tandaiTerisi());
        System.out.println("Pesan kedua kali (harus gagal): " + kamar101.tandaiTerisi());
        kamar101.kosongkanKamar();
        System.out.println("Setelah dikosongkan, status terisi: " + kamar101.isTerisi());
    }
}