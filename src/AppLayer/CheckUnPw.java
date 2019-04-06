package AppLayer;

public class CheckUnPw {

    public static boolean chechUnPw (String un, String pw, String q1, String q2, String q3, String q4) {

        boolean badUnPwflag = false;
        if (un.length() < 8 || un.length() > 20)
            badUnPwflag = true;
        else if (pw.length() < 8 || pw.length() > 20)
            badUnPwflag = true;
        else if (q1 == null || q2 == null || q3 == null || q4 == null)
            badUnPwflag = true;
        else if(CheckMalicousInput.checkText(un) || CheckMalicousInput.checkText(pw))
            badUnPwflag = true;

        return badUnPwflag;

    }
}
