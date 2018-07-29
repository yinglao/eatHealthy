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

@WebServlet("/findfoodbykeyword")
public class FindFoodByKeyword extends HttpServlet {

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
        req.getRequestDispatcher("/FindFood.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Food> foods = null;


        String keyword = req.getParameter("keyword");
        if (keyword==null  || keyword.length()<1) {
            messages.put("success", "Keyword does not exsit.");
        } else {
            try {
                foods = foodDao.getFoodByKeywords(keyword);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + keyword);
            messages.put("previousFoodKeyword", keyword);

        }
        req.setAttribute("foods", foods);
        
        //Just render the JSP.
        req.getRequestDispatcher("/FindFood.jsp").forward(req, resp);
    }
}