package net.revature.enterprise.pettribble.servlet;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.revature.enterprise.pettribble.database.PostgreDatabase;
import net.revature.enterprise.pettribble.model.PetTribble;
import net.revature.enterprise.pettribble.service.PetTribbleImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/PetTribbles")
public class PetTribbleServlet extends HttpServlet {


    private final PostgreDatabase postgreDatabase = new PostgreDatabase();
    private final Connection connection = postgreDatabase.getConnection();
    private final PetTribbleImpl petTribbleImpl = new PetTribbleImpl();
    private final Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<PetTribble> petTribbles = petTribbleImpl.getAllPetTribbles(connection);
        request.setAttribute("TRIBBLE_LIST", petTribbles);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/all-tribbles");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


}
