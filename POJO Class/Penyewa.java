

public class Penyewa extends Pengguna {

    // Constructor mengisi super()
    public Penyewa(String idUser, String nama, String alamat, String noTelepon,
                   String email, String username, String password) {
        super(idUser, nama, alamat, noTelepon, email, username, password, "penyewa");
    }

    // Default constructor
    public Penyewa() {
        super();
        setRole("penyewa");
    }

    // Metode khusus Penyewa
    public void lihatDaftarKendaraan() {
        // Akan dipanggil oleh controller, lalu memanggil KendaraanDAO.ambilTersedia()
        System.out.println("Menampilkan daftar kendaraan tersedia...");
    }

    public void cariKendaraan(String keyword) {
        System.out.println("Mencari kendaraan dengan keyword: " + keyword);
    }

    public void buatPemesanan(Kendaraan kendaraan, java.time.LocalDate tglMulai, java.time.LocalDate tglSelesai) {
        if (kendaraan == null || !kendaraan.isTersedia()) {
            System.out.println("Kendaraan tidak tersedia.");
            return;
        }
        // Akan dibuatkan objek Transaksi & disimpan via TransaksiDAO
        System.out.println("Pemesanan dibuat untuk kendaraan: " + kendaraan.getNama());
    }
}