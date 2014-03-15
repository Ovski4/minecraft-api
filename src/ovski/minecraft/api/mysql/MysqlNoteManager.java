package ovski.minecraft.api.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ovski.minecraft.api.connection.MySQLDatabaseConnection;

/**
 * MysqlNoteManager
 * 
 * Manage the requests involving the minecraft_note table
 * Require a plugin with a database connection
 * 
 * @author baptiste <baptiste.bouchereau@gmail.com>
 */
public class MysqlNoteManager
{
    /**
     * insertNote method insert a new note entry in database
     *     
     * @param donorPseudo : Contains the pseudo of the donor
     * @param receiverPseudo : Contains the pseudo of the receiver
     * @param value : Contains the value of the note
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
     * 
     * @param donorPseudo : Contains the pseudo of the donor of the note
     * @param receiverPseudo : Contains the pseudo of the receiver of the note
     * @return boolean
     */
    public static boolean noteExists(String donorPseudo, String receiverPseudo)
    {
        int donorId = MysqlPlayerManager.getPlayerIdFromPseudo(donorPseudo);
        int receiverId = MysqlPlayerManager.getPlayerIdFromPseudo(receiverPseudo);
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT id FROM minecraft_note "
        + "WHERE receiver_player_id="+receiverId+" AND donor_player_id="+donorId);
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
     * getValueOfNote return the value of a note
     * 
     * @param donorPseudo : Contains the pseudo of the donor of the note
     * @param receiverPseudo : Contains the pseudo of the receiver of the note
     * @return the value of the note
     */
    public static int getValueOfNote(String donorPseudo, String receiverPseudo)
    {
        int donorId = MysqlPlayerManager.getPlayerIdFromPseudo(donorPseudo);
        int receiverId = MysqlPlayerManager.getPlayerIdFromPseudo(receiverPseudo);
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT value FROM minecraft_note "
        + "WHERE receiver_player_id="+receiverId+" AND donor_player_id="+donorId);
        try {
            if (resultat != null && resultat.next()) {
                return resultat.getInt(1);
            }  else {
                return -1000;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1000;
        }
    }

    /**
     * getIdOfNote return the id of a note
     * 
     * @param donorPseudo : Contains the pseudo of the donor of the note
     * @param receiverPseudo : Contains the pseudo of the receiver of the note
     * @return id
     */
    public static int getIdOfNote(String donorPseudo, String receiverPseudo)
    {
        int donorId = MysqlPlayerManager.getPlayerIdFromPseudo(donorPseudo);
        int receiverId = MysqlPlayerManager.getPlayerIdFromPseudo(receiverPseudo);
        ResultSet resultat = MySQLDatabaseConnection.getData("SELECT id FROM minecraft_note "
        + "WHERE receiver_player_id="+receiverId+" AND donor_player_id="+donorId);
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
     * updateNote method update a note
     * 
     * @param noteId : Contains the id of the note
     * @param value : Contains the value of the note
     */
    public static void updateNote(int noteId, int value)
    {
        MySQLDatabaseConnection.sendData("UPDATE minecraft_note SET "
            + "value="+value
            + " WHERE id = "+noteId
        );
    }
}