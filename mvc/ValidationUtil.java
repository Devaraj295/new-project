import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    static String namePattern = ("^[a-zA-Z]{2,20}$");
    static String phoneNumberPattern = ("^[0|91]*[7-9]{1}[0-9]{9}$");
    static String agePattern = ("^[0-9]{2}$");
   
    public static boolean isValidInput(String pattern, String inputValues) {
        return Pattern.matches(pattern, inputValues);
    }
}

   