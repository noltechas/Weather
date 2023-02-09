import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datapoint {
    public double temperature;
    public Date time;
    public String timeString;

    private String pattern = "yyyy-MM-dd'T'HH:mm";

    public Datapoint(String timeString, double temperature){
        this.timeString = timeString;
        this.temperature = temperature;

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date date = inputFormat.parse(timeString);
            this.time = date;
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
    }
}
