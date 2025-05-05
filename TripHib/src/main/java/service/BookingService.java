 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Booking.Booking;
import Transport.TransportManager;
import Transport.TransportationRoute;
import UserFeedback.ServiceFeedback;
import event.Event;
import hospitality.HospitalityManager;
import event.EventManager;
import hospitality.HospitalityProperty;
import hospitality.Hotel;
import hospitality.Restaurant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tour.Attraction;
import tour.Tour;
import tour.TourGuide;
import tourist.Tourist;
import ui.View.ViewAttractionAndEventPanel;
import ui.Admin.UserManagementJPanel;
import ui.Support.BookingSupportManagerPanel;



/**
 *
 * @author godalaramakrishnareddy
 */
public class BookingService {

    private static BookingService instance;

    private List<Attraction> attractions;
    private List<Hotel> hotels;
    private List<Restaurant> restaurants;
    private List<TransportationRoute> routes;
    private List<Tour> tours;
    private List<Booking> bookings;
    private List<TourGuide> guides;
    private List<Event> events;
    private List<Tourist> tourists;
    private List<HospitalityProperty> properties;
    private List<EventManager> eventManagers;
    private List<TransportManager> transportManagers;
    private List<ViewAttractionAndEventPanel> touristPanels = new ArrayList<>();
    private List<ServiceFeedback> serviceFeedbacks;
    private List<UserManagementJPanel> userManagementPanels = new ArrayList<>();
    private List<hospitality.HospitalityManager> hospitalityManagers;
    private Map<String, List<String>> transportBookings = new HashMap<>();
   // private List<Hotel> hotels;
   // private List<Restaurant> restaurants;


    private BookingService() { // Make constructor private
        attractions = new ArrayList<>();
        hotels = new ArrayList<>();
        restaurants = new ArrayList<>();
        routes = new ArrayList<>();
        tours = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeData();
        guides = new ArrayList<>();
        initializeGuides();
        events = new ArrayList<>();
        initializeEvents();
        tourists = new ArrayList<>();
        initializeTourists();
        properties = new ArrayList<>();
        initializeHospitalityData();
        eventManagers = new ArrayList<>();  // Initialize the list
        initializeEventManagerData();
        transportManagers = new ArrayList<>();
        initializeTransportManagerData();
        serviceFeedbacks = new ArrayList<>();
        initializeServiceFeebackData();
        hospitalityManagers = new ArrayList<>();
        initializeHospitalityManagerData();


    }

    public static synchronized BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    private void initializeEventManagerData() {
        // Add some demo data if needed
        eventManagers.add(new EventManager(1, "John Doe", "john", "pass123",
                "123-456-7890", "Corporate Events", true));
        eventManagers.add(new EventManager(2, "Jane Smith", "jane", "pass456",
                "234-567-8901", "Wedding Events", true));
    }

    public List<EventManager> getEventManagers() {
        return new ArrayList<>(eventManagers);  // Return a copy of the list
    }

    private void initializeData() {
        // Initialize Attractions with proper time ranges
        attractions.add(new Attraction(1, "Statue of Liberty", "New York, NY", "Iconic national monument", 23.5, "8:30AM-4PM", 500, true));
        attractions.add(new Attraction(2, "Grand Canyon", "Arizona", "Famous natural canyon", 35.0, "6AM-8PM", 1000, true));
        attractions.add(new Attraction(3, "Golden Gate Bridge", "San Francisco, CA", "Suspension bridge landmark", 0.0, "24 Hours", 800, true));
        attractions.add(new Attraction(4, "Disneyland Park", "Anaheim, CA", "Theme park resort", 124.0, "8AM-11PM", 10000, true));
        attractions.add(new Attraction(5, "Yellowstone National Park", "Wyoming", "First national park", 35.0, "Open 24 Hours", 1200, true));
        attractions.add(new Attraction(6, "Empire State Building", "New York, NY", "Observation deck & skyscraper", 44.0, "10AM-12AM", 600, true));
        attractions.add(new Attraction(7, "Mount Rushmore", "South Dakota", "Historical monument", 10.0, "5AM-11PM", 700, true));
        attractions.add(new Attraction(8, "Alcatraz Island", "San Francisco, CA", "Historic prison island", 39.9, "9AM-6PM", 400, true));
        attractions.add(new Attraction(9, "Times Square", "New York, NY", "Major commercial intersection", 0.0, "24 Hours", 3000, true));
        attractions.add(new Attraction(10, "Space Needle", "Seattle, WA", "Observation tower", 35.0, "10AM-9PM", 500, true));


        // Initialize Hotels
        hotels.add(new Hotel(1, "The Plaza Hotel", "New York, NY", 200, 500.0, "WiFi, Spa, Butler Service", 5, true));
        hotels.add(new Hotel(2, "The Venetian Resort", "Las Vegas, NV", 400, 300.0, "Casino, Pool, Restaurants", 5, true));
        hotels.add(new Hotel(3, "Waldorf Astoria", "Chicago, IL", 220, 350.0, "Spa, Fitness Center", 5, true));
        hotels.add(new Hotel(4, "The Beverly Hills Hotel", "Los Angeles, CA", 180, 450.0, "Pool, Fine Dining", 5, true));
        hotels.add(new Hotel(5, "Fairmont Olympic Hotel", "Seattle, WA", 190, 320.0, "Historic Decor, Pool", 4, true));

        // Add more hotels...

        // Initialize Restaurants
        restaurants.add(new Restaurant(1, "Joe’s Stone Crab", "Seafood", "Miami, FL", 150, "11:30AM-10PM", 75.0, true));
        restaurants.add(new Restaurant(2, "Gordon Ramsay Hell’s Kitchen", "Contemporary", "Las Vegas, NV", 120, "4PM-11PM", 85.0, true));
        restaurants.add(new Restaurant(3, "Franklin Barbecue", "Barbecue", "Austin, TX", 100, "11AM-3PM", 40.0, true));
        restaurants.add(new Restaurant(4, "Alinea", "Fine Dining", "Chicago, IL", 60, "5PM-10PM", 295.0, true));
        restaurants.add(new Restaurant(5, "Canlis", "Fine Dining", "Seattle, WA", 80, "5PM-10PM", 150.0, true));

        // Add more restaurants...

        // Initialize Tours
        tours.add(new Tour(1, "Statue of Liberty Guided Tour", "Emily Davis", 25, 30.0));
        tours.add(new Tour(2, "Grand Canyon Hiking Tour", "James Wilson", 20, 50.0));
        tours.add(new Tour(3, "Hollywood Sign & Movie History Walk", "Jessica Lee", 15, 35.0));
        tours.add(new Tour(4, "Chicago Architecture River Cruise", "Daniel Martinez", 40, 45.0));
        tours.add(new Tour(5, "Savannah Ghosts & Gravestones Tour", "Olivia Taylor", 18, 32.0));

        // Add more tours...
    }

    private void initializeGuides() {
        guides.add(new TourGuide(1, "Sainadh Kesana", "guide1", "guide123",
                "617-555-0101", true));
        guides.add(new TourGuide(2, "Tharun", "guide2", "guide456",
                "617-555-0102", true));
        guides.add(new TourGuide(3, "Joe", "guide3", "pass789",
                "617-555-0103", true));
        guides.add(new TourGuide(4, "Sarah Johnson", "guide4", "pass321",
                "617-555-0104", true));
    }

    private void initializeEvents() {
        events.add(new Event(1, "Taste of Chicago", "Grant Park", "Annual food festival", 0.0, "2024-07-10", "10AM-9PM", 10000, true, "Food & Culture", "City of Chicago"));
        events.add(new Event(2, "Coachella Music Festival", "Empire Polo Club, CA", "Music and arts festival", 499.0, "2024-04-12", "12PM-12AM", 125000, true, "Music", "Goldenvoice"));
        events.add(new Event(3, "New Orleans Jazz & Heritage Festival", "Fair Grounds Race Course", "Jazz and heritage celebration", 85.0, "2024-05-03", "11AM-7PM", 60000, true, "Music", "NOLA Jazz Org"));
        events.add(new Event(4, "San Diego Comic-Con", "San Diego Convention Center", "Pop culture convention", 250.0, "2024-07-18", "10AM-8PM", 135000, true, "Entertainment", "Comic-Con International"));
        events.add(new Event(5, "NYC Marathon", "Central Park", "Annual marathon", 295.0, "2024-11-03", "8AM-4PM", 50000, true, "Sports", "New York Road Runners"));
        events.add(new Event(6, "National Cherry Blossom Festival", "Washington D.C.", "Spring blossom celebration", 0.0, "2024-04-01", "10AM-6PM", 150000, true, "Culture", "National Park Service"));
        events.add(new Event(7, "SXSW Tech & Music", "Austin Convention Center", "Tech and music conference", 395.0, "2024-03-10", "9AM-11PM", 75000, true, "Technology", "SXSW LLC"));
        events.add(new Event(8, "Burning Man", "Black Rock Desert, NV", "Art & community gathering", 575.0, "2024-08-25", "24 Hours", 80000, true, "Arts", "Burning Man Project"));
        events.add(new Event(9, "Miami Art Basel", "Miami Beach Convention Center", "Contemporary art show", 60.0, "2024-12-05", "11AM-7PM", 70000, true, "Arts", "Art Basel Group"));
        events.add(new Event(10, "Los Angeles Food & Wine Festival", "Downtown LA", "Gourmet food festival", 125.0, "2024-08-22", "12PM-10PM", 6000, true, "Food & Drink", "LA Events Group"));
        events.add(new Event(11, "Seattle International Film Festival", "Seattle Center", "Film screenings and talks", 40.0, "2024-05-15", "10AM-11PM", 30000, true, "Entertainment", "SIFF Foundation"));
        events.add(new Event(12, "Denver Beer Fest", "Downtown Denver", "Craft beer tasting week", 45.0, "2024-09-27", "1PM-9PM", 10000, true, "Food & Drink", "Visit Denver"));
        events.add(new Event(13, "Austin City Limits", "Zilker Park", "Music festival", 350.0, "2024-10-04", "12PM-10PM", 75000, true, "Music", "C3 Presents"));
        events.add(new Event(14, "Philadelphia Folk Festival", "Old Pool Farm", "Folk music celebration", 75.0, "2024-08-16", "11AM-10PM", 8000, true, "Music", "Philadelphia Folksong Society"));
        events.add(new Event(15, "Houston Rodeo", "NRG Stadium", "Rodeo and concerts", 20.0, "2024-03-01", "10AM-10PM", 200000, true, "Culture", "Houston Livestock Show"));
        events.add(new Event(16, "Atlanta Tech Village Demo Day", "Atlanta Tech Village", "Startup product demos", 15.0, "2024-11-12", "9AM-5PM", 1500, true, "Technology", "ATV Community"));
        events.add(new Event(17, "Las Vegas Halloween Parade", "Fremont Street", "Spooky costumes & fun", 0.0, "2024-10-31", "6PM-11PM", 10000, true, "Holiday", "Las Vegas Events"));
        events.add(new Event(18, "Portland Rose Festival", "Waterfront Park", "Parade and carnival", 10.0, "2024-06-01", "10AM-9PM", 20000, true, "Holiday", "Rose Festival Foundation"));
        events.add(new Event(19, "Orlando Health Expo", "Orange County Convention Center", "Fitness & wellness fair", 30.0, "2024-05-05", "9AM-6PM", 4000, true, "Health", "Orlando Health Council"));
        events.add(new Event(20, "San Francisco Holiday Market", "Union Square", "Christmas crafts & music", 0.0, "2024-12-10", "11AM-9PM", 5000, true, "Holiday", "SF Downtown Assoc"));

    }

    private void initializeTourists() {
        tourists.add(new Tourist(1, "John Smith", "tourist", "tourist123",
                "rkreddy23082002@gmail.com", "617-555-0101", "USA", "P123456",
                "Cultural Tours", true, 0, 0));

        tourists.add(new Tourist(2, "Sainadh Kesana", "tourist2", "tourist2123",
                "tharun.p@northeastern.edu", "617-555-0102", "Spain", "P234567",
                "Food Tours", true, 0, 0));

        tourists.add(new Tourist(3, "Pallela Tharun", "tourist3", "tourist3123",
                "david@gmail.com", "617-555-0103", "China", "P345678",
                "Historical Sites", true, 0, 0));

        tourists.add(new Tourist(4, "Sarah", "tourist4", "tourist4123",
                "sarah@northeastern.edu", "617-555-0104", "UK", "P456789",
                "Art Museums", true, 0, 0));

        tourists.add(new Tourist(5, "James zang", "tourist5", "tourist5123",
                "james@gmail.com", "617-555-0105", "Canada", "P567890",
                "Nature Tours", true, 0, 0));

        tourists.add(new Tourist(6, "Brown", "tourist6", "tourist6123",
                "emma@northeastern.edu", "617-555-0106", "Australia", "P678901",
                "Adventure Tours", true, 0, 0));

        tourists.add(new Tourist(7, "Rodriguez", "tourist7", "tourist7123",
                "luis@gmail.com", "617-555-0107", "Mexico", "P789012",
                "Food Tours", true, 0, 0));

        tourists.add(new Tourist(8, "Anna Kowalski", "tourist8", "tourist8123",
                "anna@northeastern.edu", "617-555-0108", "Poland", "P890123",
                "Historical Sites", true, 0, 0));

        tourists.add(new Tourist(9, "Mohammed Ahmed", "tourist9", "tourist9123",
                "mohammed@gmail.com", "617-555-0109", "UAE", "P901234",
                "Shopping Tours", true, 0, 0));

        tourists.add(new Tourist(10, "Yuki Tanaka", "tourist10", "tourist10123",
                "yuki@northeastern.edu", "617-555-0110", "Japan", "P012345",
                "Cultural Tours", true, 0, 0));
    }

    private void initializeHospitalityData() {
        // Hotels
        properties.add(new HospitalityProperty(1, "Marriott Downtown", "Hotel", 250.0, 450.0, true, "Hotel Manager - Marriott"));
        properties.add(new HospitalityProperty(2, "Hilton Garden", "Hotel", 180.0, 300.0, true, "Hotel Manager - Hilton"));
        properties.add(new HospitalityProperty(3, "Ritz-Carlton Boston", "Hotel", 400.0, 800.0, true, "Hotel Manager - Ritz"));
        properties.add(new HospitalityProperty(4, "Four Seasons Boston", "Hotel", 450.0, 900.0, true, "Hotel Manager - Four Seasons"));
        properties.add(new HospitalityProperty(5, "Westin Copley Place", "Hotel", 300.0, 600.0, true, "Hotel Manager - Westin"));
        properties.add(new HospitalityProperty(6, "Sheraton Boston", "Hotel", 250.0, 500.0, true, "Hotel Manager - Sheraton"));
        properties.add(new HospitalityProperty(7, "Boston Harbor Hotel", "Hotel", 350.0, 700.0, true, "Hotel Manager - Harbor"));
        properties.add(new HospitalityProperty(8, "Lenox Hotel", "Hotel", 280.0, 550.0, true, "Hotel Manager - Lenox"));
        properties.add(new HospitalityProperty(9, "Copley Square Hotel", "Hotel", 220.0, 440.0, true, "Hotel Manager - Copley"));
        properties.add(new HospitalityProperty(10, "W Boston", "Hotel", 320.0, 640.0, true, "Hotel Manager - W Hotels"));

        // Restaurants
        properties.add(new HospitalityProperty(11, "Legal Sea Foods", "Fine Dining", 60.0, 120.0, true, "Restaurant Manager - Legal"));
        properties.add(new HospitalityProperty(12, "Abe & Louie's", "Fine Dining", 80.0, 160.0, true, "Restaurant Manager - Abe & Louie's"));
        properties.add(new HospitalityProperty(13, "Atlantic Fish Co", "Fine Dining", 70.0, 140.0, true, "Restaurant Manager - Atlantic"));
        properties.add(new HospitalityProperty(14, "Giacomo's", "Italian Dining", 40.0, 80.0, true, "Restaurant Manager - Giacomo's"));
        properties.add(new HospitalityProperty(15, "Union Oyster House", "Seafood", 55.0, 110.0, true, "Restaurant Manager - Union"));
        properties.add(new HospitalityProperty(16, "Mooo Restaurant", "Steakhouse", 75.0, 150.0, true, "Restaurant Manager - Mooo"));
        properties.add(new HospitalityProperty(17, "O Ya", "Japanese", 150.0, 300.0, true, "Restaurant Manager - O Ya"));
        properties.add(new HospitalityProperty(18, "Mistral", "French", 90.0, 180.0, true, "Restaurant Manager - Mistral"));
        properties.add(new HospitalityProperty(19, "Grill 23", "Steakhouse", 85.0, 170.0, true, "Restaurant Manager - Grill 23"));
        properties.add(new HospitalityProperty(20, "Menton", "Fine Dining", 95.0, 190.0, true, "Restaurant Manager - Menton"));
    }
    private void initializeHospitalityManagerData() {
    hospitalityManagers.add(new HospitalityManager(1, "Alice Hilton", "hotelAlice", "hilton123", true));
    hospitalityManagers.add(new HospitalityManager(2, "Bob Bistro", "restoBob", "bistro123",  true));

    }


    private void initializeTransportManagerData() {
        transportManagers = new ArrayList<>();
        transportManagers.add(new TransportManager(1, "John Smith", "transport1",
                "trans123", true));
        transportManagers.add(new TransportManager(2, "Jane Doe", "transport2",
                "trans456", true));
    }

    public List<HospitalityProperty> getPropertiesByManager(String managerType) {
        if (managerType.equals("All")) {
            return new ArrayList<>(properties);
        }
        return properties.stream()
                .filter(p -> p.getManagerType().equals(managerType))
                .collect(Collectors.toList());
    }

    public void addProperty(HospitalityProperty property) {
        properties.add(property);
    }

    public void updateProperty(HospitalityProperty property) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropertyId() == property.getPropertyId()) {
                properties.set(i, property);
                break;
            }
        }
    }

    public void deleteProperty(int propertyId) {
        properties.removeIf(p -> p.getPropertyId() == propertyId);
    }

    public void registerUserManagementPanel(UserManagementJPanel panel) {
        if (!userManagementPanels.contains(panel)) {
            userManagementPanels.add(panel);
        }
    }

    public void notifyUserManagementPanel() {
        for (UserManagementJPanel panel : userManagementPanels) {
            panel.refreshData();
        }
    }

    public void bookAttraction(Attraction attraction, String username) {
        if (attraction.getCapacity() > 0) {
            attraction.setCapacity(attraction.getCapacity() - 1);
            for (Tourist tourist : tourists) {
                if (tourist.getUsername().equals(username)) {
                    tourist.incrementAttractionsBooked();
                    notifyUserManagementPanel();
                    break;
                }
            }
        }
    }

    public void bookEvent(Event event, String username) {
        if (event.getCapacity() > 0) {
            event.setCapacity(event.getCapacity() - 1);
            for (Tourist tourist : tourists) {
                if (tourist.getUsername().equals(username)) {
                    tourist.incrementEventsBooked();
                    notifyUserManagementPanel();
                    break;
                }
            }
        }
    }

    public List<Tourist> getAvailableTourists() {
        return new ArrayList<>(tourists);
    }

    public void addTourist(Tourist tourist) {
        tourists.add(tourist);
    }

    public void updateTourist(Tourist tourist) {
        for (int i = 0; i < tourists.size(); i++) {
            if (tourists.get(i).getTouristId() == tourist.getTouristId()) {
                tourists.set(i, tourist);
                break;
            }
        }
    }

    public void deleteTourist(int touristId) {
        tourists.removeIf(t -> t.getTouristId() == touristId);
    }

    public List<Attraction> getAvailableAttractions() {
        return new ArrayList<>(attractions);
    }

    public List<Tour> getAvailableTours() {
        return new ArrayList<>(tours);
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
        notifyTouristPanels();
    }

    public void updateAttraction(Attraction attraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equals(attraction.getName())) {
                attractions.set(i, attraction);
                break;
            }
        }
        notifyTouristPanels();
    }

    public void deleteAttraction(String name) {
        attractions.removeIf(a -> a.getName().equals(name));
        notifyTouristPanels();
    }

    public void registerTouristPanel(ViewAttractionAndEventPanel panel) {
        if (!touristPanels.contains(panel)) {
            touristPanels.add(panel);
        }
    }

    private int generateBookingId() {
        return bookings.size() + 1;
    }

    public List<TourGuide> getAvailableGuides() {
        return new ArrayList<>(guides);
    }

    public void addGuide(TourGuide guide) {
        guides.add(guide);
    }

    public void updateGuide(TourGuide guide) {
        for (int i = 0; i < guides.size(); i++) {
            if (guides.get(i).getGuideId() == guide.getGuideId()) {
                guides.set(i, guide);
                break;
            }
        }
    }

    public void deleteGuide(int guideId) {
        guides.removeIf(g -> g.getGuideId() == guideId);
    }

    public List<Event> getAvailableEvents() {
        return new ArrayList<>(events);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void updateEvent(Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId() == event.getEventId()) {
                events.set(i, event);
                break;
            }
        }
    }

    public void deleteEvent(int eventId) {
        events.removeIf(e -> e.getEventId() == eventId);
    }

    public void updateAttractionCapacity(String attractionName, int newCapacity) {
        for (Attraction attraction : attractions) {
            if (attraction.getName().equals(attractionName)) {
                attraction.setCapacity(newCapacity);
                // No need for fireTableDataChanged() here
                break;
            }
        }
    }

    public void updateEventCapacity(String eventName, int newCapacity) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                event.setCapacity(newCapacity);
                break;
            }
        }
    }

    public void notifyTouristPanels() {
        for (ViewAttractionAndEventPanel panel : touristPanels) {
            panel.refreshData();
        }
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getTouristBookings(String touristId) {
        return bookings.stream()
                .filter(b -> b.getTouristId().equals(touristId))
                .collect(Collectors.toList());
    }

    public void addTour(Tour tour) {
        if (tour != null) {
            tours.add(tour);
        }
    }

    public void updateTour(Tour tour) {
        for (int i = 0; i < tours.size(); i++) {
            if (tours.get(i).getTourId() == tour.getTourId()) {
                tours.set(i, tour);
                break;
            }
        }
    }

    public void deleteTour(int tourId) {
        tours.removeIf(t -> t.getTourId() == tourId);
    }

    public List<String> getGuideNames() {
        return guides.stream()
                .filter(TourGuide::isActive)
                .map(TourGuide::getName)
                .collect(Collectors.toList());
    }

    public List<TransportationRoute> getRoutes() {
        return routes;
    }

    public void addRoute(TransportationRoute route) {
        if (route != null) {
            routes.add(route);
        }
    }

    public void addEventManager(EventManager manager) {
        eventManagers.add(manager);
    }

    public void updateEventManager(EventManager manager) {
        for (int i = 0; i < eventManagers.size(); i++) {
            if (eventManagers.get(i).getManagerId() == manager.getManagerId()) {
                eventManagers.set(i, manager);
                break;
            }
        }
    }

    public void addTransportManager(TransportManager manager) {
        transportManagers.add(manager);
    }

    public List<TransportManager> getTransportManagers() {
        return new ArrayList<>(transportManagers);
    }

    public void updateTransportManager(TransportManager manager) {
        for (int i = 0; i < transportManagers.size(); i++) {
            if (transportManagers.get(i).getManagerId() == manager.getManagerId()) {
                transportManagers.set(i, manager);
                break;
            }
        }
    }

    public void deleteRoute(String routeName) {
        routes.removeIf(route
                -> route.getRouteName().equals(routeName)
        );
    }

    public void updateRoute(TransportationRoute updatedRoute) {
        for (int i = 0; i < routes.size(); i++) {
            TransportationRoute route = routes.get(i);
            if (route.getRouteName().equals(updatedRoute.getRouteName())) {
                routes.set(i, updatedRoute);
                break;
            }
        }
    }

    public List<ServiceFeedback> getServiceFeedbacks() {
        return new ArrayList<>(serviceFeedbacks);
    }

    public void addServiceFeedback(ServiceFeedback feedback) {
        serviceFeedbacks.add(feedback);
    }

    private void initializeServiceFeebackData() {
        // Add demo feedback data
        serviceFeedbacks.add(new ServiceFeedback(
                "Customer Support",
                "Long Wait Times",
                "Poor",
                "Increase Staff",
                "Waited 45 minutes for support",
                true));

        serviceFeedbacks.add(new ServiceFeedback(
                "Technical Support",
                "Software Bugs",
                "Good",
                "Better Testing",
                "Issue resolved but took time",
                true));

        serviceFeedbacks.add(new ServiceFeedback(
                "Billing Support",
                "Invoice Error",
                "Excellent",
                "Automated Checks",
                "Quick resolution of billing issue",
                true));

        serviceFeedbacks.add(new ServiceFeedback(
                "General Inquiry",
                "Product Information",
                "Average",
                "Updated Documentation",
                "Information was outdated",
                true));

    }

    public void deleteServiceFeedback(ServiceFeedback feedback) {
        serviceFeedbacks.remove(feedback);
    }

    public void deleteServiceFeedbackAtIndex(int index) {
        if (index >= 0 && index < serviceFeedbacks.size()) {
            serviceFeedbacks.remove(index);
        }
    }

    public void removeServiceFeedback(ServiceFeedback feedback) {
        serviceFeedbacks.remove(feedback);
    }
//public void bookAttraction(Attraction attraction, String username) {
//    if (attraction.getCapacity() > 0) {
//        // Update attraction capacity
//        attraction.setCapacity(attraction.getCapacity() - 1);
//        
//        // Update tourist booking count
//        for (Tourist tourist : tourists) {
//            if (tourist.getUsername().equals(username)) {
//                tourist.incrementAttractionsBooked();
//                break;
//            }
//        }
//    }
//}

//public void bookEvent(Event event, String username) {
//    if (event.getCapacity() > 0) {
//        // Update event capacity
//        event.setCapacity(event.getCapacity() - 1);
//        
//        // Update tourist booking count
//        for (Tourist tourist : tourists) {
//            if (tourist.getUsername().equals(username)) {
//                tourist.incrementEventsBooked();
//                break;
//            }
//        }
//    }
//}
    public Tourist getTouristByUsername(String username) {
        for (Tourist tourist : tourists) {
            if (tourist.getUsername().equals(username)) {
                return tourist;
            }
        }
        return null;
    }
    public List<Hotel> getHotels() {
        return new ArrayList<>(hotels);
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void updateHotel(int index, Hotel hotel) {
        if (index >= 0 && index < hotels.size()) {
            hotels.set(index, hotel);
        }
    }

    public void deleteHotel(int index) {
        if (index >= 0 && index < hotels.size()) {
            hotels.remove(index);
        }
    }
    public List<Restaurant> getRestaurants() {
        return new ArrayList<>(restaurants);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void updateRestaurant(int index, Restaurant restaurant) {
        if (index >= 0 && index < restaurants.size()) {
            restaurants.set(index, restaurant);
        }
    }

    public void deleteRestaurant(int index) {
        if (index >= 0 && index < restaurants.size()) {
            restaurants.remove(index);
        }
    }
    public List<hospitality.HospitalityManager> getHospitalityManagers() {
    return new ArrayList<>(hospitalityManagers);
}

    public void addHospitalityManager(hospitality.HospitalityManager manager) {
        hospitalityManagers.add(manager);
    }

    public void updateHospitalityManager(hospitality.HospitalityManager updated) {
        for (int i = 0; i < hospitalityManagers.size(); i++) {
           if (hospitalityManagers.get(i).getManagerId() == updated.getManagerId()) {
                hospitalityManagers.set(i, updated);
                break;
            }
        }
    }

    public void deleteHospitalityManager(int id) {
        hospitalityManagers.removeIf(manager -> manager.getManagerId() == id);
    }

    public hospitality.HospitalityManager getHospitalityManagerByUsername(String username) {
        for (hospitality.HospitalityManager manager : hospitalityManagers) {
            if (manager.getUsername().equals(username)) {
                return manager;
            }
        }
        return null;
    }
    public void bookTransport(String routeName, String username) {
        transportBookings.computeIfAbsent(routeName, k -> new ArrayList<>()).add(username);
    }

    public List<String> getTransportBookings(String routeName) {
        return transportBookings.getOrDefault(routeName, new ArrayList<>());
    }
    
    public TransportationRoute getRouteByName(String name) {
        for (TransportationRoute route : routes) {
            if (route.getRouteName().equals(name)) {
                return route;
            }
        }
        return null;
    }
    // ✅ Booking retrieval methods for Support Manager

public List<Booking> getAllAttractionBookings() {
    return bookings.stream()
            .filter(b -> b.getCategory().equalsIgnoreCase("Attraction"))
            .collect(Collectors.toList());
}

public List<Booking> getAllEventBookings() {
    return bookings.stream()
            .filter(b -> b.getCategory().equalsIgnoreCase("Event"))
            .collect(Collectors.toList());
}

public List<Booking> getAllHotelBookings() {
    return bookings.stream()
            .filter(b -> b.getCategory().equalsIgnoreCase("Hotel"))
            .collect(Collectors.toList());
}

public List<Booking> getAllRestaurantBookings() {
    return bookings.stream()
            .filter(b -> b.getCategory().equalsIgnoreCase("Restaurant"))
            .collect(Collectors.toList());
}

public List<Booking> getAllTransportBookings() {
    return bookings.stream()
            .filter(b -> b.getCategory().equalsIgnoreCase("Transport"))
            .collect(Collectors.toList());
}

}

