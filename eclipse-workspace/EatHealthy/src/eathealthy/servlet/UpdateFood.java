package eathealthy.servlet;


import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import eathealthy.dal.FoodDao;
import eathealthy.dal.FoodGroupDao;
import eathealthy.model.Food;

@WebServlet("/foodupdate")
public class UpdateFood extends HttpServlet {
    protected FoodDao foodDao;

    @Override
    public void init() throws ServletException {
        foodDao = FoodDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
    	// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int foodId = Integer.parseInt(req.getParameter("foodid"));
        if (foodId <= 0) {
            messages.put("success", "Please enter a valid FoodID.");
        } else {
        	try {
        		Food food = foodDao.getFoodById(foodId);
        		if(food == null) {
        			messages.put("success", "Food does not exist.");
        		}
        		req.setAttribute("food", food);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/UpdateFood.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

   
        // Retrieve user and validate.
        int foodId = Integer.parseInt(req.getParameter("foodid"));
        if (foodId <= 0) {
            messages.put("success", "Please enter a valid FoodID.");
        } else {
        	try {
        		Food food = foodDao.getFoodById(foodId);
        		if(food == null) {
        			messages.put("success", "Food does not exist.");
        		} else {
        			String newDescription = req.getParameter("description");
        			if (newDescription == null) {
        	            messages.put("success", "Please enter a valid New Description.");
        	        } else {
        	        	food = foodDao.updateDescription(food, newDescription);
        	        	messages.put("success", "Successfully updated " + food.getFoodId());
        	        }
        		}
        		req.setAttribute("food", food);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }


        req.getRequestDispatcher("/UpdateFood.jsp").forward(req, resp);
    }
}
