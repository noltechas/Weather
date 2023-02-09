import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeatherData{

    public static void main(String[] args) {


        ArrayList<Datapoint> datapointA = new ArrayList<>();
        ArrayList<Datapoint> datapointB = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);}

        StringBuilder statsA = new StringBuilder();
        String length = "";
        length += scanner.nextLine();
        int numLines = Integer.parseInt(length);

        for (int i = 0; i <= numLines-1 ; i++) {
            statsA.append(scanner.nextLine());
            statsA.append(" ");}

        // close the scanner
        scanner.close();
        // separates every data point value

        String[] tokensA = statsA.toString().split(" ");


        for (int i = 0; i < tokensA.length; i+=2) {
            String time = tokensA[i];
            double temp = Double.parseDouble(tokensA[i+1]);

            datapointA.add(new Datapoint(time, temp));
        }

        try {
            scanner = new Scanner(new File(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder statsB = new StringBuilder();
        String lengthB = "";
        lengthB += scanner.nextLine();
        int numLinesB = Integer.parseInt(lengthB);

        for (int i = 0; i <=numLinesB-1 ;i+=1) {
            statsB.append(scanner.nextLine());
            statsB.append(" ");
        }

        // close the scanner
        scanner.close();

        // separate every data point value
        String[] tokensB = statsB.toString().split(" ");

        for (int i = 0; i < tokensB.length; i+=2) {
            String time = tokensB[i];
            double temp = Double.parseDouble(tokensB[i+1]);
            datapointB.add(new Datapoint(time, temp));
        }

    }

    public static double getMinTemp(ArrayList<Datapoint> datapoints) {
        double min = 9999999;
        for (Datapoint datapoint : datapoints) {
            if (datapoint.temperature < min)
                min = datapoint.temperature;
        }
        return min;
    }

    public static double getMaxTemp(ArrayList<Datapoint> datapoints) {
        double max = -9999999;
        for (Datapoint datapoint : datapoints) {
            if (datapoint.temperature > max)
                max = datapoint.temperature;
        }
        return max;
    }

    public static double getMeanTemp(ArrayList<Datapoint> datapoints) {
        double mean = 0;
        for (Datapoint datapoint : datapoints) {
            mean += datapoint.temperature;
        }
        return mean / datapoints.size();
    }

    public static Date getEarliestDate(ArrayList<Datapoint> datapoints) {
        Date earliestDate = datapoints.get(0).time;
        for (int i = 1; i < datapoints.size(); i++) {
            Date currentDate = datapoints.get(i).time;
            if (currentDate.before(earliestDate)) {
                earliestDate = currentDate;
            }
        }
        return earliestDate;
    }

    public static Date getLatestDate(ArrayList<Datapoint> datapoints) {
        Date latestDate = datapoints.get(0).time;
        for (int i = 1; i < datapoints.size(); i++) {
            Date currentDate = datapoints.get(i).time;
            if (currentDate.after(latestDate)) {
                latestDate = currentDate;
            }
        }
        return latestDate;
    }

    public void printTemperatureReport(ArrayList<Datapoint> datapoints){
        System.out.println("Temperature Reports (dataset A)");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        System.out.println("Minimum: " + getMinTemp(datapoints));
        System.out.println("Mean: " + getMeanTemp(datapoints));
        System.out.println("Maximum: " + getMaxTemp(datapoints));
    }

    public void printTimeReport(ArrayList<Datapoint> datapoints){
        System.out.println("Date Reports (dataset A)");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        System.out.println("Earliest: " + getEarliestDate(datapoints));
        System.out.println("Most Recent: " + getLatestDate(datapoints));
    }

    public static void printMissingReport(ArrayList<Datapoint> datapointsA, ArrayList<Datapoint> datapointsB){
        ArrayList<Datapoint> datapointsAsorted = new ArrayList<>(datapointsA);
        datapointsAsorted.sort(Comparator.comparing(event -> event.time));
        ArrayList<Datapoint> datapointsBsorted = new ArrayList<>(datapointsB);
        datapointsBsorted.sort(Comparator.comparing(event -> event.time));

        ArrayList<Datapoint> missingData = new ArrayList<>();
        ArrayList<String> inconsistentData = new ArrayList<>();
        for (Datapoint value : datapointsAsorted) {
            boolean found = false;
            for (Datapoint datapoint : datapointsBsorted) {
                found = Objects.equals(value.timeString, datapoint.timeString);
                // If the correct time is found, stop the loop and break
                // Or, if the current loop has passed the time its looking for, you know it isn't in the list so you can also break
                if (found || datapoint.time.after(value.time)) {
                    if(!datapoint.time.after(value.time)) {
                        if (datapoint.temperature != value.temperature) {
                            inconsistentData.add(datapoint.temperature + " vs. " + value.temperature + " (" + datapoint.timeString + ")");
                        }
                    }
                    break;
                }
            }
            if (!found)
                missingData.add(value);
        }

        System.out.println("Missing Data (Included in Dataset A but not Dataset B)");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for(Datapoint value : missingData)
            System.out.println(value.temperature + " (" + value.timeString + ")");

        System.out.println("Inconsistent Data");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for(String value : inconsistentData)
            System.out.println(value);
    }

}










		/*	}
			System.out.println(min);
			{
		float max = -100;
		for(int x = 0; x < datapointsA.length; x ++) {
			if(datapointsA[i].temperature > max) {
				max = datapointsA[i].temperature;

		}
			System.out.println(max);
		}
		}
			}
		}
}




	public float getMean(float array1[]) {
		float mean = 0;
		for(int i = 0; i < array1.length; i ++) {
			mean += array1[i];

		}

	return mean / array1.length;


	}
	public float getMax(float array1[]) {
		float min = 999999999;
		for(int i = 0; i < array1.length; i ++) {
			if(array1[i] < min) {
				min = array1[i];
			}
		}




		return min;


	}
	}
*/