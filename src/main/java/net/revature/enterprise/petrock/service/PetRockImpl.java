package net.revature.enterprise.petrock.service;

import net.revature.enterprise.petrock.model.PetRock;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PetRockImpl implements PetRockDao {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String TABLE_NAME = "rock";
    public static Integer id;


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

    /*
    GET ALL PET ROCKS
    */

    public static final String QUERY_GET_ALL_PET_ROCKS = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_NAME + " FROM " +
            TABLE_NAME;

    /*
    QUERY TO DELETE BY ID
     */

    public static final String QUERY_DELETE_BY_ID = "DELETE FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ?";

    /*
    CREATE A NEW PET ROCK
     */

    public static final String QUERY_CREATE = "INSERT INTO " +
            TABLE_NAME + " (" +
            COLUMN_NAME + ") VALUES (?)";

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

    @Override
    public ArrayList<PetRock> getAllPetRocks(Connection connection) {
        try {
            ArrayList<PetRock> petRocks = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_ALL_PET_ROCKS);
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
            return new ArrayList<>();
        }
    }

    @Override
    public int deleteById(Connection connection, int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_BY_ID);
            int result = -1;
            preparedStatement.setInt(1, id);
            try {
                result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result != 0) {
                connection.commit();
                System.out.println("Deletion of ID: " + id + " was successful.");
                preparedStatement.close();
            } else {
                connection.rollback();
                System.out.println("ID #: " + id + " does not exist or is no longer available.");
            }
            connection.setAutoCommit(true);
            return result;
        } catch (SQLException e) {
            System.out.println("ID #: " + id + " does not exist or is no longer available.");
            return -1;
        }
    }

    @Override
    public PetRock createPetRock(Connection connection, String name) {
        try {
            while (true) {
                name = name.trim();
                if (name != "" || name != null) {
                    break;
                } else {
                    System.out.print("Invalid entry. Please enter a first name: ");
                }
            }

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
            int result = -1;
            preparedStatement.setString(1, name.trim().toLowerCase());
            try {
                result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            PetRock petRock = new PetRock();
            if (resultSet.next()) {
                petRock = getById(resultSet.getInt(1), connection);
            }

            if (result != 0) {
                System.out.println("Entry was successful");
                preparedStatement.close();
                return petRock;
            } else {
                System.out.println("ID #: " + id + " does not exist or is no longer available.");
                return new PetRock();
            }
        } catch (SQLException e) {
            System.out.println("Sorry, unable to create the query due to syntax.");
            return null;
        }
    }
}
