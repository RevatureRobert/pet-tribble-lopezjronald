package net.revature.enterprise.petrock.service;

import net.revature.enterprise.petrock.model.PetRock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PetRockImpl implements PetRockDao {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String TABLE_NAME = "rock";


    /*
    Query to retrieve Pet Rock by id
     */
    public static final String QUERY_SEARCH_ID = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ?";

    /*
Query to retrieve all pet rocks by name
 */
    public static final String QUERY_SEARCH_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_NAME + " = ? ORDER BY " +
            COLUMN_NAME;

    public final static Scanner scanner = new Scanner(System.in);

    @Override
    public PetRock getById(int id, Connection connection) {
        try {
            PetRock petRock = new PetRock();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    petRock.setId(resultSet.getInt(1));
                    petRock.setName(resultSet.getString(2));
                }
                resultSet.close();
            }
            preparedStatement.close();
            return petRock;
        } catch (SQLException e) {
            System.out.println("There was a problem with your transaction");
            return null;
        } catch (Exception e) {
            System.out.println("ID does not exist or is no longer in the system.");
            return null;
        }
    }

    @Override
    public ArrayList<PetRock> getByName(Connection connection, String name) {
        try {
            ArrayList<PetRock> petRocks = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_NAME);
            preparedStatement.setString(1, name.toLowerCase().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    PetRock petRock = new PetRock();
                    petRock.setId(resultSet.getInt(1));
                    petRock.setName(resultSet.getString(2));
                    petRocks.add(petRock);
                }
                resultSet.close();
            }
            preparedStatement.close();
            return petRocks;
        } catch (SQLException e) {
            System.out.println("Name does not exist or is no longer in the system");
        }
        return null;
    }
}
