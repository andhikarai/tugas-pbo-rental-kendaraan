

public abstract class Pengguna {
    private String idUser;
    private String nama;
    private String alamat;
    private String noTelepon;
    private String email;
    private String username;
    private String password;
    private String role; // "admin" atau "penyewa"

    // Constructor
    public Pengguna(String idUser, String nama, String alamat, String noTelepon,
                    String email, String username, String password, String role) {
        this.idUser = idUser;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Default constructor (untuk DAO/ORM)
    public Pengguna() {}

    // Getter & Setter
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Metode umum pengguna
    public void login() {
        System.out.println(username + " berhasil login.");
    }

    public void logout() {
        System.out.println(username + " telah logout.");
    }

    public void updateProfil(String nama, String alamat, String noTelepon, String email) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pengguna{id='" + idUser + "', nama='" + nama + "', username='" + username + "', role='" + role + "'}";
    }
}