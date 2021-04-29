package net.revature.enterprise.petrock;

import net.revature.enterprise.petrock.database.PostgreDatabase;
import net.revature.enterprise.petrock.model.PetRock;
import net.revature.enterprise.petrock.service.PetRockImpl;

import java.sql.Connection;

public class Driver {
    public static void main(String[] args) {

        PostgreDatabase db = new PostgreDatabase();
        Connection connection = db.getConnection();
        PetRockImpl petRockImpl = new PetRockImpl();
        PetRock petRock = petRockImpl.getById(1, connection);
        System.out.println(petRock.toString());

    }


}
