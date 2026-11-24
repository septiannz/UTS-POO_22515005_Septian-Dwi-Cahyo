package hotel;

public class UjiStackReservasi {
    public static void main(String[] args) {
        // 1. Uji Stack kosong (pop, peek, size, isEmpty)
        StackReservasi stack = new StackReservasi(2);
        System.out.println("Awal kosong? " + stack.isEmpty() + ", size: " + stack.size());
        System.out.println("Peek saat kosong: " + stack.peek());
        System.out.println("Pop saat kosong: " + stack.pop());

        // 2. Siapkan data dummy untuk push
        Tamu t1 = new Tamu("TM-001", "Aldi", "081111111111", 1);
        Tamu t2 = new Tamu("TM-002", "Bela", "082222222222", 1);
        Tamu t3 = new Tamu("TM-003", "Citra", "083333333333", 1);

        Kamar k1 = new Kamar("KM-01", "Standard", 200000, false);
        Kamar k2 = new Kamar("KM-02", "Deluxe", 350000, false);
        Kamar k3 = new Kamar("KM-03", "Suite", 500000, false);

        Reservasi r1 = new Reservasi("RSV-01", t1, k1, 1);
        Reservasi r2 = new Reservasi("RSV-02", t2, k2, 2);
        Reservasi r3 = new Reservasi("RSV-03", t3, k3, 1);

        // 3. Push sampai penuh (kapasitas 2), lalu coba push ke-3 (harus gagal tanpa crash)
        stack.push(r1);
        stack.push(r2);
        System.out.println("Penuh? " + stack.isFull() + ", size: " + stack.size());
        stack.push(r3); // harus gagal karena penuh

        // 4. Peek dan tampilkan isi
        System.out.println(stack.tampilkanIsi());
        Reservasi teratas = stack.peek();
        if (teratas != null) {
            System.out.println("Elemen TOP (peek): " + teratas.getKodeReservasi());
        }

        // 5. Cari berdasarkan kode (ada & tidak ada)
        System.out.println("Cari RSV-01: " + (stack.cariBerdasarkanKode("RSV-01") != null ? "Ditemukan" : "Tidak ada"));
        System.out.println("Cari RSV-99: " + (stack.cariBerdasarkanKode("RSV-99") != null ? "Ditemukan" : "Tidak ada"));

        // 6. Pop semua elemen (LIFO: r2 dulu baru r1)
        Reservasi pop1 = stack.pop();
        Reservasi pop2 = stack.pop();
        System.out.println("Pop pertama: " + (pop1 != null ? pop1.getKodeReservasi() : "-"));
        System.out.println("Pop kedua: " + (pop2 != null ? pop2.getKodeReservasi() : "-"));
        System.out.println("Setelah semua di-pop, kosong? " + stack.isEmpty());
    }
}
