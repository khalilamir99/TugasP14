package Database;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class Program {
    static Connection con;
    public static void main(String[] args) throws Exception {
        Scanner inputan = new Scanner (System.in);
        String pilihanPengguna;
        boolean isLanjutkan = true;

        String url = "jdbc:mysql://localhost:3306/db_tugas14";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Class Driver ditemukan");

            while (isLanjutkan){
                System.out.println("|========================|");
                System.out.println("|---DATABASE TRANSAKSI---|");
                System.out.println("|========================|");
                System.out.println("|1. Tambah Data Transaksi|");
                System.out.println("|2. Hapus Data Transaksi |");
                System.out.println("|3. Ubah Data Transaksi  |");
                System.out.println("|4. Cari Data Transaksi  |");
                System.out.println("|5. Lihat Data Transaksi |");
                
                System.out.print("\nMasukkan Pilihan Anda = ");
                pilihanPengguna = inputan.next();

                switch (pilihanPengguna){
                    case "1":
                            tambahdata();
                            break;
                    case "2":
                            hapusdata();
                            break;
                    case "3":
                            ubahdata();
                            break;
                    case "4":
                            caridata();
                            break;
                    case "5":
                            lihatdata();
                            break;
                    default:
                            System.err.println("\nInputan Anda Tidak ditemukan!");
                            break;
                }

                System.out.print("\nApakah Anda Ingin Lanjut[y/n]?");
                pilihanPengguna = inputan.next();
                isLanjutkan = pilihanPengguna.equalsIgnoreCase("y");
            }
            System.out.println("Terima Kasih!");
            }

        catch(ClassNotFoundException ex) {
            System.err.println("Driver Error");
            System.exit(0);
            }
                
        catch(SQLException e){
            System.out.println("Tidak berhasil koneksi");
            }
        
        //inputan.close();
    }

//====================================================================================================================================

private static void tambahdata() throws SQLException{
    
    String text1 = "\n===TAMBAH DATA PEMBELIAN===";
    System.out.println(text1.toUpperCase());

    Transaksi transaksi = new Transaksi();

    try{
        transaksi.NoFaktur();
        transaksi.HargaBarang();
        transaksi.Jumlah();
        transaksi.HargaBarang();
        transaksi.SubTotal();

        String sql = "INSERT INTO transaksi (nofaktur, namabarang, jumlahbarang, hargabarang, totalbelanja) VALUES ('"+transaksi.noFaktur+"','"+transaksi.namaBarang+"','"+transaksi.jumlah+"','"+transaksi.hargaBarang+"', '"+transaksi.subTotal+"')";

        Statement statement = con.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil Menginputkan Data Pembelian");
        
    }

    catch (SQLException e){
        System.err.println("\nTerjadi kesalahan input data");
    }
    catch (InputMismatchException e) {
        System.err.println("\nInputlah dengan angka saja");
       }
       
}

//====================================================================================================================================

private static void hapusdata() {
    String text2 = "\n===HAPUS DATA PEMBELIAN===";
    System.out.println(text2.toUpperCase());
    
    Scanner inputan = new Scanner (System.in);
    Transaksi transaksi = new Transaksi();
    
    try{
        lihatdata();
        System.out.print("Ketik nomor faktur yang akan dihapus : ");
        transaksi.noFaktur = Integer.parseInt(inputan.nextLine());
        
        String sql = "DELETE FROM transaksi WHERE nofaktur = "+ transaksi.noFaktur;
        Statement statement = con.createStatement();
        //ResultSet result = statement.executeQuery(sql);
        
        if(statement.executeUpdate(sql) > 0){
            System.out.println("Berhasil menghapus data harga barang (Harga Barang "+transaksi.noFaktur+")");
        }
    }
    catch(SQLException e){
         System.out.println("Terjadi kesalahan dalam menghapus data barang");
         System.err.println(e.getMessage());
         }
    //inputan.close(); 
}

//====================================================================================================================================

private static void ubahdata() throws SQLException{
    String text3 = "\n===UBAH DATA PEMBELIAN===";
    System.out.println(text3.toUpperCase());
    
    Scanner inputan = new Scanner (System.in);
    Transaksi transaksi = new Transaksi();

    try{
        lihatdata();
        System.out.print("Masukkan No Faktur Pembelian yang Akan diubah : ");
        transaksi.noFaktur = Integer.parseInt(inputan.nextLine());

        String sql = "SELECT * FROM transaksi WHERE nofaktur = " +transaksi.noFaktur;

        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if(result.next()){
            System.out.print("No Faktur ["+result.getString("nofaktur")+"]\t: ");
            transaksi.noFaktur = inputan.nextInt();

            System.out.print("Nama Barang ["+result.getString("namabarang")+"]\t: ");
            transaksi.namaBarang = inputan.next();

            System.out.print("Jumlah Barang ["+result.getInt("jumlahbarang")+"]\t: ");
            transaksi.jumlah = inputan.nextInt();

            System.out.print("Harga Barang ["+result.getInt("hargabarang")+"]\t: ");
            transaksi.hargaBarang = inputan.nextInt();

            System.out.println("\n");

            sql = "UPDATE transaksi SET namabarang='"+transaksi.namaBarang+"', jumlahbarang='"+transaksi.jumlah+"' , hargabarang='"+transaksi.hargaBarang+"' WHERE nofaktur='"+transaksi.noFaktur+"' ";
            //System.out.println(sql);

            if(statement.executeUpdate(sql) > 0){
                System.out.println("Data Berhasil diperbarui!");
            }
        }
        statement.close();
    }

    catch (SQLException e){
        System.err.println("Terjadi kesalahan Ubah data");
        System.err.println(e.getMessage());
        
    }
    
    //inputan.close(); 
}

//====================================================================================================================================

private static void caridata () throws SQLException {
    String text4 = "\n===Cari Data PEMBELIAN===";
    System.out.println(text4.toUpperCase());
    
    Scanner inputan = new Scanner (System.in);
            
    System.out.print("Masukkan Nomor Faktur : ");
    
    String keyword = inputan.nextLine();
    Statement statement = con.createStatement();
    String sql = "SELECT * FROM transaksi WHERE nofaktur LIKE '%"+keyword+"%'";
    ResultSet result = statement.executeQuery(sql); 
            
    while(result.next()){
        System.out.println("\nNomor Faktur\t");
        System.out.println(result.getString("nofaktur"));
        System.out.println("\nNama Barang\t");
        System.out.println(result.getString("namabarang"));
        System.out.println("\nJumlah Barang\t");
        System.out.println(result.getInt("jumlahbarang"));
        System.out.println("\nHarga Barang\t");
        System.out.println(result.getInt("hargabarang"));
        System.out.println("\n--------------------------------------");
    }
    //inputan.close();
}

//====================================================================================================================================

private static void lihatdata() throws SQLException{
    String text5 = "\n===DATA SELURUH PEMBELIAN===";
    System.out.println(text5.toUpperCase());

    String sql = "SELECT * FROM transaksi";
    Statement statement = con.createStatement();
    ResultSet result = statement.executeQuery(sql);

    while (result.next()){
        System.out.println("\nNomor Faktur\t");
        System.out.println(result.getString("nofaktur"));
        System.out.println("\nNama Barang\t");
        System.out.println(result.getString("namabarang"));
        System.out.println("\nJumlah Barang\t");
        System.out.println(result.getInt("jumlahbarang"));
        System.out.println("\nHarga Barang\t");
        System.out.println(result.getInt("hargabarang"));
        System.out.println("\n--------------------------------------");
    }
}

}