package ferrari_chris.esame_prova.esercizio02;

import java.util.*;
import java.util.stream.Collectors;

final class Item {
    public enum Category {
        DRINK, FRUIT, SNACK, CANDY;

        public String toString() {
            return this.name().charAt(0) + this.name().toLowerCase().substring(1);
        }
    }

    private int id;
    private double price;
    private Category category;

    public Item(int id, double price, Category category) {
        this.id = id;
        this.price = price;
        this.category = category;
    }

    public Item applyDiscount(double discount) {
        System.out.println("applyDiscount: " + this);
        return new Item(this.id, this.price * discount, this.category);
    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s_%03d $%.2f", category, id, price);
    }
}

class GroceriesStore {
    private static List<Item> createInitialStock() {
        return List.of(
                new Item(1, 10.0, Item.Category.DRINK), new Item(2, 5.1, Item.Category.DRINK),
                new Item(3, 11.0, Item.Category.DRINK), new Item(4, 5.2, Item.Category.CANDY),
                new Item(5, 20.0, Item.Category.DRINK), new Item(6, 5.3, Item.Category.FRUIT),
                new Item(7, 12.0, Item.Category.SNACK), new Item(8, 5.4, Item.Category.CANDY),
                new Item(9, 30.0, Item.Category.DRINK), new Item(10, 5.5, Item.Category.FRUIT),
                new Item(11, 13.0, Item.Category.SNACK), new Item(12, 5.6, Item.Category.CANDY),
                new Item(13, 40.0, Item.Category.DRINK), new Item(14, 5.7, Item.Category.FRUIT),
                new Item(15, 14.0, Item.Category.SNACK), new Item(16, 5.8, Item.Category.CANDY),
                new Item(17, 50.0, Item.Category.DRINK), new Item(18, 5.9, Item.Category.FRUIT),
                new Item(19, 15.0, Item.Category.SNACK), new Item(20, 5.0, Item.Category.CANDY)
        );
    }

    public static void main(String[] args) {
        List<Item> stock = createInitialStock();

        // Compute discounts for snacks
        final Item.Category discountCategory = Item.Category.CANDY;
        final double discount = 0.75;
        System.out.println(String.format("This weeks promotion: %s %3.1f%% OFF", discountCategory, (1. - discount) * 100.));

        // TODO: implement solution starting from here
        final String topDiscounted = stock.stream()
                .filter(item -> item.getCategory().equals(discountCategory))
                .map(item -> item.applyDiscount(discount))
                .sorted(Comparator.comparingDouble(Item::getPrice).reversed())
                .limit(2)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        // TODO: implement solution up to here

        System.out.println("Top discounted items are: " + topDiscounted);
    }
}
