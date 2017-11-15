package shop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by evami on 09.11.17.
 */
public class MainShop {
    private static final String LOGIN_CMD = "login";
    private static final String LOGOUT_CMD = "logout";
    private static final String SING_IN_CMD = "sing in";
    private static final String PRODUCTS_CMD = "products";
    private static final String ADD_TO_CART_CMD = "add to cart";
    private static final String OPEN_CART_CMD = "cart";
    private static final String REMOVE_FROM_CART_CMD = "remove from cart";
    private static final String CLEAR_CART_CMD = "clear cart";
    private static final String OPEN_PRODUCT_CMD = "open product";
    private static final String ISSUE_ORDER_CMD = "issue";
    private static final String CANCEL_ORDER_CMD = "cancel";
    private static final String ACCOUNT_VIEW_CMD = "account";
    private static final String ACCOUNT_CMD = "account+";
    private static final String ADD_USER_CMD = "add user";
    private static final String REMOVE_USER_CMD = "remove user";
    private static final String ADD_PRODUCT_CMD = "add product";
    private static final String REMOVE_PRODUCT_CMD = "remove product";
    private static final String ORDERS_CMD = "orders";
    private static final String USER_ORDERS_CMD = "my orders";
    private static final String USERS_CABINET_CMD = "cabinet";
    private static final String USERS_SET_LOGIN_CMD = "set login";
    private static final String USERS_SET_PASS_CMD = "set password";
    private static final String VIEW_CART = "view cart";


    private static final String BACK_CMD = "back";
    private static final String EXIT_CMD = "exit";
    private static final String HELP_CMD = "help";

    private static final String SEPARATOR_1 = " ";
    private static final String SEPARATOR_2 = ",";

    private static void welcome(){
        System.out.println("login");
        System.out.println("sing in");
        System.out.println("products");
        System.out.println("exit");
        System.out.println();
    }

    private static void issueOrderView(Scanner scanner, Shop shop){
        while (true) {
            if (shop.nowUser == null) {
                System.out.println("login");
                System.out.println("sing in");
                String line3 = scanner.nextLine().trim();
                switch (line3) {
                    case LOGIN_CMD:
                        loginView(scanner, shop);
                        break;
                    case SING_IN_CMD:
                        singinView(scanner, shop);
                        break;
                }
            }
        }
    }

    private static void userMenu(){
        System.out.println("products");
        System.out.println("cabinet");
        System.out.println("logout");
        System.out.println("back");
    }

    private static void productsMenu(){
        System.out.println("cart");
        System.out.println("add to cart");
        System.out.println("open product");
        System.out.println("back");
    }

    private static void cartMenu(){
        System.out.println(CLEAR_CART_CMD);
        System.out.println(OPEN_PRODUCT_CMD);
        System.out.println(REMOVE_FROM_CART_CMD);
        System.out.println(ISSUE_ORDER_CMD);
        System.out.println(BACK_CMD);
        System.out.println(VIEW_CART);
    }

    private static void cabinetMenu(){
        System.out.println("my orders");
        System.out.println(USERS_SET_LOGIN_CMD);
        System.out.println(USERS_SET_PASS_CMD);
        System.out.println(ACCOUNT_CMD);
        System.out.println(BACK_CMD);
    }

    private static void openProductMenu(){
        System.out.println(ADD_TO_CART_CMD);
        System.out.println(BACK_CMD);
    }

    private static void addToCart(Shop shop, String line) {
        if (!line.isEmpty()) {
            String[] addSplit = line.split(SEPARATOR_2);

            for (String mes : addSplit) {
                String[] argSplit = mes.trim().split(SEPARATOR_1);

                for (Product product : shop.getProducts().keySet()) {
                    if (product.getName().equals(argSplit[0]))
                        shop.cart.addProduct(product, Integer.parseInt(argSplit[1]));
                }
            }
        }
    }

    private static void removeFromCart(Shop shop, String line){
        if (!line.isEmpty()) {
            String[] addSplit = line.split(SEPARATOR_2);

            for (String mes : addSplit) {
                String[] argSplit = mes.trim().split(SEPARATOR_1);

                for (Product product : shop.getProducts().keySet()) {
                    if (product.getName().equals(argSplit[0]))
                        shop.cart.removeProduct(product, Integer.parseInt(argSplit[1]));
                }
            }
        }
    }

    private static void loginView(Scanner scanner, Shop shop){
        System.out.println("login");
        String login = scanner.nextLine().trim();
        System.out.println("password");
        String password = scanner.nextLine().trim();
        shop.authorizationUser(login, password);
    }

    private static void singinView(Scanner scanner, Shop shop){
        System.out.println("login");
        String login = scanner.nextLine().trim();
        System.out.println("password");
        String password = scanner.nextLine().trim();
        shop.registrationUser(login, password);
    }

    private static void productsView(Scanner scanner, Shop shop){
        for (Product product: shop.getProducts().keySet()){
            System.out.println(product + " " + shop.getProducts().get(product));
        }
        System.out.println();
        while (true){
            productsMenu();
            String line = scanner.nextLine().trim();
            switch (line){
                case OPEN_CART_CMD:
                    cartView(scanner, shop);
                    break;
                case ADD_TO_CART_CMD:
                    String line2 = scanner.nextLine().trim();
                    addToCart(shop, line2);
                    break;
                case OPEN_PRODUCT_CMD:
                    String product = scanner.nextLine().trim();
                    openproductView(scanner, shop, product);
                    break;
                case BACK_CMD:
                    return;
            }
        }
    }

    private static void openproductView(Scanner scanner, Shop shop, String product) {
        for (Product prod1 : shop.getProducts().keySet()) {
            if (prod1.getName().equals(product)) {
                while (true) {
                    openProductMenu();
                    String line = scanner.nextLine().trim();
                    switch (line) {
                        case OPEN_CART_CMD:
                            cartView(scanner, shop);
                            break;
                        case ADD_TO_CART_CMD:
                            String line2 = scanner.nextLine().trim();
                            if (!line2.isEmpty()) {
                                shop.cart.addProduct(prod1, Integer.parseInt(line2));
                            }
                        case BACK_CMD:
                            return;
                    }
                }
            }
            break;
        }
    }

    private static void cartView(Scanner scanner, Shop shop){
        while (true){
            cartMenu();
            String line = scanner.nextLine().trim();
            switch (line){
                case VIEW_CART:
                    System.out.println(shop.cart);
                    break;
                case CLEAR_CART_CMD:
                    shop.cart.clearCart();
                    break;
                case REMOVE_FROM_CART_CMD:
                    String line2 = scanner.nextLine().trim();
                    removeFromCart(shop, line2);
                case OPEN_PRODUCT_CMD:
                    String line1 = scanner.nextLine().trim();
                    openproductView(scanner, shop, line1);
                case ISSUE_ORDER_CMD:
                    issueOrderView(scanner, shop);
                    Order order = new Order(shop.cart, shop.nowUser);
                    order.issueOrder();
                    break;
                case BACK_CMD:
                    return;
            }
        }
    }

    private static void userView(Scanner scanner, Shop shop){
        userMenu();
        while (true){
            String line = scanner.nextLine().trim();
            switch (line){
                case PRODUCTS_CMD:
                    productsView(scanner, shop);
                    break;
                case USERS_CABINET_CMD:
                    cabinetView(scanner, shop);
                    break;
                case LOGOUT_CMD:
                    shop.logoutUser();
                case BACK_CMD:
                    return;
            }
        }
    }

    private static void cabinetView(Scanner scanner, Shop shop){
        cabinetMenu();
        while (true){
            String line = scanner.nextLine().trim();
            switch (line){
                case USER_ORDERS_CMD:
                    System.out.println(shop.nowUser.getOrders());
                    break;
                case USERS_SET_LOGIN_CMD:
                    System.out.println("Inter new login");
                    String newLogin = scanner.nextLine().trim();
                    System.out.println("Inter password");
                    String password = scanner.nextLine().trim();
                    shop.nowUser.setLogin(newLogin, password);
                    break;
                case USERS_SET_PASS_CMD:
                    System.out.println("Inter old password");
                    String oldPass = scanner.nextLine().trim();
                    System.out.println("Inter new password");
                    String newPass = scanner.nextLine().trim();
                    shop.nowUser.setPassword(oldPass,newPass);
                    break;
                case ACCOUNT_VIEW_CMD:
                    System.out.println(shop.nowUser.getAccount());
                    break;
                case ACCOUNT_CMD:
                    System.out.println("Inter sum");
                    shop.nowUser.replenishAccount(scanner.nextDouble());
                    break;
                case BACK_CMD:
                    return;
            }
        }
    }
    //для каждой стадии написать приветствие

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<User> users = new HashSet<>();
        HashMap<Product, Integer> products = new HashMap<>();

        users.add(new User("admin", "admin"));
        users.add(new User("milova", "milova"));

        products.put(new Product("pen", "blue", 15), 5);
        products.put(new Product("book", "Marks", 150), 10);
        products.put(new Product("pencil", "black", 10), 15);

        Shop shop = new Shop(users, products);

        while (true){
            welcome();
            String line = scanner.nextLine().trim();
            switch (line){
                case LOGIN_CMD:
                    loginView(scanner, shop);
                    if (shop.nowUser != null)
                        userView(scanner, shop);
                    break;
                case SING_IN_CMD:
                    singinView(scanner, shop);
                    if (shop.nowUser != null)
                        userView(scanner, shop);
                    break;
                case PRODUCTS_CMD:
                    productsView(scanner, shop);
                    break;
                case EXIT_CMD:
                    return;
            }
        }
    }
}
