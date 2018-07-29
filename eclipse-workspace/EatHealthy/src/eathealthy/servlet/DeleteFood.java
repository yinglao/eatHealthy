package eathealthy.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eathealthy.dal.FoodDao;
import eathealthy.model.Food;


@WebServlet("/deletefood")
public class DeleteFood extends HttpServlet {
	
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
        messages.put("title", "Delete Food");
        // Provide a title and render the JSP.   
        req.getRequestDispatcher("/DeleteFood.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

	    // Retrieve and validate name.
	        int foodId = Integer.parseInt(req.getParameter("foodid"));
	        if (foodId <= 0) {
	            messages.put("title", "Invalid FoodId");
	            messages.put("disableSubmit", "true");
	        } else {
		        try {
		        	Food food = foodDao.getFoodById(foodId);
		        	food = foodDao.delete(food);
		        	// Update the message.
			        if (food == null) {
			            messages.put("title", "Successfully deleted ");
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("title", "Failed to delete ");
			        	messages.put("disableSubmit", "false");
			        }
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
        
        req.getRequestDispatcher("/DeleteFood.jsp").forward(req, resp);
    }
}
