package by.baranovskaya.entity;

public class Service extends Entity {
    private int idService;
    private String typeService;
    private String description;
    private String image;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (idService != service.idService) return false;
        if (typeService != null ? !typeService.equals(service.typeService) : service.typeService != null) return false;
        if (description != null ? !description.equals(service.description) : service.description != null) return false;
        return image != null ? image.equals(service.image) : service.image == null;
    }

    @Override
    public int hashCode() {
        int result = idService;
        result = 31 * result + (typeService != null ? typeService.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", typeService='" + typeService + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
