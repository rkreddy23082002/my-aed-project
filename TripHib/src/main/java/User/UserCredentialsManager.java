package User;

import hospitality.HospitalityManager;
import service.BookingService;
import java.util.*;

public class UserCredentialsManager {
    private static UserCredentialsManager instance;

    private Map<String, UserCredentials> userCredentials;
    private Map<String, String> userOrganizations;
    private Map<String, String> userEnterprises;

    private UserCredentialsManager() {
        userCredentials = new HashMap<>();
        userOrganizations = new HashMap<>();
        userEnterprises = new HashMap<>();
        initializeDefaultCredentials();
    }

    public static synchronized UserCredentialsManager getInstance() {
        if (instance == null) {
            instance = new UserCredentialsManager();
        }
        return instance;
    }

    private void initializeDefaultCredentials() {
        addUser("admin", "admin123", "Admin");
        addUser("tourist", "tourist123", "Tourist");
        addUser("guide1", "guide123", "TourGuide");
        addUser("transport1", "trans123", "TransportManager");
        addUser("event1", "event123", "EventManager");
        addUser("hotel1", "hotel123", "HospitalityManager");
        addUser("analyst1", "analyst123", "DataAnalyst");
        addUser("support1", "support123", "CustomerSupport");
        addUser("usermgr", "usermgr123", "UserManager"); 
        addUser("supportmgr", "support123", "BookingSupportManager");
    }

    public String getOrganizationForUser(String username) {
        return userOrganizations.getOrDefault(username, "Unknown");
    }

    public String getEnterpriseForUser(String username) {
        return userEnterprises.getOrDefault(username, "Unknown");
    }

    public boolean validateUser(String username, String password) {
        UserCredentials user = userCredentials.get(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }

        // 🔄 Check dynamic HospitalityManagers
        List<HospitalityManager> managers = BookingService.getInstance().getHospitalityManagers();
        for (HospitalityManager manager : managers) {
            if (manager.getUsername().equals(username)
                && manager.getPassword().equals(password)
                && manager.isActive()) {

                addUser(username, password, "HospitalityManager");
                return true;
            }
        }

        return false;
    }
    public List<UserCredentials> getAllUserCredentials() {
        return new ArrayList<>(userCredentials.values());
    }


    public String getUserRole(String username) {
        UserCredentials user = userCredentials.get(username);
        return user != null ? user.getRole() : null;
    }

    public void addUser(String username, String password, String role) {
        if (userCredentials.containsKey(username)) return;
        userCredentials.put(username, new UserCredentials(username, password, role));
    }

    public void updateUser(String oldUsername, String newUsername, String password, String role) {
        if (!userCredentials.containsKey(oldUsername)) return;
        userCredentials.remove(oldUsername);
        userCredentials.put(newUsername, new UserCredentials(newUsername, password, role));
    }

    public boolean removeUser(String username) {
        return userCredentials.remove(username) != null;
    }

    public boolean updatePassword(String username, String newPassword) {
        UserCredentials user = userCredentials.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean updateRole(String username, String newRole) {
        UserCredentials user = userCredentials.get(username);
        if (user != null) {
            user.setRole(newRole);
            return true;
        }
        return false;
    }

    public boolean userExists(String username) {
        return userCredentials.containsKey(username);
    }



    // ✅ USER DATA CLASS
    public static class UserCredentials {
        private String username;
        private String password;
        private String role;

        public UserCredentials(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getRole() { return role; }

        public void setPassword(String password) { this.password = password; }
        public void setRole(String role) { this.role = role; }

        @Override
        public String toString() {
            return "User{" +
                   "username='" + username + '\'' +
                   ", role='" + role + '\'' +
                   '}';
        }
    }
}
