package pages;

public class BritishAirwaysPages {

    private static ProductsPage productsPage;

    public static void openPage(Pages page) {
        switch (page) {
            case PRODUCTS:
                productsPage = new ProductsPage();
                break;
        }
    }

    public static ProductsPage productsPage() {
        if (productsPage == null) productsPage = new ProductsPage();
        return productsPage;
    }
}
