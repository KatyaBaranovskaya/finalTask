package by.baranovskaya.entity;

public class Service extends Entity{
    private int idService;
    private String typeService;
    private double price;

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (idService != service.idService) return false;
        if (Double.compare(service.price, price) != 0) return false;
        return typeService != null ? typeService.equals(service.typeService) : service.typeService == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idService;
        result = 31 * result + (typeService != null ? typeService.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", typeService='" + typeService + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
