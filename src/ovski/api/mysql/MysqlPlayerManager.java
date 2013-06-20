package ovski.api.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ovski.api.connection.MySQLDatabaseConnection;


/**
 * MysqlPlayerManager
 * Manage requests involving the user table
 * Require a plugin with a database connection
 * @author Ovski
 */
public class MysqlPlayerManager
{
    /**
     * getPlayerIdFromPseudo method retrieve the id of a player according to his pseudo
     * @param String pseudo : Contains the pseudo of the player
     * @return int playerId : The id of the player
     */
    public static int getPlayerIdFromPseudo(String pseudo)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT player_id FROM player WHERE LOWER(pseudo)=LOWER('"+pseudo+"')");
        try
        {
            if (resultat != null && resultat.next())
            {
                return resultat.getInt("player_id");
            }
            else
            {
                return -1;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * exists method check if a player exist in database
     * @param String pseudo : Contains the pseudo of the player
     * @return boolean
     */
    public static boolean exists(String pseudo)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT player_id FROM player WHERE LOWER(pseudo)=LOWER('"+pseudo+"')");
        try
        {
            if (resultat != null && resultat.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}