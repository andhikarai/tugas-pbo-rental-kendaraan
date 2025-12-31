
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Status transaksi sebagai enum supaya lebih aman & readable
enum StatusTransaksi {
    MENUNGGU, DIPROSES, SELESAI, DIBATALKAN
}

public class Transaksi {
    private String idTransaksi;
    private Penyewa penyewa;
    private Kendaraan kendaraan;
    private LocalDate tanggalMulai;
    private LocalDate tanggalSelesai;
    private int lamaSewaHari;
    private double totalBiaya;
    private StatusTransaksi statusTransaksi;

    // Constructor
    public Transaksi(String idTransaksi, Penyewa penyewa, Kendaraan kendaraan,
                     LocalDate tanggalMulai, LocalDate tanggalSelesai) {
        this.idTransaksi = idTransaksi;
        this.penyewa = penyewa;
        this.kendaraan = kendaraan;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.lamaSewaHari = (int) ChronoUnit.DAYS.between(tanggalMulai, tanggalSelesai);
        this.statusTransaksi = StatusTransaksi.MENUNGGU;
        hitungTotalBiaya(); // akan menghitung otomatis saat objek dibuat
    }

    // Default constructor
    public Transaksi() {}

    // Getter & Setter
    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

    public Penyewa getPenyewa() { return penyewa; }
    public void setPenyewa(Penyewa penyewa) { this.penyewa = penyewa; }

    public Kendaraan getKendaraan() { return kendaraan; }
    public void setKendaraan(Kendaraan kendaraan) { this.kendaraan = kendaraan; }

    public LocalDate getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(LocalDate tanggalMulai) { this.tanggalMulai = tanggalMulai; }

    public LocalDate getTanggalSelesai() { return tanggalSelesai; }
    public void setTanggalSelesai(LocalDate tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
        this.lamaSewaHari = (int) ChronoUnit.DAYS.between(tanggalMulai, tanggalSelesai);
        hitungTotalBiaya();
    }

    public int getLamaSewaHari() { return lamaSewaHari; }
   

    public double getTotalBiaya() { return totalBiaya; }


    public StatusTransaksi getStatusTransaksi() { return statusTransaksi; }
    public void setStatusTransaksi(StatusTransaksi statusTransaksi) { this.statusTransaksi = statusTransaksi; }

    // Metode
    public void hitungTotalBiaya() {
        if (kendaraan != null && lamaSewaHari > 0) {
            this.totalBiaya = kendaraan.getHargaPerHari() * lamaSewaHari;
        } else {
            this.totalBiaya = 0.0;
        }
    }

    public void konfirmasiPesanan() {
        if (statusTransaksi == StatusTransaksi.MENUNGGU) {
            this.statusTransaksi = StatusTransaksi.DIPROSES;
            if (kendaraan != null) {
                kendaraan.ubahStatus(false); // mengubah jadi "tidak tersedia"
            }
            System.out.println("Transaksi " + idTransaksi + " dikonfirmasi.");
        }
    }

    public void selesaikanTransaksi() {
        if (statusTransaksi == StatusTransaksi.DIPROSES) {
            this.statusTransaksi = StatusTransaksi.SELESAI;
            if (kendaraan != null) {
                kendaraan.ubahStatus(true); // mengembalikan ke "tersedia"
            }
            System.out.println("Transaksi " + idTransaksi + " selesai.");
        }
    }

    @Override
    public String toString() {
        return String.format("Transaksi[ID=%s, Penyewa=%s, Kendaraan=%s, Tgl=%s–%s, Lama=%d hari, Total=Rp%.0f, Status=%s]",
                idTransaksi, penyewa.getUsername(), kendaraan.getNama(),
                tanggalMulai, tanggalSelesai, lamaSewaHari, totalBiaya, statusTransaksi);
    }
}