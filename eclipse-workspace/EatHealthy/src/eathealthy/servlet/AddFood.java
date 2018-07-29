package eathealthy.servlet;


import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import eathealthy.dal.FoodDao;
import eathealthy.dal.FoodGroupDao;
import eathealthy.model.*;

@WebServlet("/addfood")
public class AddFood extends HttpServlet {

    protected FoodDao foodDao;

    @Override
    public void init() throws ServletException {
        foodDao = FoodDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/AddFood.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Create the food.
        int foodId = Integer.parseInt(req.getParameter("foodid"));
        String description = req.getParameter("description");
        double nitrogenFactor = Double.parseDouble(req.getParameter("nitrogenfactor"));
        double proteinFactor = Double.parseDouble(req.getParameter("proteinfactor"));
        double fatFactor = Double.parseDouble(req.getParameter("fatfactor"));
        double carbohydrateFactor = Double.parseDouble(req.getParameter("carbohydratefactor"));
        
        int foodGroupId = Integer.parseInt(req.getParameter("foodgroupid"));
        
        String foodGroupDesc = req.getParameter("foodgroupdesc");
        
        FoodGroup foodGroup = new FoodGroup(foodGroupId, foodGroupDesc);
         
        try {
        	Food food = new Food (foodId, description, nitrogenFactor, proteinFactor, fatFactor, carbohydrateFactor, foodGroup);
        	food = foodDao.create(food);
        	messages.put("success", "Successfully created " + foodId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        req.getRequestDispatcher("/AddFood.jsp").forward(req, resp);
    }
}