package ovski.api.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ovski.api.connection.MySQLDatabaseConnection;


/**
 * MysqlNoteManager
 * Manage the requests involving the minecraft_note table
 * Require a plugin with a database connection
 * @author Ovski
 */
public class MysqlNoteManager {

    /**
     * insertNote method insert a new note entry in database
     * @param int donorId : Contains the id of the donor
     * @param int receiverId : Contains the id of the killed recever
     * @param int value : Contains the value of the note (=0, <0 or >0)
     */
    public static void insertNote(String donorPseudo, String receiverPseudo, int value)
    {
        int receiverId = MysqlPlayerManager.getPlayerIdFromPseudo(receiverPseudo);
        int donorId = MysqlPlayerManager.getPlayerIdFromPseudo(donorPseudo);
        MySQLDatabaseConnection.sendData("INSERT INTO minecraft_note (receiver_player_id, donor_player_id, value) "
                + "VALUES ("
                + receiverId
                + ","
                + donorId
                + ","
                + value+ ")"
        );
    }

    /**
     * exists method check if a note exist in database
     * @param String donorPseudo : Contains the pseudo of the donor of the note
     * @param String receiverPseudo : Contains the pseudo of the receiver of the note
     * @return boolean
     */
    public static boolean noteExists(String donorPseudo, String receiverPseudo)
    {
        int donorId = MysqlPlayerManager.getPlayerIdFromPseudo(donorPseudo);
        int receiverId = MysqlPlayerManager.getPlayerIdFromPseudo(receiverPseudo);
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT note_id FROM minecraft_note "
        + "WHERE receiver_player_id="+receiverId+" AND donor_player_id="+donorId);
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

    /**
     * getValueOfNote return the value of a note
     * @param String donorPseudo : Contains the pseudo of the donor of the note
     * @param String receiverPseudo : Contains the pseudo of the receiver of the note
     * @return int: value
     */
    public static int getValueOfNote(String donorPseudo, String receiverPseudo)
    {
        int donorId = MysqlPlayerManager.getPlayerIdFromPseudo(donorPseudo);
        int receiverId = MysqlPlayerManager.getPlayerIdFromPseudo(receiverPseudo);
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT value FROM minecraft_note "
        + "WHERE receiver_player_id="+receiverId+" AND donor_player_id="+donorId);
        try
        {
            if (resultat != null && resultat.next())
            {
                return resultat.getInt(1);
            }
            else
            {
                return -1000;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1000;
        }
    }

    /**
     * getIdOfNote return the id of a note
     * @param String donorPseudo : Contains the pseudo of the donor of the note
     * @param String receiverPseudo : Contains the pseudo of the receiver of the note
     * @return int: id
     */
    public static int getIdOfNote(String donorPseudo, String receiverPseudo)
    {
        int donorId = MysqlPlayerManager.getPlayerIdFromPseudo(donorPseudo);
        int receiverId = MysqlPlayerManager.getPlayerIdFromPseudo(receiverPseudo);
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT note_id FROM minecraft_note "
        + "WHERE receiver_player_id="+receiverId+" AND donor_player_id="+donorId);
        try
        {
            if (resultat != null && resultat.next())
            {
                return resultat.getInt(1);
            }
            else
            {
                return -1000;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1000;
        }
    }

    /**
     * updateNote method update a note
     * @param int note_id : Contains the id of the note
     */
    public static void updateNote(int noteId, int value)
    {
        MySQLDatabaseConnection.sendData("UPDATE minecraft_note SET "
                + "value="+value
                + " WHERE note_id = "+noteId
        );
    }
}