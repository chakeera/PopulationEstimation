package GUI;

import java.util.ArrayList;
import java.util.List;
import Database.*;

public class queries
{
    String region = "";
    String year = "";
    List<String> results = new ArrayList<String>();
    DBConnect dbConnect = new DBConnect();

    public List<String> getOffspring(){
//        String query = "Select population, year from regionpopulation where region =" + region + " && year >= 2013 and <= 2018 ;";
        String query = "Select * from fatherchild";
        results = dbConnect.connectDB(query);
        return results;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    //Select (p.population / 100)*age structure.percentage from
    //(Select population, year from regionpopulation where region = bangkok && year >= 2013 and <= 2018 ;) as p ,age structure where p.year = age structure.year;
}
