package net.revature.enterprise.petrock.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.revature.enterprise.petrock.database.PostgreDatabase;
import net.revature.enterprise.petrock.model.PetRock;
import net.revature.enterprise.petrock.service.PetRockImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(urlPatterns = "/pet-rocks")
public class PetRockServlet extends HttpServlet {

    PostgreDatabase postgreDatabase = new PostgreDatabase();
    Connection connection = postgreDatabase.getConnection();
    PetRockImpl petRockImpl = new PetRockImpl();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<PetRock> petRocks = petRockImpl.getAllPetRocks(connection);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


}
