package ovski.minecraft.api.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ovski.minecraft.api.connection.MySQLDatabaseConnection;

/**
 * MysqlUserManager
 * Manage requests involving the minecraft_user table
 * Require a plugin with a database connection
 * 
 * @author baptiste <baptiste.bouchereau@gmail.com>
 */
public class MysqlUserManager
{
    /**
     * getPlayerIdFromPseudo method retrieve the id of a player according to his pseudo
     * 
     * @param serverKey : get the serverKey of a user
     * @return playerId : The id of the player
     */
    public static int getUserIdFromServerKey(String serverKey)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT id FROM minecraft_user WHERE BINARY server_key='"+serverKey+"'");
        try {
            if (resultat != null && resultat.next()) {
                return resultat.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    /**
     * Set a player for a given user
     * 
     * @param playerId
     * @param userId
     */
    public static void setPlayer(int playerId, int userId)
    {
        MySQLDatabaseConnection.sendData("UPDATE minecraft_user SET "
            + "player_id="+playerId
            + " WHERE id = "+userId
        );
    }
}