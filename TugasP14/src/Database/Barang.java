package Database;

import java.util.Scanner;

public class Barang implements Penjualan {
		  Scanner item = new Scanner(System.in);
			int nofaktur;
			String namabarang;
			int hargabarang;
			int jumlah;
			public void NoFaktur() {
				System.out.print("Input No Faktur : ");
				nofaktur = item.nextInt();

            }
			public void NamaBarang() {
				System.out.print("Input Nama Barang : ");
				namabarang = item.next();
                namabarang = namabarang.toUpperCase();
			}

			public void HargaBarang() {
				System.out.print("Input Harga Barang : ");
				hargabarang = item.nextInt();
				
			}

			public void Jumlah() {
				System.out.print("Input Jumlah Barang : ");
				jumlah = item.nextInt();
			}

            @Override
            public void SubTotal() {
               
            }

            @Override
            public void Discount() {
              
                
            }

            @Override
            public void TotalHarga() {
    
                
            }

        }