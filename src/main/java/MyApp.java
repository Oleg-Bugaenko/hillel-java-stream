import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MyApp {
    public static void main(String[] args) {
        //1.1 - 3.1
        List<Product> products = List.of(
                new Product("Book", 150.),
                new Product("Pen", 10.),
                new Product("Book", 270., true),
                new Product("Book", 300., true),
                new Product("Pencil", 10.),
                new Product("Pencil", 15.),
                new Product("Copy Book", 80.));
        //1.2
        System.out.println("+++ Task 1.2 +++");
        System.out.println(findProduct(products, "Book", 250.));

        //2.2
        System.out.println("+++ Task 2.2 +++");
        System.out.println(findDiscountedProducts(products, "Book", 10));

        //3.2 - 3.3
        System.out.println("+++ Task 3.2 - 3.3 +++");
        try {
            System.out.println(findProductWithMinPrice(products, "Book"));
            // System.out.println(findProductWithMinPrice(products, "Ruler"));
        } catch (MyException e) {
            e.printStackTrace();
        }

        //4.1 - 5.1
        List<Product> products2 = List.of(
                new Product("Book", 150., false, LocalDate.of(2023, 1, 15)),
                new Product("Pen", 10., false, LocalDate.of(2022, 10, 23)),
                new Product("Book", 30., true, LocalDate.of(2023, 1, 10)),
                new Product("Pencil", 15., false, LocalDate.of(2022, 12, 10)),
                new Product("Copy Book", 80., true, LocalDate.of(2023, 1, 8)),
                new Product("Book", 50., false, LocalDate.of(2023, 1, 8)));

        //4.2
        System.out.println("+++ Task 4.2 +++");
        System.out.println(findThreeRecentlyAddedProducts(products2));

        //5.2
        System.out.println("+++ Task 5.2 +++");
        System.out.printf("Total cost of products 'Book': %3.2f \n", calculateTotalCost(products2, "Book"));

        //6.2
        System.out.println("+++ Task 6.2 +++");
        for (Map.Entry<String, List<Product>> key : groupProducts(products2).entrySet()) {
            System.out.println(key.getKey());
            for (Product value : key.getValue()) {
                System.out.println(value.printAllData());
            }
        }

    }

    //1.2
    public static List<Product> findProduct(List<Product> prod, String typeProduct, double priceMore) {
        return prod.stream()
                .filter(product -> product.getType().equals(typeProduct))
                .filter(product -> product.getPrice() > 250.)
                .toList();
    }

    //2.2
    public static List<Product> findDiscountedProducts(List<Product> prod, String typeProduct, double discount) {
        return prod.stream()
                .filter(product -> product.getType().equals(typeProduct))
                .filter(Product::isDiscount)
                .peek(product -> {
                    double price = product.getPrice();
                    product.setPrice(price * (1 - discount / 100));
                })
                .toList();
    }

    //3.2 - 3.3
    public static Product findProductWithMinPrice(List<Product> prod, String typeProduct) throws MyException {
        try {
            return prod.stream()
                    .filter(product -> product.getType().equals(typeProduct))
                    .min(((o1, o2) -> o1.getPrice().compareTo(o2.getPrice())))
                    .orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new MyException("Product: " + typeProduct + " not found");
        }
    }

    //4.2
    public static List<Product> findThreeRecentlyAddedProducts(List<Product> prod) {
        return prod.stream()
                .sorted((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()))
                .limit(3)
                .toList();
    }

    public static double calculateTotalCost(List<Product> prod, String typeProduct) {
        return prod.stream()
                .filter(product -> product.getType().equals(typeProduct))
                .filter(e -> {
                    int currentYear = LocalDate.now().getYear();
                    return currentYear == e.getCreateDate().getYear();
                })
                .filter(product -> product.getPrice() <= 75.)
                .reduce(0.0, (cost, product) -> cost + product.getPrice(), (o1, o2) -> o1 + o2);
    }

    public static Map<String, List<Product>> groupProducts(List<Product> prod) {
        return prod.stream()
                .collect(Collectors.groupingBy(Product::getType, Collectors.toList()));
    }


}
