import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Polymorphism
        Pelanggan pelanggan1 = new Pelanggan(); 
        Kasir kasir1 = new Kasir();
        kasir1.login();

        Scanner scanner = new Scanner(System.in);
        String opsi;
        boolean isLanjutkan = true;

        while (isLanjutkan){
            
            System.out.println("========== MENU ==========");
            System.out.println("1. Tampilkan Data");
            System.out.println("2. Tambah Data");
            System.out.println("3. Update data");
            System.out.println("4. Delete data");
            System.out.println("5. Keluar");
        
            System.out.print("Pilihan:");
            opsi = scanner.next();
            
            switch (opsi) {
                case "1":
                    //menampilkan data pelanggan dari database
                    pelanggan1.tampilkanData();
                    break;

                case "2":
                    System.out.println("======================================================");
                    System.out.println("----------------------Tambah Data--------------------");
                    System.out.println("======================================================");
                    //menambahkan data ke database
                    pelanggan1.inputDataPelanggan();
                    pelanggan1.struk();
                    pelanggan1.insertData();
                    pelanggan1.tampilkanData();
                    break;
                
                case "3":
                    System.out.println("======================================================");
                    System.out.println("----------------------Update Data---------------------");
                    System.out.println("======================================================");
                    // mengubah data dengan no struk tertentu
                    pelanggan1.updateDataFromUserInput();
                    pelanggan1.updateData();
                    pelanggan1.tampilkanData();
                    break;
                
                case "4":
                    System.out.println("======================================================");
                    System.out.println("----------------------Delete Data---------------------");
                    System.out.println("======================================================");
                    //menghapus data dengan no struk tertentu
                    pelanggan1.deleteDataFromUserInput();
                    pelanggan1.deleteData();
                    pelanggan1.tampilkanData();
                    break;

                case"5":
                    System.out.println("======================================================");
                    System.out.println("------------------------LOGOUT------------------------");
                    System.out.println("======================================================");
                    //keluar dari program
                    System.out.println ("Anda telah keluar, silahkan login kembali");
                    System.exit(0);

                default:
                    System.out.println("Pilihan tidak valid");
            }
        }

        //exception handling   
        try {
        //mengisi data pelanggan
        pelanggan1.inputDataPelanggan();
        //mencetak struk
        pelanggan1.struk();
    }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}


