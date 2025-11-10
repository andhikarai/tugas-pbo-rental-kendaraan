// import libraries yang dibutuhkan
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class JavaCRUD {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/rental_kendaraan";
    static final String USER = "root";
    static final String PASS = "Bintang11";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    final static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    static String levelUser; // untuk menyimpan role login (admin / penyewa)

    public static void main(String[] args) {
        try {
            // register driver
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // login dulu sebelum akses menu
            if (login()) {
                while (!conn.isClosed()) {
                    showMenu();
                }
            } else {
                System.out.println("Login gagal. Program keluar.");
                System.exit(0);
            }

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    // ===================== LOGIN METHOD =====================
    static boolean login() {
        try {
            System.out.println("===== LOGIN RENTAL KENDARAAN =====");
            System.out.print("Username: ");
            String username = input.readLine();
            System.out.print("Password: ");
            String password = input.readLine();

            String sql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                levelUser = rs.getString("level_user");
                System.out.println("\nLogin berhasil sebagai: " + levelUser.toUpperCase());
                return true;
            } else {
                System.out.println("Username atau password salah!");
                return false;
            }

        } catch (IOException | SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // ===================== MENU UTAMA =====================
    static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Lihat Data Kendaraan");
        if (levelUser.equalsIgnoreCase("admin")) {
            System.out.println("2. Tambah Data Kendaraan");
            System.out.println("3. Edit Data Kendaraan");
            System.out.println("4. Hapus Data Kendaraan");
        }
        System.out.println("0. Keluar");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());
            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    showData();
                    break;
                case 2:
                    if (levelUser.equalsIgnoreCase("admin"))
                        insertKendaraan();
                    else
                        System.out.println("Akses ditolak. Hanya admin yang bisa menambah data.");
                    break;
                case 3:
                    if (levelUser.equalsIgnoreCase("admin"))
                        updateKendaraan();
                    else
                        System.out.println("Akses ditolak. Hanya admin yang bisa mengedit data.");
                    break;
                case 4:
                    if (levelUser.equalsIgnoreCase("admin"))
                        deleteKendaraan();
                    else
                        System.out.println("Akses ditolak. Hanya admin yang bisa menghapus data.");
                    break;
                default:
                    System.out.println("Pilihan salah!");
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    // ===================== TAMPIL DATA =====================
    static void showData() {
        String sql = "SELECT * FROM kendaraan";

        try {
            rs = stmt.executeQuery(sql);

            System.out.println("+--------------------------------+");
            System.out.println("|     DATA KENDARAAN RENTAL      |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int id = rs.getInt("id_kendaraan");
                String nama = rs.getString("nama_kendaraan");
                String jenis = rs.getString("jenis");
                int harga = rs.getInt("harga_sewa");

                System.out.println(String.format("%d. %s (%s) - Rp%d/hari", id, nama, jenis, harga));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // ===================== TAMBAH DATA =====================
    static void insertKendaraan() {
        try {
            System.out.print("Nama Kendaraan: ");
            String nama = input.readLine().trim();
            System.out.print("Jenis (Mobil/Motor): ");
            String jenis = input.readLine().trim();
            System.out.print("Harga Sewa per Hari: ");
            int harga = Integer.parseInt(input.readLine());

            String sql = String.format("INSERT INTO kendaraan (nama_kendaraan, jenis, harga_sewa) VALUES('%s','%s',%d)", nama, jenis, harga);
            stmt.execute(sql);
            System.out.println("Data kendaraan berhasil ditambahkan!");

        } catch (IOException | SQLException e) {
            System.out.println(e);
        }
    }

    // ===================== UPDATE DATA =====================
    static void updateKendaraan() {
        try {
            System.out.print("ID kendaraan yang mau diedit: ");
            int id = Integer.parseInt(input.readLine());
            System.out.print("Nama Kendaraan: ");
            String nama = input.readLine().trim();
            System.out.print("Jenis: ");
            String jenis = input.readLine().trim();
            System.out.print("Harga Sewa: ");
            int harga = Integer.parseInt(input.readLine());

            String sql = String.format("UPDATE kendaraan SET nama_kendaraan='%s', jenis='%s', harga_sewa=%d WHERE id_kendaraan=%d", nama, jenis, harga, id);
            stmt.execute(sql);
            System.out.println("Data kendaraan berhasil diperbarui!");

        } catch (IOException | SQLException e) {
            System.out.println(e);
        }
    }

    // ===================== HAPUS DATA =====================
    static void deleteKendaraan() {
        try {
            System.out.print("ID kendaraan yang mau dihapus: ");
            int id = Integer.parseInt(input.readLine());
            String sql = String.format("DELETE FROM kendaraan WHERE id_kendaraan=%d", id);
            stmt.execute(sql);
            System.out.println("Data kendaraan berhasil dihapus!");

        } catch (IOException | SQLException e) {
            System.out.println(e);
        }
    }
}