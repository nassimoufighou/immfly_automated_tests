package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.selector.ByAttribute;
import com.codeborne.selenide.selector.ByText;
import domain.Product;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class ProductsPage {

    private final String URL = "https://highlifeshop.com/speedbird-cafe";

    public ProductsPage() {
        open(URL);
        try {
            Selenide.$(By.className("amgdprcookie-modal-template")).should(Condition.appear);
            Selenide.$(By.xpath(".//button[@class=\"amgdprcookie-button -decline\"]")).click();
            Selenide.$(By.className("amgdprcookie-modal-template")).should(Condition.disappear);
        }
        catch (ElementNotFound enf) {}
    }

    public void sortProductsBy(String option) {
        if (option.equals("Product Name")) option = "name";
        if (option.equals("New Arrivals")) option = "new";
        Selenide.$(By.id("sorter")).selectOptionByValue(option.toLowerCase(Locale.ROOT));
        waitUntilPageIsReady();
    }

    public void changeSortingOrder() {
        Selenide.$(By.xpath(".//a[@data-value=\"asc\"]")).click();
        waitUntilPageIsReady();
    }

    public List<Product> getProductsFromPage() {
        ElementsCollection productsFromPage = Selenide.elements(By.xpath(".//li[@class=\"item product product-item\"]"));
        List<Product> productList = new ArrayList<>();
        for (SelenideElement element : productsFromPage) {
            String productName = getProductName(element);
            String productPriceCurrency = getProductePriceCurrency(element);
            double productPrice = getProductPrice(element);
            Product product = Product.builder()
                    .name(productName)
                    .currency(productPriceCurrency)
                    .price(productPrice)
                    .build();
            productList.add(product);
        }
        return productList;
    }


    private void waitUntilPageIsReady() {
        Selenide.$(By.xpath(".//ol[@class=\"products list items product-items\"]")).should(Condition.appear);
    }

    private String getProductName(SelenideElement element) {
        String productName = element.$(By.className("product-item-link")).getText();
        return productName;
    }

    private String getProductePriceCurrency(SelenideElement element) {
        String price = element.$(By.className("price")).getText();
        String priceWithNoCurrency = price.substring(0, 1);
        return priceWithNoCurrency;
    }

    private double getProductPrice(SelenideElement element) {
        String price = element.$(By.className("price")).getText().substring(1);
        return Double.parseDouble(price);
    }
}
