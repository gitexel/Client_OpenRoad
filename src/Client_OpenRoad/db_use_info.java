package Client_OpenRoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Salem on 5/29/17 at 3:19 PM.
 */
public class db_use_info {

    final char fileSseparator = File.separatorChar;
    Properties props;
    FileInputStream in ;
    String driver ;
    String url ;
    String username ;
    String password ;

    boolean every_things_going_ok(){

        if(driver != null && url !=null && username != null && password !=null ) {
            return true;
        }
        else return false;
    }

    private void load_file(){

        props =  new Properties();
        try {
            in = new FileInputStream(System.getProperty("user.dir")+fileSseparator+"db_settings"+fileSseparator+"db.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


         driver = props.getProperty("jdbc.driver");
         url = props.getProperty("jdbc.url");
         username = props.getProperty("jdbc.username");
         password = props.getProperty("jdbc.password");
    }
    public db_use_info() {

        load_file();

    }


    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
