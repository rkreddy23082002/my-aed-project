package hospitality;

public class HospitalityManager {
    private int managerId;
    private String name;
    private String username;
    private String password;
    private boolean isActive;

    // ✅ Constructor (without type)
    public HospitalityManager(int managerId, String name, String username, String password, boolean isActive) {
        this.managerId = managerId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    // ✅ Getters
    public int getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    // ✅ Setters
    public void setActive(boolean active) {
        this.isActive = active;
    }
}
