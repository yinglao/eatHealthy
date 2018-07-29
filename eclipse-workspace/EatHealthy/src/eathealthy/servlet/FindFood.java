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

@WebServlet("/findfood")
public class FindFood extends HttpServlet {

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
        
        Food food = null;


        int foodId = Integer.parseInt(req.getParameter("foodid"));
        if (foodId < 1) {
            messages.put("success", "Food Id does not exsit.");
        } else {
            try {
                food = foodDao.getFoodById(foodId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + foodId);
            messages.put("previousFoodId", String.valueOf(foodId));

        }
        req.setAttribute("food", food);
        
        //Just render the JSP.
        req.getRequestDispatcher("/FindFood.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
       
        Food food = null;

        int foodId = Integer.parseInt(req.getParameter("foodid"));
        if (foodId < 1) {
            messages.put("success", "Food Id does not exsit.");
        } else {
            try {
                food = foodDao.getFoodById(foodId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + foodId);

        }
        req.setAttribute("food", food);

        
        req.getRequestDispatcher("/FindFood.jsp").forward(req, resp);
    }
}