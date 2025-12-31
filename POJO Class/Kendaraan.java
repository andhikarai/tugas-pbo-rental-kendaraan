

public class Kendaraan {
    private String id;
    private String nama;
    private String tipe; // misal: "Mobil", "Motor", "SUV"
    private String platNomor;
    private double hargaPerHari;
    private boolean tersedia; // true = tersedia, false = sedang disewa

    // Constructor
    public Kendaraan(String id, String nama, String tipe, String platNomor, double hargaPerHari) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
        this.platNomor = platNomor;
        this.hargaPerHari = hargaPerHari;
        this.tersedia = true; // default: tersedia
    }

    // Default constructor
    public Kendaraan() {}

    // Getter & Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public String getPlatNomor() { return platNomor; }
    public void setPlatNomor(String platNomor) { this.platNomor = platNomor; }

    public double getHargaPerHari() { return hargaPerHari; }
    public void setHargaPerHari(double hargaPerHari) { this.hargaPerHari = hargaPerHari; }

    public boolean isTersedia() { return tersedia; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }

    // Metode
    public String getDetail() {
        return String.format("ID: %s | Nama: %s | Tipe: %s | Plat: %s | Harga/hari: Rp%.0f | Status: %s",
                id, nama, tipe, platNomor, hargaPerHari, tersedia ? "Tersedia" : "Disewa");
    }

    public void ubahStatus(boolean statusBaru) {
        this.tersedia = statusBaru;
    }

    @Override
    public String toString() {
        return "Kendaraan{" + getDetail() + "}";
    }
}