package hotel;

public class UjiReservasi {
    public static void main(String[] args) {
        Tamu tamu1 = new Tamu("TM-001", "Andi Pratama", "081298765432", 0);
        Tamu tamu2 = new Tamu("TM-002", "Siti Rahma", "085211223344", 0);
        Kamar kamar102 = new Kamar("KM-102", "Suite", 500000.0, false);

        // 1. Reservasi valid pada kamar kosong (3 malam -> 1.500.000)
        Reservasi rsv1 = new Reservasi("RSV-001", tamu1, kamar102, 3);
        System.out.println(rsv1.ringkasanTransaksi());
        System.out.println("Status kamar 102 terisi? " + kamar102.isTerisi());

        // 2. Coba pesan kamar yang sama saat sudah terisi (harus gagal)
        Reservasi rsv2 = new Reservasi("RSV-002", tamu2, kamar102, 2);
        System.out.println(rsv2.ringkasanTransaksi());

        // 3. Uji validasi jumlah malam tidak valid
        rsv1.setJumlahMalam(0);

        // 4. Uji pembatalan reservasi (kamar harus kembali kosong)
        rsv1.batalkan();
        System.out.println("Setelah dibatalkan -> " + rsv1.ringkasanTransaksi());
        System.out.println("Status kamar 102 terisi? " + kamar102.isTerisi());
    }
}