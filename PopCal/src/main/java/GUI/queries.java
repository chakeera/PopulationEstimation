package GUI;

import java.util.ArrayList;
import java.util.List;
import Database.*;

public class queries
{
    String region = "";
    String year = "";
    List<String> Offspringresults = new ArrayList<String>();
    List<String> PopulationRegionresults = new ArrayList<String>();
    DBConnect dbConnect = new DBConnect();

    public List<String> getPopulationregion(){
        String query = "Select population, year from regionpopulation where region =" + region + " && year >= 2013 and <= 2018 ;";
        PopulationRegionresults = dbConnect.connectDB(query);
        return  PopulationRegionresults;
    }
//    public void setPopulationRegion(List<String>  PopulationRegion){
//        this.PopulationRegionresults=  PopulationRegion;
//    }

    public List<String> getOffspring(){
        String query = "Select population, year from regionpopulation where region =" + region + " && year >= 2013 and <= 2018 ;";
        Offspringresults = dbConnect.connectDB(query);
        return  Offspringresults;
    }

//    public void setOffspring(List<String> Offspring){
//        this.Offspringresults=  Offspring;
//    }


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
