

public class Admin extends Pengguna {

    public Admin(String idUser, String nama, String alamat, String noTelepon,
                 String email, String username, String password) {
        super(idUser, nama, alamat, noTelepon, email, username, password, "admin");
    }

    public Admin() {
        super();
        setRole("admin");
    }

    // Metode khusus Admin
    public void tambahKendaraan(Kendaraan kendaraan) {
        System.out.println("Kendaraan " + kendaraan.getNama() + " berhasil ditambahkan.");
    }

    public void hapusKendaraan(String idKendaraan) {
        System.out.println("Kendaraan dengan ID " + idKendaraan + " dihapus.");
    }

    public void kelolaTransaksi() {
        System.out.println("Mengelola transaksi...");
    }

    public void generateLaporan(java.time.LocalDate dari, java.time.LocalDate sampai) {
        System.out.println("Laporan pendapatan dari " + dari + " sampai " + sampai);
    }
}
