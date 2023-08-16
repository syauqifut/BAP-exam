package Logistik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Logistik {
    double maxBerat;
    double maxJarak;

    public Logistik(double maxBerat, double maxJarak){
        this.maxBerat = maxBerat;
        this.maxJarak = maxJarak;
    }

    public boolean validate(double berat, double jarak){
        return berat <= maxBerat && jarak <= maxJarak;
    }

    public abstract double hitungOngkir(double berat, double jarak);
}

class Regular extends Logistik {
    public Regular() {
        super(100, Double.POSITIVE_INFINITY);
    }

    @Override
    public double hitungOngkir(double berat, double jarak){
        return berat * 5000;
    }
}

class Express extends Logistik {
    public Express() {
        super(70, 100);
    }

    @Override
    public double hitungOngkir(double berat, double jarak){
        return berat * 5000 + jarak * 1000;
    }
}

class Instant extends Logistik {
    public Instant() {
        super(5, 20);
    }

    @Override
    public double hitungOngkir(double berat, double jarak){
        return jarak * 5000;
    }
}

class Pengiriman {
    private int number;
    private Logistik logistik;
    private double berat;
    private double jarak;

    public Pengiriman(
        int number,
        Logistik logistik,
        double berat,
        double jarak
    ){
        this.number = number;
        this.logistik = logistik;
        this.berat = berat;
        this.jarak = jarak;
    }

    public double hitungOngkir(){
        return logistik.hitungOngkir(this.berat, this.jarak);
    }

    public String returnString(){
//        return ""+this.berat;
        return this.number+". "+logistik.getClass().getSimpleName()+" - "+this.berat+" kg - "+this.jarak+" km - Rp "+String.format("%,.2f", hitungOngkir());
    }
}


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pengiriman> pengirimanList = new ArrayList<>();

        int number = 1;

        System.out.println("Selamat datang di Syauqi Delivery");
        while(true){
            System.out.print("Masukkan berat barang (dalam kg): ");
            double berat = scanner.nextDouble();
            System.out.print("Masukkan jarak pengiriman (dalam km): ");
            double jarak = scanner.nextDouble();
            System.out.print("Masukkan jenis pengiriman (regular/express/instant): ");
            String jenis = scanner.next().toLowerCase();

            Logistik logistik;
            switch (jenis){
                case "regular":
                    logistik = new Regular();
                break;
                case "express":
                    logistik = new Express();
                break;
                case "instant":
                    logistik = new Instant();
                break;
                default:
                    System.out.println("Jenis pengiriman tidak valid. Silakan input ulang!");
                    continue;
            }

            if(logistik.validate(berat, jarak)){
                Pengiriman pengiriman = new Pengiriman(number, logistik, berat, jarak);
                pengirimanList.add(pengiriman);
                number++;
                System.out.println("Data Pengiriman berhasil ditambahkan");
            } else {
                System.out.println("Data pengiriman tidak sesuai batas");
                continue;
            }

            System.out.print("Apakah Anda ingin menambahkan data pengiriman lagi (y/n)? ");
            String lanjut = scanner.next().toLowerCase();
            String ya = "y";

            if(!lanjut.equals(ya)){
                break;
            }
//            if (inputAgain == 'ya'){
//                continue;
//            } else {
//                break;
//            }
        }

        System.out.println("Daftar data pengiriman:");
        for (Pengiriman pengiriman : pengirimanList){
            System.out.println(pengiriman.returnString());
        }
    }
}