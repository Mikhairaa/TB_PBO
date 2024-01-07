import java.util.Scanner;

public class Kasir implements Login {
    String captcha = "Xcvbn4";
    String password = "Walach99";
    String username ="Khaira";
    String inputCaptcha;
    String inputPassword;

     //method login
     public void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("SELAMAT DATANG DI SHOESIA STORE");
        System.out.println("==============================");

        //username
        System.out.println("Username\t: "+username);

        //Input Password
        System.out.print("Password\t: ");
        inputPassword = scanner.next();
 
        while(!inputPassword.equals(password)){
             System.out.println("Password Salah!");
             System.out.print("Masukkan password: ");
             inputPassword = scanner.next();
         }
        //input kode captcha
        System.out.println("Kode captcha\t: " + captcha);
        System.out.print("Entry Captcha\t: ");
        inputCaptcha = scanner.next();

        while(!inputCaptcha.equals(captcha)){
            System.out.println("Anda Bukan Manusia\n");
            System.out.print("Masukkan captcha: ");
            inputCaptcha = scanner.next();
        }
        System.out.println("Kode Benar, Anda Manusia\n");
        System.out.println("======================================================");
        System.out.println("--------------------Login Berhasil--------------------");
        System.out.println("======================================================");
     }
}

