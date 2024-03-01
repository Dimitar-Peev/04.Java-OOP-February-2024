package _04_InterfacesAndAbstraction._01_Lab._01_CarShop;

public interface Car{
    Integer TIRES = 4;

    String getModel ();

    String getColor ();

    Integer getHorsePower ();

    String countryProduced();
}
