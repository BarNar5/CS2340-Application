package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Test for Model class addLocation method
 * @author Naren Dikkala
 *
 */
public class AddLocationTest {

	@org.junit.Test
	public void test() {
		Random rand = new Random();
		List<Location> locations = new ArrayList<>();
		Model test = Model.getInstance();
		Location location = new Location(1, 33.7818455,-84.4349526, "Philips Arena", "stadium");
		test.addLocation(location);
		Location location2 = new Location(2, 33.7521365,-84.3840288, "Grady Health System", "hospital");
		test.addLocation(location2);
		locations.add(location);
		locations.add(location2);
		assertEquals(locations, test.getLocations());
		for (int i = 0; i < 1000; i++) {
			double lat = rand.nextDouble();
			double lon = rand.nextDouble();
			location2 = new Location(2, 33+lat,-84 - lon, "Grady Health System", "hospital");
			test.addLocation(location2);
			locations.add(location2);
		}
		assertEquals(locations, test.getLocations());
	}

}
