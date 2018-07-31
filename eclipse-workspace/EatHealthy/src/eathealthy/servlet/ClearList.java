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


@WebServlet("/clearlist")
public class ClearList extends HttpServlet {

    protected NutrientDataDao nutrientDataDao;
    protected FoodDao foodDao;
    protected List<FoodNutrient> foodNutrientList;

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
        foodNutrientList.clear();
        System.out.print("delete");
        System.out.print(foodNutrientList.size());
        req.setAttribute("foodNutrientList", foodNutrientList);
        req.getRequestDispatcher("/customerlist").forward(req, resp);
    }
    

}