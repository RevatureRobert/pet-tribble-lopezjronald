package net.revature.enterprise.petrock;

import net.revature.enterprise.petrock.database.PostgreDatabase;
import net.revature.enterprise.petrock.model.PetRock;
import net.revature.enterprise.petrock.service.PetRockImpl;

import java.sql.Connection;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        PostgreDatabase db = new PostgreDatabase();
        Connection connection = db.getConnection();
        PetRockImpl petRockImpl = new PetRockImpl();
        PetRock petRock = petRockImpl.getById(1, connection);
        System.out.println(petRock.toString());
        ArrayList<PetRock> petRocks = petRockImpl.getByName(connection, "rocky");
        for (PetRock tempRock: petRocks) {
            System.out.println(tempRock.toString());
        }
        ArrayList<PetRock> allPetRocks = petRockImpl.getAllPetRocks(connection);
        System.out.println(allPetRocks);

    }


}
