import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproedd", "postgres", "Senbeno98");
        Statement st = con.createStatement();


        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.


        String sql1 = "SELECT country_name FROM countries WHERE region_id=1"; //burda select yani DQL kullandigimiz icin
        boolean r1 = st.execute(sql1);                                        //ve resultset old icin true dondu
        System.out.println("r1 = " + r1);

        //execute() yine kullanabiliriz ama sadece boolean return eder. Datayi aldik mi almadik mi ona bakiyo.
        // Aldigimiz datayi goremeyiz.

        //Recordları görmek için ExecuteQuery() methodunu kullanmalıyız.
        ResultSet resultSet1 = st.executeQuery(sql1);

        //ResultSet = Databaseden bize datalarin geldigi konteynir.
        while (resultSet1.next()){


            System.out.println(resultSet1.getString(1));

        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        String sql2 ="SELECT country_name, country_id FROM countries WHERE region_id>2";
        ResultSet resultSet2 = st.executeQuery(sql2);

        System.out.println("-----------------------");

        while (resultSet2.next()){

            System.out.println(resultSet2.getString("country_name")+"--"+resultSet2.getString("country_id"));

        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        System.out.println("--------------------------");
        String sql3 ="SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        //SELECT * FROM companies ORDER BY number_of_employees LIMIT 1;

        ResultSet resultSet3 = st.executeQuery(sql3);

        while (resultSet3.next()){

            System.out.println(resultSet3.getInt(1)+"--"+resultSet3.getString(2)+"--"+resultSet3.getInt(3));
                                                //  1.sutun                              2.sutun                              3.sutun
        }

        con.close();
        st.close();
    }
}


