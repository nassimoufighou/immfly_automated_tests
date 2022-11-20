package domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product {

    private String name;
    private String currency;
    private double price;
}
