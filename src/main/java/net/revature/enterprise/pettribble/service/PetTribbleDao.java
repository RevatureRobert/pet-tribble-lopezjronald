package net.revature.enterprise.pettribble.service;

import net.revature.enterprise.pettribble.model.PetTribble;

import java.sql.Connection;
import java.util.ArrayList;

public interface PetTribbleDao {

    PetTribble getById(int id, Connection connection);

    ArrayList<PetTribble> getByName(Connection connection, String name);

    ArrayList<PetTribble> getAllPetTribbles(Connection connection);

    int deleteById(Connection connection, int id);

    PetTribble createPetTribble(Connection connection, String name);


}
