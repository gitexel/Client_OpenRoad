package Client_OpenRoad;


import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Salem on 12/19/16 at 2:31 AM.
 */
public class inputs_validation extends database_input_validation {

    private final String email_regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String password_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$!*~%^&+=])(?=\\S+$).{8,}$";
    private final String username_regex = "^[a-zA-Z0-9._-]{3,}$";
    private final String phone_regex = "[0-9]{11,15}$";
    private final String regular_regex = "^[@.A-Za-z0-9,_ -/:]+$";
    private final String name_regex = "^[A-Za-z]+$";
    private final String file_name_regex = "^[\\w,\\s-]{1,35}\\.(pdf|jpg|png|docx)$";
    private final String id_regex = "[0-9]{14,}$";
    private final String card_number_regex = "[0-9]{16,17}$";
    private final String card_number_cvv_regex = "[0-9]{3,3}$";
    private final String Bank_account_number_regex = "[0-9]{9,17}$";

    /**
     * id_v()----> check if numbers only and == 14 .
     * e_mail_v() ----> check if email valid not (sam.mail- sam..mail@yahoo.com - @yahoo.com - sam@.com - sam@com, ETC).
     * username_v() ----> all letters upper and lower valid characters >= 3  and  numbers but not contain (SPECIAL CHARACTERS).
     * password_v() ----> should not less than 8 and contain upper and lower characters and  special character (@#$!*()~%^&+=) must occur at least once .
     * name_v()  ----> should have characters only.
     * phone_v() ----> should have  11 to 15 number .
     * file_name_v() ---->   characters only  not greater than 35 or less than 0
     */

    inputs_validation() {

    }

    public boolean id_v(@NotNull String id) {

        return this.matches(this.id_regex, id);
    }

    public boolean e_mail_v(@NotNull String e_mail) {

        return this.matches(this.email_regex, e_mail);

    }

    public boolean username_v(@NotNull String user) {

        return this.matches(this.username_regex, user);

    }

    /**
     * validate password with this regex
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$!*~%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     */

    public boolean password_v(@NotNull char[] password) {
        return this.password_matches(this.password_regex, password);
    }

    public boolean phone_v(@NotNull String phone) {
        return this.matches(this.phone_regex, phone);
    }

    public boolean regular_v(@NotNull String text) {

        return this.matches(this.regular_regex, text);

    }

    /**
     * [A-z] will match all the alphabets (both lowercase and uppercase).
     * ^ and $ will make sure that nothing but these alphabets will be matched.
     */
    public boolean name_v(@NotNull String text) {

        return this.matches(this.name_regex, text);
    }

    public boolean file_name_v(@NotNull String text) {

        return this.matches(this.file_name_regex, text);
    }

    public boolean card_number_v(@NotNull String text) {

        return this.matches(this.card_number_regex, text);
    }

    public boolean card_number_cvv_v(@NotNull String text) {

        return this.matches(this.card_number_cvv_regex, text);
    }

    public boolean bank_account_number_v(@NotNull String text) {

        return this.matches(this.Bank_account_number_regex, text);
    }

    private boolean matches(@NotNull String regex, @NotNull String text) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();

    }

    private boolean password_matches(@NotNull String regex, @NotNull char[] text) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(text));
        return matcher.matches();

    }

}
