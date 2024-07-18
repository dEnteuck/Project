package vlu.android.project.Model;

public class Discovery {
    private int imgDiscovery; // Keep as int
    private String nameDiscovery;
    private String dayDiscovery;
    private String detailDiscovery;

    // Getters and Setters
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

    public String getDayDiscovery() {
        return dayDiscovery;
    }

    public void setDayDiscovery(String dayDiscovery) {
        this.dayDiscovery = dayDiscovery;
    }

    public String getDetailDiscovery() {
        return detailDiscovery;
    }

    public void setDetailDiscovery(String detailDiscovery) {
        this.detailDiscovery = detailDiscovery;
    }

    public Discovery(int imgDiscovery, String nameDiscovery, String dayDiscovery, String detailDiscovery) {
        this.imgDiscovery = imgDiscovery;
        this.nameDiscovery = nameDiscovery;
        this.dayDiscovery = dayDiscovery;
        this.detailDiscovery = detailDiscovery;
    }

    public Discovery() {
    }
}
