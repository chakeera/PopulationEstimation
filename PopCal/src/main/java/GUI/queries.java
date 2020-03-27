package GUI;

public class queries {
    public String getOffspring(String region){
        return "Select population, year from regionpopulation where region = bangkok && year >= 2013 and <= 2018 ;";
    }
    //Select (p.population / 100)*age structure.percentage from
    //(Select population, year from regionpopulation where region = bangkok && year >= 2013 and <= 2018 ;) as p ,age structure where p.year = age structure.year;
}
