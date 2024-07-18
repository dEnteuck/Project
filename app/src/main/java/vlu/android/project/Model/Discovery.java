package vlu.android.project.Model;

public class Discovery {
    int imgDiscovery;
    String nameDiscovery, detailDiscovery, dayDiscovery;

    public Discovery(int imgDiscovery, String nameDiscovery, String detailDiscovery, String dayDiscovery) {
        this.imgDiscovery = imgDiscovery;
        this.nameDiscovery = nameDiscovery;
        this.detailDiscovery = detailDiscovery;
        this.dayDiscovery = dayDiscovery;
    }

    public Discovery() {
    }

    public int getImgDiscovery() {
        return imgDiscovery;
    }

    public void setImgDiscovery(int imgDiscovery) {
        this.imgDiscovery = imgDiscovery;
    }

    public String getNameDiscovery() {
        return nameDiscovery;
    }

    public void setNameDiscovery(String nameDiscovery) {
        this.nameDiscovery = nameDiscovery;
    }

    public String getDetailDiscovery() {
        return detailDiscovery;
    }

    public void setDetailDiscovery(String detailDiscovery) {
        this.detailDiscovery = detailDiscovery;
    }

    public String getDayDiscovery() {
        return dayDiscovery;
    }

    public void setDayDiscovery(String dayDiscovery) {
        this.dayDiscovery = dayDiscovery;
    }
}