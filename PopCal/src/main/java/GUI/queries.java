package GUI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import Database.*;

public class queries
{
    String region = "";
    String year = "";
    List<String> Offspringresults = new ArrayList<String>();
    List<String> Populationresults = new ArrayList<String>();
    List<String> PopulationRegionresults = new ArrayList<String>();
    DBConnect dbConnect = new DBConnect();

    public List<String> getPopulationRegion(){
        String query = "Select population from regionpopulation where region ='" + region + "' AND year = '" + year + "' ;";
        PopulationRegionresults = dbConnect.connectDB(query);
        return  PopulationRegionresults;
    }

    public List<String> getEstPop(){
        String query1 = "Select percentage/100 as percentage from agestructure where age = '0-7' and year >= 2016;";
        Offspringresults = dbConnect.connectDB(query1);
        String query2 = "select population from totalpopulation where year>=2015";
        Populationresults =dbConnect.connectDB(query2);
        return  Offspringresults;
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
