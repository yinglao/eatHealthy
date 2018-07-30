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

@WebServlet("/findnutrientbyfoodid")
public class FindNutrient extends HttpServlet {

    protected NutrientDataDao nutrientDataDao;
    protected FoodDao foodDao;

    @Override
    public void init() throws ServletException {
        nutrientDataDao = NutrientDataDao.getInstance();
        foodDao = FoodDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int foodId = Integer.parseInt(req.getParameter("foodid"));
        if (foodId < 1) {
            messages.put("success", "Food Id does not exsit.");
        } else {
            
            messages.put("success", "Displaying results for " + foodId);
        }
        
        Food food;
        List<NutrientData> nutrientDataList = new ArrayList<NutrientData>();
        try {
            food = foodDao.getFoodById(foodId);
            nutrientDataList = nutrientDataDao.getNutrientDataByFood(food);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.setAttribute("nutrientDataList", nutrientDataList);
        req.getRequestDispatcher("/FindNutrientValue.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Food food = null;
        List<NutrientData> nutrientDataList = null;

        int foodId = Integer.parseInt(req.getParameter("foodid"));
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

        }
        
        
        req.setAttribute("nutrientDataList", nutrientDataList);
        req.getRequestDispatcher("/FindNutrientValue.jsp").forward(req, resp);
    }
}