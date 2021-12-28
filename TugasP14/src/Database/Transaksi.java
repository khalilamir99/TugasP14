package Database;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaksi extends Barang {
	
    int subtotal;
    int diskon;
    int totalharga;
    int potongan;
    String kasir = "KHALIL";
    char[]kupon= {'P','R','O','M','O'};
    String undianhadiah;
    public int noFaktur;
    public String namaBarang;
    public int hargaBarang;
    public String subTotal;
    public void SubTotal() {
        subtotal = hargabarang* jumlah;
        System.out.println("Subtotal : " +subtotal);
    }
    public void Discount() {
        {
        if(subtotal >25000 && subtotal <=35000) {
            potongan = subtotal*5/100;
            System.out.println("Diskon yang didapatkan "+potongan);
        }
        else if (subtotal >35000 && subtotal <=50000) {
            potongan = subtotal*10/100;
            System.out.println("Diskon yang didapatkan "+potongan);
        }
        else if (subtotal >50000 && subtotal <=65000) {
            potongan = subtotal*15/100;
            System.out.println("Diskon yang didapatkan "+potongan);
        }
        else if (subtotal >65000) {
            potongan = subtotal*20/100;
            System.out.println("Diskon yang didapatkan "+potongan);
        }
        else
            System.out.println("\nTidak dapat diskon ");
    }
        diskon = potongan;
    }
  
    public void TotalHarga() {
        totalharga = subtotal-diskon;
        System.out.println("Jumlah Belanja : " +totalharga);
    }
    public void waktu(){
        Date harisekarang = new Date();
        SimpleDateFormat tf = new SimpleDateFormat("E yyyy.MM.dd");
        System.out.println("Tanggal Transaksi : " + tf.format(harisekarang));
    }
    public void tampil()
    {
        System.out.println("---NOTA PEMBELIAN---");
        waktu();
        kasir = kasir.toLowerCase();
        undianhadiah = undianhadiah.copyValueOf(kupon,0,5);
        System.out.println("Nama Kasir   : " + kasir);
        System.out.println("Nama Barang  : " + namabarang);
        System.out.println("No.Faktur    : " + nofaktur);
        System.out.println("Harga Barang : " + hargabarang);
        System.out.println("Jumlah Barang: " + jumlah);
        System.out.println("Sub Total    : " + subtotal);
        System.out.println("Anda Mendapatkan Kupon Undian : " + undianhadiah);
    }    
}