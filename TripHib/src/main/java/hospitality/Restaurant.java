package hospitality;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String cuisine;
    private String location;
    private int capacity;
    private String openingHours;
    private double averageCost;
    private boolean isActive;

    private List<String[]> bookedCustomers; // Stores [name, email] pairs

    public Restaurant(int restaurantId, String name, String cuisine, String location, int capacity,
                      String openingHours, double averageCost, boolean isActive) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisine = cuisine;
        this.location = location;
        this.capacity = capacity;
        this.openingHours = openingHours;
        this.averageCost = averageCost;
        this.isActive = isActive;
        this.bookedCustomers = new ArrayList<>();
    }

    // Getters
    public int getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public String getCuisine() { return cuisine; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public String getOpeningHours() { return openingHours; }
    public double getAverageCost() { return averageCost; }
    public boolean isActive() { return isActive; }
    public List<String[]> getBookedCustomers() { return bookedCustomers; }

    // Setters
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    public void setName(String name) { this.name = name; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }
    public void setLocation(String location) { this.location = location; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }
    public void setAverageCost(double averageCost) { this.averageCost = averageCost; }
    public void setActive(boolean active) { isActive = active; }

    // Booking method
    public boolean bookTable(String name, String email) {
        if (capacity > 0 && isActive) {
            bookedCustomers.add(new String[]{name, email});
            capacity--;
            if (capacity == 0) {
                isActive = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", averageCost=" + averageCost +
                '}';
    }
}
