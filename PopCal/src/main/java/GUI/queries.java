package GUI;

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
        String query1 = "Select ((agestructure.percentage/100)* totalpopulation.population) as offspring from " +
                "agestructure inner join totalpopulation on agestructure.year = totalpopulation.year where agestructure.age = '0-7' and agestructure.year >= 2016;";
        List<String> Offspringresults = dbConnect.connectDB(query1);
        String query2 = "select population from totalpopulation where year>=2015 and year<=2020";
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

}
