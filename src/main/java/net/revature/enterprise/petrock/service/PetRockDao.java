package net.revature.enterprise.petrock.service;

import net.revature.enterprise.petrock.model.PetRock;

import java.sql.Connection;
import java.util.ArrayList;

public interface PetRockDao {

    PetRock getById(int id, Connection connection);

    ArrayList<PetRock> getByName(Connection connection, String name);

    ArrayList<PetRock> getAllPetRocks(Connection connection);

    int deleteById(Connection connection, int id);

    PetRock createPetRock(Connection connection, String name);

}
