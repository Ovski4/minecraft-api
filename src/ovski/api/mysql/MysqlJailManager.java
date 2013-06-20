package ovski.api.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;

import ovski.api.connection.MySQLDatabaseConnection;


public class MysqlJailManager
{
    /**
     * addJail method insert a new jail entry in database
     * @param Location location : Contains the location of the spawn in the jail
     */
    public static void addJail(Location location)
    {
        MySQLDatabaseConnection.sendData("INSERT INTO jail (x, y, z) "
                + "VALUES ("
                + location.getBlockX()
                + ","
                + location.getBlockY()
                + ","
                + location.getBlockZ() + ")"
        );
    }

    /**
     * removeJail method remove a jail entry in database
     * @param int Id : Contains the id of the jail
     */
    public static void removeJail(int jailId)
    {
        MySQLDatabaseConnection.sendData("DELETE FROM jail WHERE jail_id="+jailId);
    }

    /**
     * getJailId method get the id of the jail with a given location
     * @param Location location : Contains the location
     */
    public static int getJailId(Location location)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT jail_id FROM jail "
        + "WHERE x="+location.getBlockX()+" AND y="+location.getBlockY()+" AND z="+location.getBlockZ());
        try
        {
            if (resultat != null && resultat.next())
            {
                return resultat.getInt(1);
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
     * getClosestJail method get the id of the closest jail from a location
     * @param Location location : Contains the location
     */
    public static int getClosestJailId(Location location)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT * FROM jail");
        try
        {
            if (resultat != null)
            {
                int id =-1;
                double distance = -1;
                while (resultat.next())
                {
                    int jailId = resultat.getInt(1);
                    double x = resultat.getDouble(2);
                    double y = resultat.getDouble(3);
                    double z = resultat.getDouble(4);
                    double distance2 = Math.abs(location.getX()-x)+Math.abs(location.getY()-y)+Math.abs(location.getZ()-z);
                    if(distance < 0 || distance2 < distance)
                    {
                        distance = distance2;
                        id = jailId;
                    }
                }
                return id;
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
     * exists method check if a jail exist in database
     * @param String jail_id : Contains the id of the jail
     * @return boolean
     */
    public static boolean exists(int jailId)
    {
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT jail_id FROM jail WHERE jail_id="+jailId);
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
