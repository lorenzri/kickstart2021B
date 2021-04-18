package com.kickstart2021B;

import com.kickstart2021B.entity.in.Car;
import com.kickstart2021B.entity.in.Simulation;
import com.kickstart2021B.entity.in.Street;
import com.kickstart2021B.entity.out.GreenLight;
import com.kickstart2021B.entity.out.Schedule;
import com.kickstart2021B.entity.out.Submission;
import com.kickstart2021B.graph.Intersection;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class ParentApplication {

	public static void main(String[] args) {

		String filename = args[0];

		List<String> linesFromFile = null;
		URL resource = ParentApplication.class.getClassLoader().getResource(filename);
		File file = null;

		try {
			file = new File(resource.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Simulation simulation = buildSimulation(scanner);

		Submission submission = buildSubmission(simulation);

		try (OutputStream outputStream = new FileOutputStream(filename.split("\\.")[0] + "_submission" + ".txt")) {
			writeSubmission(outputStream, submission);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeSubmission(OutputStream outputStream, Submission submission) throws IOException {
		outputStream.write(submission.toString().getBytes());
	}

	private static Submission buildSubmission(Simulation simulation) {
		Submission submission = new Submission();
		submission.setNumberOfIntersections(simulation.getIntersections());
		List<Schedule> schedules = new ArrayList<>();

		for (Map.Entry<Integer, Intersection> entry : simulation.getIntersectionMap().entrySet()) {
			Schedule schedule = new Schedule();
			schedule.setIntersection(entry.getKey());
			schedule.setNumberOfIncomingStreets(entry.getValue().getIncomingStreets().size());
			List<GreenLight> greenlights = new ArrayList<>();
			for (Street street : entry.getValue().getIncomingStreets()) {
				GreenLight greenlight = new GreenLight();
				// TODO: 25.02.21 CHANGE

				if(entry.getValue().getIncomingStreets().size() == 1){
					greenlight.setDuration(simulation.getDuration());
				} else {
					if(simulation.getStreetUsage().get(street.getName()) != null && simulation.getStreetUsage().get(street.getName()) > 10) {
						greenlight.setDuration(Math.round(simulation.getDuration()/3));
					}
					greenlight.setDuration(1);
				}
				greenlight.setStreet(street.getName());
				greenlights.add(greenlight);
			}


			schedule.setGreenLights(greenlights);
			schedules.add(schedule);
		}
		submission.setSchedules(schedules);

		return submission;
	}

	private static Simulation buildSimulation(Scanner scanner) {
		Simulation simulation = new Simulation();

		// read simulation
		String lineSimulation = scanner.nextLine();

		simulation.setDuration(Integer.parseInt(lineSimulation.split(" ")[0]));
		simulation.setIntersections(Integer.parseInt(lineSimulation.split(" ")[1]));
		simulation.setStreets(Integer.parseInt(lineSimulation.split(" ")[2]));
		simulation.setCars(Integer.parseInt(lineSimulation.split(" ")[3]));
		simulation.setPoints(Integer.parseInt(lineSimulation.split(" ")[4]));

		// read streets
		List<Street> streets = new ArrayList<>();
		Map<Integer, Intersection> intersectionMap = new HashMap<>();
		Map<String, Integer> streetUsage = new HashMap<>();
		for(int i = 0; i < simulation.getStreets(); i++){
			String lineStreet = scanner.nextLine();
			Street street = new Street();
			String[] lineStreetArray = lineStreet.split(" ");
			street.setStart(Integer.parseInt(lineStreetArray[0]));
			street.setEnd(Integer.parseInt(lineStreetArray[1]));
			// fill intersectionmap
			if(intersectionMap.containsKey(street.getEnd())){
				Intersection intersection = intersectionMap.get(street.getEnd());
				intersection.getIncomingStreets().add(street);
			} else {
				Intersection intersection = new Intersection();
				intersection.setId(street.getEnd());
				intersection.getIncomingStreets().add(street);
				intersectionMap.put(street.getEnd(), intersection);
			}
			street.setName(lineStreetArray[2]);
			street.setLength(Integer.parseInt(lineStreetArray[3]));
			streets.add(street);
		}

		// read cars
		List<Car> cars = new ArrayList<>();
		for(int i = 0; i < simulation.getCars(); i++){
			String lineCar = scanner.nextLine();
			Car car = new Car();
			String[] lineCarArray = lineCar.split(" ");
			car.setStart(Integer.parseInt(lineCarArray[0]));
			for(int j = 1; j < lineCarArray.length; j++){
				car.getStreets().add(lineCarArray[j]);
				if (streetUsage.containsKey(lineCarArray[j])){
					streetUsage.put(lineCarArray[j], streetUsage.get(lineCarArray[j]) + 1);
				} else {
					streetUsage.put(lineCarArray[j], 1);
				}
			}
			cars.add(car);
		}

		// fill everything to simulation object
		simulation.setStreetList(streets);
		simulation.setCarList(cars);
		simulation.setIntersectionMap(intersectionMap);
		simulation.setStreetUsage(streetUsage);

		return simulation;
	}
}
