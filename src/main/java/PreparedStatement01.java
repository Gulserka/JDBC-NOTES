import java.sql.*;

//import java.sql.*;   sanirim bazen hata veriyor o zaman bunu yap
public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
Parametrelendirilmis(Parameterised) SQL query(sorgu)'leri ile calisir. Bir sorguyu 0 veya daha fazla parametre ile kullanabiliriz
prepared bir interface
         */


        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproedd", "postgres", "Senbeno98");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.


        //1. Adım: PreparedStatement query'sini oluştur.
        //String sql1 = "update companies set number_of_employees=9999 where company='IBM';" ---> BOYLEYDI.
        String sql1 = "update companies set number_of_employees = ? where company = ?;"; //  --->BOYLE OLDU, DINAMIK OLSUN DIYE YAPIYORUZ


        //2. Adım: PreparedStatement objesini oluştur.
        PreparedStatement pst1 = con.prepareStatement(sql1);


        //3. Adım: setInt(), setString(), ... methodlarını kullanarak soru işaretleri yerlerine değer gir.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");


        //4. Adım: Query'yi çalıştır.
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = "SELECT * FROM companies";

        ResultSet rs1 =  st.executeQuery(sql2);

        while(rs1.next()){
            System.out.println(rs1.getInt(1)+"--"+rs1.getString(2)+"--"+rs1.getInt(3));
        }



        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet rs2 =  st.executeQuery(sql2);

        while(rs2.next()){
            System.out.println(rs2.getInt(1)+"--"+rs2.getString(2)+"--"+rs2.getInt(3));
        }

        con.close();
        st.close();
        rs1.close();
        rs2.close();
    }

    }

