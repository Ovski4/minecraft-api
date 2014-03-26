package net.ovski.minecraft.api.mysql;

import net.ovski.minecraft.api.connection.MySQLDatabaseConnection;

public class MysqlJailedManager
{
    /**
     * jailedUser method insert a new jailed entry in database
     * @param Location location : Contains the location of the spawn in the jail
     */
    public static void jailedUser(String pseudo, String jailId, String reason, String beginingDate, long remainingTime)
    {
        int userId = MysqlPlayerManager.getPlayerIdFromPseudo(pseudo);
        MySQLDatabaseConnection.sendData("INSERT INTO jailed (jail_id, jailed_user_id, reason, starts_at, remaining_time , active) "
                + "VALUES ("
                + jailId
                + ","
                + userId
                + ","
                + reason
                + ","
                + beginingDate
                + ","
                + remainingTime
                + ","
                + true + ")"
        );
    }

    /**
     * jailedUser method insert a new jailed entry in database
     * @param Location location : Contains the location of the spawn in the jail
     */
    public static void freeJailedUser(String pseudo, String jailId, String reason, String beginingDate, long remainingTime)
    {
        //int userId = MysqlPlayerManager.getPlayerIdFromPseudo(pseudo);
        //UPDATE where =jaled_user_id = userID
    }
}