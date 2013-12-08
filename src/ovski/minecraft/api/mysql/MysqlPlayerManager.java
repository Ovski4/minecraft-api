package ovski.minecraft.api.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ovski.minecraft.api.connection.MySQLDatabaseConnection;
import ovski.minecraft.api.entities.PlayerStats;

/**
 * MysqlPlayerManager
 * 
 * Manage requests involving the minecraft_player table
 * Require a plugin with a database connection
 * 
 * @author baptiste <baptiste.bouchereau@gmail.com>
 */
public class MysqlPlayerManager
{
    /**
     * getPlayerIdFromPseudo method retrieve the id of a player according to his pseudo
     * 
     * @param pseudo : Contains the pseudo of the player
     * @return int playerId : The id of the player
     */
    public static int getPlayerIdFromPseudo(String pseudo)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT id FROM minecraft_player WHERE LOWER(pseudo)=LOWER('"+pseudo+"')");
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
     * exists method check if a player exist in database
     * 
     * @param pseudo : String that contains the pseudo of the player
     * @return boolean
     */
    public static boolean exists(String pseudo)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT id FROM minecraft_player WHERE LOWER(pseudo)=LOWER('"+pseudo+"')");
        try {
            if (resultat != null && resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * isRegistered method check if a player is registered as a user in database
     * 
     * @param pseudo : String that contains the pseudo of the player
     * @return boolean
     */
    public static boolean isRegistered(String pseudo)
    {
        ResultSet resultat =
            MySQLDatabaseConnection.getData(
                "SELECT user_id FROM minecraft_player WHERE LOWER(pseudo)=LOWER('"+pseudo+"')"
            )
        ;
        try {
            if (resultat != null && resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * updateStats method update the statistics of a player
     * 
     * @param playerStats : A PlayerStats object that contains the playerStats of a player
     */
    public static void updateStats(PlayerStats playerStats)
    {
        int playerId = getPlayerIdFromPseudo(playerStats.getPseudo());
        MySQLDatabaseConnection.sendData("UPDATE minecraft_player SET "
            + "broken_blocks="+playerStats.getBlocksBroken()+", "
            + "placed_blocks="+playerStats.getBlocksPlaced()+", "
            + "stupid_deaths="+playerStats.getStupidDeaths()+", "
            + "pvp_deaths="+playerStats.getNormalDeaths()+", "
            + "kills="+playerStats.getKillNumber()+", "
            + "played_time="+playerStats.getTimePlayed()+", "
            + "verbosity="+playerStats.getVerbosity()+", "
            + "prestige="+playerStats.getPrestige()
            + " WHERE id = "+playerId
        );
        System.out.println("The stats of "+playerStats.getPseudo()+" have been updated");
    }

    /**
     * updatePrestige method update the prestige of a player
     * 
     * @param prestige : Contains the value of the prestige
     * @param pseudo : String that contains the pseudo of the player 
     */
    public static void updatePrestige(String pseudo, int prestige)
    {
        int playerId = getPlayerIdFromPseudo(pseudo);
        MySQLDatabaseConnection.sendData("UPDATE minecraft_player SET "
            + "prestige="+prestige
            + " WHERE id = "+playerId
        );
    }

    /**
     * getPrestige method retrieve the prestige of a player
     * 
     * @param pseudo : Contains the pseudo of a player
     * @return prestige : The prestige value
     */
    public static int getPrestige(String pseudo)
    {
        int playerId = getPlayerIdFromPseudo(pseudo);
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT prestige FROM minecraft_player WHERE id="+playerId);
        try {
            if (resultat != null && resultat.next()) {
                return resultat.getInt(1);
            } else {
                return -1000;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return -1000;
        }
    }

    /**
     * getStats method retrieve the statistics of a player, and load them in a PlayerStats object
     * 
     * @param pseudo : Contains the pseudo of a player
     * @return PlayerStats playerStats : The stats object of the player
     */
    public static PlayerStats getStats(String pseudo)
    {
        int playerId = getPlayerIdFromPseudo(pseudo);
        PlayerStats playerStats = new PlayerStats();
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT * FROM minecraft_player WHERE id="+playerId);
        try {
            if (resultat != null && resultat.next()) {
                playerStats.setPseudo(pseudo);
                playerStats.setBlocksBroken(resultat.getInt(7));
                playerStats.setBlocksPlaced(resultat.getInt(8));
                playerStats.setStupidDeaths(resultat.getInt(9));
                playerStats.setNormalDeaths(resultat.getInt(10));
                playerStats.setKillNumber(resultat.getInt(11));
                playerStats.setTimePlayed(resultat.getLong(12));
                playerStats.setVerbosity(resultat.getInt(13));
                playerStats.setPrestige(resultat.getInt(14));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return playerStats;
    }

    /**
     * createPlayer method insert a new player in database
     * 
     * @param pseudo : the pseudo of the player
     * @param userId : the id of the user
     */
    public static void createPlayer(int userId, String pseudo)
    {
        MySQLDatabaseConnection.sendData("INSERT INTO minecraft_player"
            + "(user_id, pseudo, broken_blocks, placed_blocks, stupid_deaths,"
            + " pvp_deaths, kills, played_time, verbosity, prestige) "
            + "VALUES (" + userId + ", '" + pseudo + "', 0, 0, 0, 0, 0, 0, 0, 0)"
        );

        MysqlUserManager.setPlayer(getPlayerIdFromPseudo(pseudo), userId);
        System.out.println("player "+pseudo+" has been created");
    }
}