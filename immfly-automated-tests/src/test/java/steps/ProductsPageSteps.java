package steps;

import domain.Product;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.BritishAirwaysPages;
import pages.Pages;

import java.util.ArrayList;
import java.util.List;

public class ProductsPageSteps extends BasePageSteps{

    private Product productA, productB, productC;

    private List<Product> productsSortedByNameAscendant = new ArrayList<>();
    private List<Product> productsSortedByNameDescendant = new ArrayList<>();

    private List<Product> productsSortedByPositionAscendant = new ArrayList<>();
    private List<Product> productsSortedByPositionDescendant = new ArrayList<>();

    private List<Product> productsSortedByPriceAscendant = new ArrayList<>();
    private List<Product> productsSortedByPriceDescendant = new ArrayList<>();

    private List<Product> productsSortedByArrivalsAscendant = new ArrayList<>();
    private List<Product> productsSortedByArrivalsDescendant = new ArrayList<>();

    /**
     * This test have been designed considering an empty environment (with no products)
     * In the "Before" all the required test data  will be created.
     * The strategy followed is to create N different products in the environment,
     * and prepare different arrays based on the different criteria.
     */
    @Before
    public void before() {
        /** This products should be created through API calls to the test environment **/
        productA = Product.builder().name("A").price(15.30).build();
        productB = Product.builder().name("B").price(5.20).build();
        productC = Product.builder().name("C").price(17.90).build();

        /**The use of this arrays below is to assert the order of the products in the page**/
        productsSortedByNameDescendant.add(productC);
        productsSortedByNameDescendant.add(productB);
        productsSortedByNameDescendant.add(productA);

        productsSortedByNameAscendant.add(productA);
        productsSortedByNameAscendant.add(productB);
        productsSortedByNameAscendant.add(productC);

        /**Products sorted by arrivals and by position are not created because of missing information.
         * The correspondent arrays are not created either
         ***/
    }

    /**
     * In the "After" method, all the created products will be deleted to avoid data leftovers
     * and to keep the tests idempotent
     */
    @After
    public void after() {
        /** This products should be deleted through API calls to the test environment **/
    }

    @Given("^a user is in the products page$")
    public void aUserIsInTheProductsPage() {
        BritishAirwaysPages.openPage(Pages.PRODUCTS);
    }

    @Given("changes the order to descendant")
    public void changesTheOrderToDescendant() {
    }

    @When("the user selects {string} option from the criteria dropdown")
    public void theUserSelectsOptionFromTheCriteriaDropdown(String criteria) {
        BritishAirwaysPages.productsPage().sortProductsBy(criteria);
    }

    @Then("the products are sorted by position in ascendant order")
    public void theProductsAreSortedByPositionInAscendantOrder() {
        List<Product> products = BritishAirwaysPages.productsPage().getProductsFromPage();
        Assert.assertEquals(products, productsSortedByPositionAscendant);
    }

    @Then("the products are sorted by product name in descendant order")
    public void theProductsAreSortedByProductNameInDescendantOrder() {
        List<Product> products = BritishAirwaysPages.productsPage().getProductsFromPage();
        Assert.assertEquals(products, productsSortedByNameDescendant);
    }
}
