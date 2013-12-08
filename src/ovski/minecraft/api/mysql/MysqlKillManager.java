package ovski.minecraft.api.mysql;

import ovski.minecraft.api.connection.MySQLDatabaseConnection;

/**
 * MysqlKillManager
 * 
 * Manage the requests involving the minecraft_kill table
 * Require a plugin with a database connection
 * 
 * @author baptiste <baptiste.bouchereau@gmail.com>
 */
public class MysqlKillManager {

	/**
     * insertKill method insert a new kill in database
     * 
     * @param killerId : Contains the id of the killer
     * @param killedId : Contains the id of the killed player
     * @param weaponId : Contains the id of the weapon use for the kill
     * @param date : Contains the date of the kill
     */
    public static void insertKill(int killerId, int killedId, int weaponId, String date)
    {
        MySQLDatabaseConnection.sendData("INSERT INTO minecraft_kill (killed_player_id, killer_player_id, date, weapon_id) "
            + "VALUES ("
            + killedId
            + ","
            + killerId
            + ",'"
            + date
            + "',"
            + weaponId+ ")"
        );
    }
}