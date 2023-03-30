import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {

    /*
        Given--on kosullar
          User connects to the database
        When-yapacagimiz islemler
          User sends the query to get the region ids from "countries" table
        Then-assertion kismi
          Assert that the number of region ids greater than 1 is 17.(idsi 1den buyuk olanlari(ulkeleri) assert et)
        And
          User closes the connection
       */

    @Test
    public void countryTest() throws SQLException {

        //Test methodu olusturdugumuz zaman main methid olurturmuyoruz
        //Test methodlarimiz void olmak zorunda.


        //User connects to the database
        JdbcUtils.connectToDataBase("localhost", "postgres", "techproedd", "Senbeno98");//bunu connectiona koymaya gerek yok cunku connectioni kullanmicaz ekstra olarak
        Statement statement = JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql1 = "select region_id from countries";
        ResultSet resultSet1 = statement.executeQuery(sql1);

        List<Integer> ids = new ArrayList<>();//list olusturduk gelenleri bunun icine koymak icin

        while (resultSet1.next()) {

            ids.add(resultSet1.getInt(1));
        }

        System.out.println("ids = " + ids);
        List<Integer> idsGreaterThan1 = new ArrayList<>();
        for (int w : ids){
            if (w>1){
                idsGreaterThan1.add(w);
            }
        }
        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);

    }


}
