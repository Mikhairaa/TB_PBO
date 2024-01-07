import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

public class Pelanggan extends DataPelanggan {
    //collection framework untuk menampilkan data yang diinputkan pelanggan
    private List<String> purchasedItems;

    String url = "jdbc:mysql://localhost:3306/shoesia"; 
    String username = "root";
    String password = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public Pelanggan() {
        //membuat object ArrayList
        purchasedItems = new ArrayList<>();
    }
    //method untuk menginputkan data pelanggan
    public void inputDataPelanggan() throws Exception{

        Scanner scanString = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        
        System.out.print("Masukkan nomor faktur = ");
        no_faktur = scanString.next();
        System.out.print("Masukkan nama pelanggan = ");
        nama = scanString.next();
        System.out.print("Masukkan alamat = ");
        alamat = scanString.next();
        System.out.print("Masukkan kode barang = ");
        kode_barang = scanString.next();
        System.out.print("Masukkan nama barang = ");
        namaBarang = scanString.next();
        System.out.print("Masukkan ukuran = ");
        ukuran = scanString.next();
        System.out.print("Masukkan warna = ");
        warna = scanString.next();
        System.out.print("Masukkan harga barang = ");
        hargaBarang = scanInt.nextInt();
        System.out.print("Masukkan jumlah beli = ");
        jumlahBarang = scanInt.nextInt();

        // detail produk yg dibeli pelanggan
        purchasedItems.add(namaBarang + " - " + ukuran + " - " + warna +
        " - Harga: " + hargaBarang + " - Jumlah: " + jumlahBarang);
    }
    //method untuk mencetak struk
    @Override
    public void struk() throws Exception{
        Integer totalBayar = hargaBarang*jumlahBarang;
        this.totalBayar = totalBayar;

        Date HariSekarang = new Date();
        SimpleDateFormat tg = new SimpleDateFormat("E, yyyy/MM/dd ");
        SimpleDateFormat wt = new SimpleDateFormat("hh:mm:ss zzz");

        System.out.println("----------------SHOESIA STORE---------------");
        System.out.println("Tanggal\t :"+tg.format(HariSekarang));
        System.out.println("Waktu\t   :"+wt.format(HariSekarang));
        System.out.println("No.Faktur   :"+ no_faktur);
        System.out.println("============================================");
        System.out.println("DATA PELANGGAN");
        System.out.println("--------------------------------------------" );
        System.out.println("Nama Pelanggan  = " + nama);
        System.out.println("Alamat          = " + alamat);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++" );
        System.out.println("DATA PEMBELIAN BARANG");
        System.out.println("--------------------------------------------" );

        //perulangan untuk menampilkan detail produk
        for (String item : purchasedItems) {
            System.out.println(item);
        }

        System.out.println("Kode Barang     = " + kode_barang);
        System.out.println("Nama Barang     = " + namaBarang);
        System.out.println("Ukuran          = " + ukuran);
        System.out.println("Warna           = " + warna);
        System.out.println("Harga Barang    = " + hargaBarang);
        System.out.println("Jumlah Beli     = " + jumlahBarang);
        System.out.println("TOTAL BAYAR     = " + this.totalBayar);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Kasir\t        : Khaira");
        System.out.println("--------------------------------------------" );
        //method string
        System.out.println("toUperrcase\t: " + nama.toUpperCase() );
        System.out.println("length\t\t: " + nama.length() );
        System.out.println("equals\t\t:" + tg.equals(wt) );
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");

    }
    //method untuk menambahkan data ke database
        public void insertData() throws Exception{
    
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO penjualan (no, nama_pelanggan, alamat_pelanggan, kode_produk, nama_produk, ukuran_produk, warna_produk, harga_produk, jumlah,total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, no_faktur);
                statement.setString(2, nama);
                statement.setString(3, alamat);
                statement.setString(4, kode_barang);
                statement.setString(5, namaBarang);
                statement.setString(6, ukuran);
                statement.setString(7, warna);
                statement.setInt(8, hargaBarang);
                statement.setInt(9, jumlahBarang);
                statement.setInt(10, this.totalBayar);
    
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("\nData inserted successfully into 'penjualan' table!");
                }
            } catch (SQLException e) {
                System.out.println("\nError inserting data to 'penjualan' table: " + e.getMessage());
            }
        }
        //method untuk menginputkan data tertentu yangg ingin diupdate
        public void updateDataFromUserInput() {
            Scanner scannerStr = new Scanner(System.in);
            Scanner scannerInt = new Scanner(System.in);

            System.out.println("Masukkan NO data yang ingin diupdate:");
            String idToUpdate = scannerStr.nextLine();
    
            System.out.println("Masukkan nama baru pelanggan:");
            String newNamaPelanggan = scannerStr.nextLine();
    
            System.out.println("Masukkan alamat baru pelanggan:");
            String newAlamat = scannerStr.nextLine();
    
            System.out.println("Masukkan kode barang baru:");
            String newKodeBarang = scannerStr.nextLine();

            System.out.println("Masukkan nama barang baru:");
            String newNamaBarang = scannerStr.nextLine();

            System.out.println("Masukkan ukuran barang baru:");
            String newUkuran = scannerStr.nextLine();

            System.out.println("Masukkan warna barang baru:");
            String newWarna = scannerStr.nextLine();

            System.out.println("Masukkan harga barang baru:");
            Integer newHargaBarang = scannerInt.nextInt();

            System.out.println("Masukkan jumlah beli baru:");
            Integer newJumlahBeli = scannerInt.nextInt();

            Integer totalBelanja = newHargaBarang*newJumlahBeli;

            System.out.println("Total Bayar:" + totalBelanja);
    
            updateData( idToUpdate,newNamaPelanggan, newAlamat, newKodeBarang, newNamaBarang, newUkuran,newWarna,newHargaBarang, newJumlahBeli,totalBelanja);
        }
        //method untuk mengubah data tertentu di database
        public void updateData(String idToUpdate, String newNamaPelanggan, String newAlamat, String newKodeBarang, String newNamaBarang, String newUkuran, String newWarna, Integer newHargaBarang, Integer newJumlahBeli,Integer totalBelanja ) {
            //Tanda tanya (?) adalah tempat menanmpung nilai-nilai yang diinputkan
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "UPDATE penjualan SET nama_pelanggan = ?, alamat_pelanggan = ?, kode_produk = ?, ukuran_produk = ?, warna_produk = ?, harga_produk = ?, jumlah = ?, total = ?, nama_produk = ? WHERE no = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, newNamaPelanggan);
                statement.setString(2, newAlamat);
                statement.setString(3, newKodeBarang);
                statement.setString(4, newUkuran);
                statement.setString(5, newWarna);
                statement.setInt(6, newHargaBarang);
                statement.setInt(7, newJumlahBeli);
                statement.setInt(8, totalBelanja);
                statement.setString(9, newNamaBarang);
                statement.setString(10, idToUpdate);
    
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Data updated successfully!");
                } else {
                    System.out.println("No data found or update failed.");
                }
            } catch (SQLException e) {
                System.out.println("Error updating data: " + e.getMessage());
            }
        }
        //method untuk mengingputkan data tertentu yang ingin dihapus
        public void deleteDataFromUserInput() throws Exception {
            Scanner scannerDel = new Scanner(System.in);
    
            System.out.println("Masukkan nomor struk yang ingin dihapus:");
            String idToDelete = scannerDel.nextLine();
            scannerDel.nextLine(); // Consume the newline character
    
            deleteData(idToDelete);
        }
        //method untuk menghapus data tertentu di database
        public void deleteData(String idToDelete) throws Exception{
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "DELETE FROM penjualan WHERE no = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, idToDelete);
    
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Data deleted successfully!");
                } else {
                    System.out.println("No data found or delete failed.");
                }
            } catch (SQLException e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
        }
        //method untuk menampilkan data dari database
        public void tampilkanData() {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT * FROM penjualan";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                System.out.println("+---------------------------------------------------+");
                System.out.println("|           DATA PELANGGAN SHOESIA STORE            |");
                System.out.println("+---------------------------------------------------+");
    
                while (resultSet.next()) {
                    String no_faktur = resultSet.getString("no");
                    String nama = resultSet.getString("nama_pelanggan");
                    String alamat = resultSet.getString("alamat_pelanggan");
                    String kode_barang = resultSet.getString("kode_produk");
                    String namaBarang = resultSet.getString("nama_produk");
                    String ukuran = resultSet.getString("ukuran_produk");
                    String warna = resultSet.getString("warna_produk");
                    int hargaBarang = resultSet.getInt("harga_produk");
                    int jumlahBarang = resultSet.getInt("jumlah");
                    int totalBayar = resultSet.getInt("total");
    
                    System.out.println(String.format("%s. %s -- %s -- %s -- %s -- %s -- %s -- %s -- %s -- (%s)", no_faktur, nama, alamat, kode_barang, namaBarang, ukuran, warna,hargaBarang, jumlahBarang, totalBayar));
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving data: " + e.getMessage());
            }
        }
       
}



