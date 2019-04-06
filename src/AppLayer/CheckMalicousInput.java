package AppLayer;

public class CheckMalicousInput {

    // Basic SQL injection protection

    public static boolean checkText(String text) {

        boolean isMalicous = false;

        if (text.contains("\'")) {
            isMalicous = true;
        }


        return isMalicous;

    }

}
