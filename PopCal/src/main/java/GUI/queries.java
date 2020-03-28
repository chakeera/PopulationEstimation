package GUI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import Database.*;
import Math.*;

public class queries
{
    String region = "";
    String year = "";
    DBConnect dbConnect = new DBConnect();
    Eigen eigen = new Eigen();

    public List<String> getPopulationRegion(){
        String query = "Select population from regionpopulation where region ='" + region + "' AND year = '" + year + "' ;";
        List<String> PopulationRegionresults = dbConnect.connectDB(query);
        return  PopulationRegionresults;
    }

    public double[] getEstPop(){
        String query1 = "Select percentage/100 as percentage from agestructure where age = '0-7' and year >= 2016;";
        List<String> Offspringresults = dbConnect.connectDB(query1);
        String query2 = "select population from totalpopulation where year>=2015 and year<=2019";
        List<String> Populationresults =dbConnect.connectDB(query2);
        double[] EigenResults = eigen.answer(Offspringresults, Populationresults);
        return  EigenResults;
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
