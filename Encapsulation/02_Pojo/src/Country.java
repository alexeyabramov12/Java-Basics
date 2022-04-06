public class Country {

    private String nameOfTheCountry;
    private int population;
    private double areaInSquareKilometers;
    private String nameOfTheCapital;
    private String accessToTheSea;

    public Country(String nameOfTheCountry) {

        this.nameOfTheCountry = nameOfTheCountry;
    }

    public String getNameOfTheCountry() {
        return nameOfTheCountry;
    }

    public void setNameOfTheCountry(String nameOfTheCountry) {
        this.nameOfTheCountry = nameOfTheCountry;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getAreaInSquareKilometers() {
        return areaInSquareKilometers;
    }

    public void setAreaInSquareKilometers(double areaInSquareKilometers) {
        this.areaInSquareKilometers = areaInSquareKilometers;
    }

    public String getNameOfTheCapital() {
        return nameOfTheCapital;
    }

    public void setNameOfTheCapital(String nameOfTheCapital) {
        this.nameOfTheCapital = nameOfTheCapital;
    }

    public String getAccessToTheSea() {
        return accessToTheSea;
    }

    public void setAccessToTheSea(String accessToTheSea) {
        this.accessToTheSea = accessToTheSea;
    }
}

