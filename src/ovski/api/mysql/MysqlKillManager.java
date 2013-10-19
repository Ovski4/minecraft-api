package ovski.api.mysql;

import ovski.api.connection.MySQLDatabaseConnection;

/**
 * MysqlKillManagerminecraft_kill table
 * Require a plugin with a database connection
 * @author Ovski
 */
public class MysqlKillManager {

    public static void insertKill(int killerId, int killedId, int weaponId, String date)
    {
        /**
         * insertKill method insert a new kill in database
         * @param int killerId : Contains the id of the killer
         * @param int killedId : Contains the id of the killed player
         * @param int weaponId : Contains the id of the weapon use for the kill
         * @param String date : Contains the date of the kill
         */
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