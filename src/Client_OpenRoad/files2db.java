package Client_OpenRoad;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.sql.*;

/**
 * Created by Salem on 4/6/17 at 2:07 PM.
 */
public  class files2db{
    private final char s = File.separatorChar;
    private db_use_info db_token = new db_use_info();
    public files2db() {

    }

    @Nullable
    private Connection connection() {

        Connection connection = null;

        try {
            Class.forName(db_token.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public boolean file2db(@NotNull String file_path, int cu) {
        // cu can be 1- for profile_photo 2-id_photo 3-C.V. 4-Helath insurance 5- english-certificate

        try {

            //String querySetLimit = "SET GLOBAL max_allowed_packet=41943040";  // 4 MB
            //Statement stSetLimit = this.connection().createStatement();
          //  stSetLimit.execute(querySetLimit);
            PreparedStatement pst = this.connection().prepareStatement("update opr.customer set customer." + this.wculman(cu) + "=? WHERE username=?");
            InputStream inputstream = new FileInputStream(new File(file_path));
            pst.setBlob(1, inputstream);
            pst.setString(2, account_issue.username);

            int action = pst.executeUpdate();

            if (action > 0) {
                return true;
            }

            inputstream.close();
            this.connection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Nullable
    private String wculman(int n_culman) {

        String index = null;

        if (n_culman == 1) {
            index = "profile_photo";
        } else if (n_culman == 2) {
            index = "n_id_photo";
        } else if (n_culman == 3) {
            index = "cv";
        } else if (n_culman == 4) {
            index = "health_certificate";
        } else if (n_culman == 5) {
            index = "english_certificate";
        } else return "";

        return index;
    }


    public boolean get_profile_photo() {

        boolean done = false;
        try {

            PreparedStatement smt = this.connection().prepareStatement("select profile_photo from opr.customer where username=?");
            smt.setString(1, account_issue.username);
            ResultSet rst = smt.executeQuery();

            if (rst.next()) {


                Blob blob = rst.getBlob("profile_photo");

                if (blob != null) {
                    done = true;
                    InputStream inputStream = blob.getBinaryStream();
                    new File(System.getProperty("user.home")+s+".openroad_files").mkdir();
                    OutputStream outputStream = new FileOutputStream(System.getProperty("user.home")+s+".openroad_files"+s+ account_issue.username + "profile.png");
                    int bytesRead = -1;
                    byte[] buffer = new byte[4096]; // 4 MB
                    done = true;
                    System.out.println("select done ");
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);

                    }

                    inputStream.close();
                    outputStream.close();
                }


            }
            this.connection().close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return done;
    }
}
