package cars;

import model.highway.CarPositionObserver;
import model.highway.CarTrack;
import model.timer.TimeObserver;
import passengers.CarInStationObserver;
import passengers.CarPassengerObserver;

import java.util.ArrayList;

public abstract class BaseCar implements CarObservable, TimeObserver {
    private CarTrack track;
    private final ArrayList<CarPositionObserver> positionObservers = new ArrayList<>();
    private final ArrayList<CarPassengerObserver> passengerObservers = new ArrayList<>();
    private final ArrayList<CarInStationObserver> inStationObservers = new ArrayList<>();
    private double speed = 0;
    private String currentLocation;

    protected static double MAX_SPEED;
    protected static int MAX_PASSENGERS;
    protected static long PULL_OFF_TIME;

    public final double getMaxSpeed() {
        return MAX_SPEED;
    }

    public final int getMaxPassengers() {
        return MAX_PASSENGERS;
    }
    public final long getPullOffTime() {
        return  PULL_OFF_TIME;
    }

    public BaseCar(CarTrack track) {
        this.track = track;
    }

    public CarTrack getTrack() {
        return track;
    }

    public void setTrack(CarTrack track) {
        this.track = track;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setCurrentLocation(String currentLocation){
        this.currentLocation = currentLocation;
    }

    public String getLocation(){
        return currentLocation;
    }

    @Override
    public final void registerObserver(CarPositionObserver positionObserver) {
        positionObservers.add(positionObserver);
    }

    @Override
    public final void removeObserver(CarPositionObserver positionObserver) {
        positionObservers.remove(positionObserver);
    }

    @Override
    public final void notifyPositionObservers() {
        for (CarPositionObserver positionObserver : positionObservers) {
            positionObserver.updateCarPosition(this);
        }
    }

    @Override
    public final void registerObserver(CarPassengerObserver passengerObserver) {
        passengerObservers.add(passengerObserver);
    }

    @Override
    public final void removeObserver(CarPassengerObserver passengerObserver) {
        passengerObservers.remove(passengerObserver);
    }

    @Override
    public final void notifyPassengerObservers() {
        for (CarPassengerObserver passengerObserver : passengerObservers) {
            passengerObserver.updateCarPassenger(this);
        }
    }

    @Override
    public void registerObserver(CarInStationObserver inStationObserver) {
        inStationObservers.add(inStationObserver);
    }

    @Override
    public void removeObserver(CarInStationObserver inStationObserver) {
        inStationObservers.remove(inStationObserver);
    }

    @Override
    public void notifyCarInStationObservers() {
        for (CarInStationObserver inStationObserver : inStationObservers) {
            inStationObserver.updateCarInStation(this);
        }
    }

    @Override
    public void updateTime() {

    }
}


