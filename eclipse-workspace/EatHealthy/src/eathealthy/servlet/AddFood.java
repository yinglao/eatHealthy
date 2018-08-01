package eathealthy.servlet;


import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import eathealthy.dal.FoodDao;
import eathealthy.dal.FoodGroupDao;
import eathealthy.dal.NutrientDao;
import eathealthy.dal.NutrientDataDao;
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
        
        double proteinpercentage = Double.parseDouble(req.getParameter("proteinpercentage"));
        double fatpercentage = Double.parseDouble(req.getParameter("fatpercentage"));
        double carbohydratepercentage = Double.parseDouble(req.getParameter("carbohydratepercentage"));
        double calorie = Double.parseDouble(req.getParameter("calorie"));
        
        try {
        	Food food = new Food (foodId, description, nitrogenFactor, proteinFactor, fatFactor, carbohydrateFactor, foodGroup);
        	food = foodDao.create(food);
        	messages.put("success", "Successfully created " + foodId);
        	
        	NutrientDao nutrientDao = NutrientDao.getInstance();
        	NutrientData data1 = new NutrientData(proteinpercentage,food,nutrientDao.getNutrientById(203));
        	NutrientData data2 = new NutrientData(fatpercentage,food,nutrientDao.getNutrientById(204));
        	NutrientData data3 = new NutrientData(carbohydratepercentage,food,nutrientDao.getNutrientById(205));
        	NutrientData data4 = new NutrientData(calorie,food,nutrientDao.getNutrientById(208));
        	NutrientDataDao nutrientDataDao = NutrientDataDao.getInstance();
        	System.out.println("1");
        	data1 = nutrientDataDao.create(data1);
        	System.out.println("2");
        	data2 = nutrientDataDao.create(data2);
        	System.out.println("3");
        	data3 = nutrientDataDao.create(data3);
        	System.out.println("4");
        	data4 = nutrientDataDao.create(data4);
        	System.out.println("5");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        req.getRequestDispatcher("/AddFood.jsp").forward(req, resp);
    }
}