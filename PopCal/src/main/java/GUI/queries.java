package GUI;

public class queries
{
    String region = "";
    String year = "";

    public String getOffspring(){
        return "Select population, year from regionpopulation where region =" + region + " && year >= 2013 and <= 2018 ;";
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
