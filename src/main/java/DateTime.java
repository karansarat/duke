import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public static final String dashLine = "\t____________________________________________________________\n";
    SimpleDateFormat formatter;
    Date dt;

    public DateTime() {
        this.formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }
    public void parse(String sDate) throws ParseException {
            this.dt = formatter.parse(sDate);
    }
}
