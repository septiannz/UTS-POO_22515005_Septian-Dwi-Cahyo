BAB 1: ANALISIS KONSEP PEMROGRAMAN BERORIENTASI OBJEK (OOP)
1.1 Deskripsi Sistem Hotel dan Masalah yang Diselesaikan
Sistem manajemen hotel berbasis konsol ini dirancang untuk mengelola data kamar, pendataan tamu, pencatatan reservasi, perhitungan biaya menginap, serta pengelolaan riwayat pembatalan reservasi terakhir menggunakan struktur data Stack buatan sendiri. Melalui sistem ini, pengelola dapat menambahkan dan melihat daftar kamar serta tamu, membuat reservasi yang menghubungkan tamu dengan kamar tertentu, mencari data berdasarkan kunci tertentu, serta memantau ringkasan operasional seperti jumlah data dan total biaya.

Dalam pengelolaan hotel secara manual atau menggunakan pencatatan terpisah, terdapat beberapa masalah yang sering terjadi:

Risiko bentrok pemesanan kamar: Kamar yang sedang terisi dapat tidak sengaja dipesan kembali karena status ketersediaannya tidak terkelola secara konsisten.

Kesalahan perhitungan biaya: Perhitungan biaya menginap yang dilakukan secara manual atau terpisah dari data reservasi rawan keliru ketika jumlah malam atau tarif kamar berubah.

Kesulitan melacak pembatalan reservasi terakhir: Operasi pembatalan yang sering menyasar transaksi paling akhir memerlukan mekanisme Last-In, First-Out (LIFO) yang jelas agar riwayat aktivitas tetap tertib.

Masukan pengguna yang tidak valid: Pada aplikasi berbasis konsol, input kosong, huruf pada kolom angka, atau pelanggaran aturan bisnis dapat menyebabkan program berhenti mendadak (crash) atau menyimpan data yang keliru.

Dengan menerapkan pendekatan berorientasi objek yang dipadukan dengan Stack berkapasitas terukur, setiap aturan bisnis dan perubahan status data dapat dikendalikan secara terpusat dan terprediksi.

1.2 Identifikasi Objek Dunia Nyata sebagai Kelas
Pada domain sistem hotel, empat entitas utama dimodelkan sebagai kelas terpisah karena masing-masing memiliki atribut keadaan (state) dan perilaku (behavior) yang berbeda:

Kamar (Kamar)

Alasan dijadikan kelas: Kamar merupakan unit inventaris utama hotel yang memiliki identitas, tipe/kelas, tarif per malam, serta status ketersediaan (kosong atau terisi). Memisahkannya ke dalam kelas tersendiri memudahkan pelacakan status kamar, perubahan status saat reservasi dibuat atau dibatalkan, serta pencegahan pemesanan ulang pada kamar yang sedang terisi.

Tamu (Tamu)

Alasan dijadikan kelas: Tamu adalah pihak yang melakukan pemesanan layanan hotel dan memiliki data identitas seperti ID/kode tamu, nama, serta informasi kontak. Menjadikannya kelas sendiri memisahkan data personal pelanggan dari detail transaksi kamar sehingga data tamu dapat digunakan kembali pada berbagai reservasi maupun pencarian.

Reservasi (Reservasi)

Alasan dijadikan kelas: Reservasi merepresentasikan transaksi pemesanan yang mengaitkan tamu dan kamar untuk durasi menginap tertentu (jumlah malam) serta menghasilkan total biaya berdasarkan aturan biaya = tarif × malam. Kelas ini menjaga konsistensi perhitungan biaya dan siklus hidup transaksi, termasuk saat proses pembatalan melalui struktur data Stack.

Hotel (Hotel)

Alasan dijadikan kelas: Hotel berperan sebagai wadah pengelola utama yang menaungi kumpulan data kamar, tamu, reservasi, serta operasi Stack pembatalan reservasi. Memodelkan Hotel sebagai kelas terpisah membantu menyatukan logika pengelolaan koleksi data, pencarian berdasarkan kunci, dan pembuatan ringkasan laporan agar kelas Main fokus pada interaksi menu konsol.

1.3 Perbandingan Pendekatan Prosedural vs. OOP pada Kasus Hotel
Pada pendekatan prosedural, data kamar, tamu, reservasi, dan status ketersediaan biasanya disimpan dalam variabel atau array terpisah yang berdiri sendiri, lalu dimanipulasi oleh banyak fungsi global. Jika jumlah fitur bertambah—misalnya menambahkan aturan bahwa kamar terisi tidak bisa dipesan atau memperbarui status saat reservasi dibatalkan—pengembang harus memastikan setiap fungsi memperbarui seluruh array terkait dengan urutan yang tepat. Ketergantungan implisit antarvariabel ini membuat kode mudah menimbulkan bug saat skala program membesar.

Sebaliknya, pada pendekatan Pemrograman Berorientasi Objek (OOP), data dan perilaku yang saling berkaitan disatukan ke dalam satu objek. Misalnya, objek Reservasi menyimpan informasi kamar, tamu, jumlah malam, dan biaya sekaligus menyediakan logika perhitungannya, sementara objek Kamar menjaga statusnya sendiri agar tidak mudah diubah sembarangan dari luar. Struktur ini membuat alur sistem hotel lebih modular, mudah dibaca, dan lebih aman ketika aturan bisnis diperketat.

1.4 Abstraksi dan Enkapsulasi serta Konsep OOP Lainnya
Abstraksi
Abstraksi pada sistem hotel diterapkan dengan menyembunyikan kerumitan teknis di balik antarmuka operasi yang jelas. Contohnya, saat menu konsol memproses reservasi atau pembatalan reservasi terakhir, pengguna maupun pemanggil cukup berinteraksi dengan operasi tingkat tinggi (seperti membuat reservasi, mencari data, atau melakukan push/pop pada Stack), tanpa perlu mengurus detail internal bagaimana array/list dikelola atau bagaimana biaya dihitung di balik layar. Hal yang sama berlaku pada kelas Stack buatan sendiri yang menyembunyikan penggunaan ArrayList internal dan pengecekan kapasitas di balik operasi publiknya.

Enkapsulasi
Enkapsulasi diterapkan dengan menjadikan seluruh atribut pada kelas-kelas entitas bersifat private sehingga tidak dapat diakses atau diubah secara langsung dari luar kelas. Perubahan nilai dilakukan melalui konstruktor dan method setter yang dilengkapi validasi (misalnya mencegah tarif negatif, jumlah malam tidak valid, atau pemesanan kamar yang sedang terisi). Dengan cara ini, integritas data dan aturan bisnis biaya = tarif × malam tetap terjaga di satu tempat.
