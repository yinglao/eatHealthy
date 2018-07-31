package eathealthy.servlet;


import java.io.*;

import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import eathealthy.dal.FoodDao;
import eathealthy.dal.NutrientDataDao;
import eathealthy.model.*;
import eathealthy.tool.FoodNutrient;


@WebServlet("/customerlist")
public class CustomerList extends HttpServlet {

    protected NutrientDataDao nutrientDataDao;
    protected FoodDao foodDao;
    protected List<FoodNutrient> foodNutrientList;
    double totalAmount = 0;
    double totalProtein = 0;
    double totalFat = 0;
    double totalCarbohydrate = 0;
    double totalCalorie = 0;

    @Override
    public void init() throws ServletException {
        nutrientDataDao = NutrientDataDao.getInstance();
        foodDao = FoodDao.getInstance();
        foodNutrientList = new ArrayList<FoodNutrient>();
        
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        if (req.getAttribute("foodNutrientList") != null) {
        	foodNutrientList = (List<FoodNutrient>)req.getAttribute("foodNutrientList");
        	totalAmount = 0;
            totalProtein = 0;
            totalFat = 0;
            totalCarbohydrate = 0;
            totalCalorie = 0;
        	
        }

        
        req.setAttribute("foodNutrientList", foodNutrientList);
        req.setAttribute("totalAmount", totalAmount);
        req.setAttribute("totalProtein", totalProtein);
        req.setAttribute("totalFat", totalFat);
        req.setAttribute("totalCarbohydrate", totalCarbohydrate);
        req.setAttribute("totalCalorie", totalCalorie);
        req.getRequestDispatcher("/CustomerList.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
    	System.out.println("connected!");
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Food food = null;
        List<NutrientData> nutrientDataList = null;

        int foodId = Integer.parseInt(req.getParameter("foodId"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        if (foodId < 1) {
            messages.put("success", "Food Id does not exsit.");
        } else {
            try {
                food = foodDao.getFoodById(foodId);
                nutrientDataList = nutrientDataDao.getNutrientDataByFood(food);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            
            messages.put("success", "Displaying results for " + foodId);
            totalAmount += amount;
            totalProtein += nutrientDataList.get(0).getNutrientValue() * amount;
            totalFat += nutrientDataList.get(1).getNutrientValue() * amount;
            totalCarbohydrate += nutrientDataList.get(2).getNutrientValue() * amount;
            totalCalorie += nutrientDataList.get(4).getNutrientValue() * amount;
            
            foodNutrientList.add(new FoodNutrient(food,
            		amount,
            		nutrientDataList.get(0).getNutrientValue(),
            		nutrientDataList.get(1).getNutrientValue(),
            		nutrientDataList.get(2).getNutrientValue(),
            		nutrientDataList.get(4).getNutrientValue()));
        }
        req.getRequestDispatcher("/FindFood.jsp").forward(req, resp);
    }
    
    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
    	
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        foodNutrientList.clear();
//        System.out.print("delete");
//        System.out.print(foodNutrientList.size());
        req.setAttribute("foodNutrientList", foodNutrientList);
        req.getRequestDispatcher("/CustomerList.jsp").forward(req, resp);
    }
}
