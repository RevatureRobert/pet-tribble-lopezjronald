package net.revature.enterprise.pettribble.service;

import net.revature.enterprise.pettribble.model.PetTribble;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PetTribbleImpl implements PetTribbleDao {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String TABLE_NAME = "tribble";
    /*
    Query to retrieve Pet Tribble by id
     */
    public static final String QUERY_SEARCH_ID = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ?";
    /*
    Query to retrieve all pet tribble by name
     */
    public static final String QUERY_SEARCH_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_NAME + " = ? ORDER BY " +
            COLUMN_NAME;
    public static final String QUERY_GET_ALL_PET_TRIBBLES = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_NAME + " FROM " +
            TABLE_NAME;

    /*
    GET ALL PET TRIBBLE
    */
    public static final String QUERY_DELETE_BY_ID = "DELETE FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ?";

    /*
    QUERY TO DELETE BY ID
     */
    public static final String QUERY_CREATE = "INSERT INTO " +
            TABLE_NAME + " (" +
            COLUMN_NAME + ") VALUES (?)";

    /*
    CREATE A NEW PET tribble
     */
    public final static Scanner scanner = new Scanner(System.in);
    public static Integer id;

    @Override
    public PetTribble getById(int id, Connection connection) {
        try {
            PetTribble petTribble = new PetTribble();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    petTribble.setId(resultSet.getInt(1));
                    petTribble.setName(resultSet.getString(2));
                }
                resultSet.close();
            }
            preparedStatement.close();
            return petTribble;
        } catch (SQLException e) {
            System.out.println("There was a problem with your transaction");
            return null;
        } catch (Exception e) {
            System.out.println("ID does not exist or is no longer in the system.");
            return null;
        }
    }

    @Override
    public ArrayList<PetTribble> getByName(Connection connection, String name) {
        try {
            ArrayList<PetTribble> petTribbles = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_NAME);
            preparedStatement.setString(1, name.toLowerCase().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    PetTribble petTribble = new PetTribble();
                    petTribble.setId(resultSet.getInt(1));
                    petTribble.setName(resultSet.getString(2));
                    petTribbles.add(petTribble);
                }
                resultSet.close();
            }
            preparedStatement.close();
            return petTribbles;
        } catch (SQLException e) {
            System.out.println("Name does not exist or is no longer in the system");
        }
        return null;
    }

    @Override
    public ArrayList<PetTribble> getAllPetTribbles(Connection connection) {
        try {
            ArrayList<PetTribble> petTribbles = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_ALL_PET_TRIBBLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    PetTribble petTribble = new PetTribble();
                    petTribble.setId(resultSet.getInt(1));
                    petTribble.setName(resultSet.getString(2));
                    petTribbles.add(petTribble);
                }
                resultSet.close();
            }
            preparedStatement.close();
            return petTribbles;
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
    public PetTribble createPetTribble(Connection connection, String name) {
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
            PetTribble petTribble = new PetTribble();
            if (resultSet.next()) {
                petTribble = getById(resultSet.getInt(1), connection);
            }

            if (result != 0) {
                System.out.println("Entry was successful");
                preparedStatement.close();
                return petTribble;
            } else {
                System.out.println("ID #: " + id + " does not exist or is no longer available.");
                return new PetTribble();
            }
        } catch (SQLException e) {
            System.out.println("Sorry, unable to create the query due to syntax.");
            return null;
        }
    }
}
