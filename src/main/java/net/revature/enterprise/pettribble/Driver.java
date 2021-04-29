package net.revature.enterprise.pettribble;

import net.revature.enterprise.pettribble.database.PostgreDatabase;
import net.revature.enterprise.pettribble.model.PetTribble;
import net.revature.enterprise.pettribble.service.PetTribbleImpl;

import java.sql.Connection;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        PostgreDatabase db = new PostgreDatabase();
        Connection connection = db.getConnection();
        PetTribbleImpl petTribbleImpl = new PetTribbleImpl();
        PetTribble petTribble = petTribbleImpl.getById(1, connection);
        System.out.println(petTribble.toString());
        ArrayList<PetTribble> petTribbles = petTribbleImpl.getByName(connection, "rocky");
        for (PetTribble tempTribble: petTribbles) {
            System.out.println(tempTribble.toString());
        }
        String[] names = {"Joe", "Bob", "Luke", "Mike", "Myers"};
        for (String name: names) {
            petTribbleImpl.createPetTribble(connection, name);
        }
        ArrayList<PetTribble> allPetTribbles = petTribbleImpl.getAllPetTribbles(connection);
        System.out.println(allPetTribbles);
        int deletedId = petTribbleImpl.deleteById(connection, 8);
        System.out.println(deletedId);


    }


}
