package RVD;

public class Flight {
    String flightNumber;
    String departureTime;
    String destination;
    status flightStatus;

    public Flight(String flightNumber, String departureTime, String destination, status flightStatus) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.destination = destination;
        this.flightStatus = flightStatus;
    }

    public String getFlightNumber() { return flightNumber; }
    public String getDepartureTime() { return departureTime; }
    public String getDestination() { return destination; }
    public status getFlightStatus() { return flightStatus; }
    public void setFlightStatus(status flightStatus) { this.flightStatus = flightStatus; }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber + "\n" +
               "Departure Time: " + departureTime + "\n" +
               "Destination: " + destination + "\n" +
               "Status: " + flightStatus;
    }

}

enum status {
    PARKED,
    TAXING,
    RUNWAY,
    DEPARTED
}