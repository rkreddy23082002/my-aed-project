package Booking;

/**
 *
 * @author godalaramakrishnareddy
 */
public class Booking {
    private int bookingId;
    private String touristId;
    private int itemId;  // attraction/event/hotel ID
    private String category; // "Attraction", "Event", "Hotel", etc.
    private String itemName; // E.g., "Statue of Liberty"
    private String touristName;
    private String touristEmail;
    private String date;
    private int quantity;
    private boolean isActive;

    // ✅ New Field
    private String bookingDate;

    // ✅ Updated Constructor (include bookingDate)
    public Booking(int bookingId, String touristId, int itemId, String category,
                   String itemName, String touristName, String touristEmail,
                   String date, int quantity, boolean isActive, String bookingDate) {
        this.bookingId = bookingId;
        this.touristId = touristId;
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.touristName = touristName;
        this.touristEmail = touristEmail;
        this.date = date;
        this.quantity = quantity;
        this.isActive = isActive;
        this.bookingDate = bookingDate;
    }

    // ---------- Getters ----------
    public int getBookingId() {
        return bookingId;
    }

    public String getTouristId() {
        return touristId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getItemName() {
        return itemName;
    }

    public String getTouristName() {
        return touristName;
    }

    public String getTouristEmail() {
        return touristEmail;
    }

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    // ---------- Setters ----------
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public void setTouristEmail(String touristEmail) {
        this.touristEmail = touristEmail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getItemType() {
        return this.category;  // assuming `category` holds values like "Attraction", "Hotel", etc.
    }

}
