import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private static List<Item> orderItems = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
        }
        return null;
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Integer totalCost(Restaurant restaurant, List<String> orderItems) {
        // Parameter : restaurant and order items
        // Input Validation - Check if items in list belong to that restaurant
        // If validation fail, return false
        // If validation success, return totalCost;
        Integer totalCost = 0;
        List<Item> menu = restaurant.getMenu();
        for (String itemName : orderItems) {
            boolean itemFind = false;
            for (Item item : menu) {
                if (itemName.equals(item.getName())) {
                    itemFind = true;
                    totalCost = totalCost + item.getPrice();
                }
            }
            if (!itemFind) {
                return null;
            }
        }
        return totalCost;
    }
}
