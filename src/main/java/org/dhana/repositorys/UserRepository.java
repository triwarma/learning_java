package org.dhana.repositorys;

import org.dhana.models.UserModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    private final String USER_DB;
    private final String PASS_DB;
    private final String URL_DB;

    public UserRepository(){
        this.USER_DB = System.getenv("USER_DB");
        this.PASS_DB = System.getenv("PASS_DB");
        this.URL_DB = System.getenv("URL_DB");
    }

    public void save(UserModel user){
        String sql = "INSERT INTO user (username, password) VALUES (?,?)";
        try(
                Connection conn = DriverManager.getConnection(URL_DB,USER_DB,PASS_DB);
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
