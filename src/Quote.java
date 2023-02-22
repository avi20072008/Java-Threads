public class Quote {
    private String siteName;
    private int price;

    public Quote(String siteName, int price) {
        this.siteName = siteName;
        this.price = price;
    }

    public String getSiteName() {
        return siteName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "siteName='" + siteName + '\'' +
                ", price=" + price +
                '}';
    }
}
