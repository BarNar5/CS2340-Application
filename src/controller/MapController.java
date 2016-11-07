package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import fxapp.MainApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import model.Model;
import model.Location;

import netscape.javascript.JSObject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * The controller for the map screen.
 *
 */
public class MapController implements Initializable, MapComponentInitializedListener {

    /** a link back to the main application class */
    private MainApplication mainApplication;


    /** references to the widgets in the fxml file */
    @FXML
    private GoogleMapView mapView;


    /** a GoogleMap object displayed in the window */
    private GoogleMap map;


    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    /**
     * Setup the main application link.
     *
     * @param mainApplication  a reference (link) to our main class
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }


    /**
     * Initializes the map displayed on the screen.
     */
    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(33.7756, -84.3963);

        options.center(center)
                .zoom(10)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);


        /** now we communicate with the model to get all the locations for markers */
        Model model = Model.getInstance();
        List<Location> locations = model.getLocations();

        for (Location l : locations) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(l.getLatitude(), l.getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(l.getTitle());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(l.getDescription());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }

    }

    /**
     * Called when the user clicks close in the menu.
     */
    @FXML
    public void onCloseMenu() {
        mainApplication.showApplicationScreen();
    }

}
