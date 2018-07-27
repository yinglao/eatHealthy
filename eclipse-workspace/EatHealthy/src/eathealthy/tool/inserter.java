package eathealthy.tool;
import java.sql.SQLException;

import eathealthy.dal.*;
import eathealthy.model.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class inserter {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		// get dao
		FoodGroupDao foodGroupDao = FoodGroupDao.getInstance();
		NutrientDao nutrientDao = NutrientDao.getInstance();
		FoodDao foodDao = FoodDao.getInstance();
		LangualDescriptionDao langualDescriptionDao = LangualDescriptionDao.getInstance();
		WeightDao weightDao = WeightDao.getInstance();
		NutrientDataDao nutrientDataDao = NutrientDataDao.getInstance();
		FootnoteDao footnoteDao = FootnoteDao.getInstance();
		LangualDao langualDao = LangualDao.getInstance();

		//foodGroupDao tests
		FoodGroup foodGroup = foodGroupDao.getFoodGroupById(100);
		System.out.println(foodGroup.getFoodGroupId() + " " + foodGroup.getFoodGroupDesc());
		List<FoodGroup> foodGroups = foodGroupDao.getFoodGroupsByKeywords("Product");
		System.out.println(foodGroups.size());
		foodGroup = new FoodGroup(4000, "foodgroup1");
		foodGroup = foodGroupDao.create(foodGroup);
		foodGroup = foodGroupDao.updateFoodGroupDesc(foodGroup, "newfoodgroup");
		foodGroupDao.delete(foodGroup);
		
		//nutrientDao tests
		Nutrient nutrient = nutrientDao.getNutrientById(203);
		System.out.println(nutrient.getNutrientId() + " " + nutrient.getDescription());
		List<Nutrient> nutrients = nutrientDao.getNutrientsByKeywords("ose");
		System.out.println(nutrients.size());
		nutrient = new Nutrient(900, "unit", "nutrient1", "nutrient1");
		nutrient = nutrientDao.create(nutrient);
		nutrient = nutrientDao.updateDescription(nutrient, "newnutrient1");
		nutrientDao.delete(nutrient);
		
		//foodDao tests
		Food food = foodDao.getFoodById(10001);
		System.out.println(food.getFoodId() + " " + food.getDescription() + food.getFoodGroup().getFoodGroupDesc());
		FoodGroup foodGroup2 = foodGroupDao.getFoodGroupById(100);
		List<Food> foodList = foodDao.getFoodByFoodGroup(foodGroup2);
		System.out.println(foodList.size());
		food = new Food(93601, "desc", 0.1,0.1,0.1,0.1,foodGroup2);
		food = foodDao.create(food);
		food = foodDao.updateDescription(food, "newdesc");
		foodDao.delete(food);		
		
		//langualDescriptionDao tests
		LangualDescription langualDescription = langualDescriptionDao.getLangualDescriptionByFactor("A0107");
		System.out.println(langualDescription.getLangualFactor() + " " + langualDescription.getDescription());
		List<LangualDescription> langualDescriptions = langualDescriptionDao.getLangualDescriptionsByKeywords("Product");
		System.out.println(langualDescriptions.size());
		langualDescription = new LangualDescription("FFFFF", "DESCRIPTION");
		langualDescription = langualDescriptionDao.create(langualDescription);
		langualDescription = langualDescriptionDao.updateLangualDescription(langualDescription, "newfoodgroup");
		langualDescriptionDao.delete(langualDescription);
		
		
		//weightDao tests
		Weight weight = weightDao.getWeightById(1);
		System.out.println(weight.getWeightId() + " " + weight.getAmount() + " " + weight.getUnit() + " " + weight.getFood().getDescription() + " is " + weight.getWeightInGram() + " g");
		Food food2 = foodDao.getFoodById(1001);
		System.out.println(food2.getFoodId() + " " + food2.getDescription() + food2.getFoodGroup().getFoodGroupDesc());
		List<Weight> weightList = weightDao.getWeightByFood(food2);
		System.out.println(weightList.size());
		weight = new Weight(1, "unit", 100, food2);
		weight = weightDao.create(weight);
		weight = weightDao.updateWeightInGram(weight, 1000);
		weightDao.delete(weight);
		
		//nutrientDataDao tests
		NutrientData nutrientData = nutrientDataDao.getNutrientDataById(1);
		System.out.println(nutrientData.getNutrientDataId() + " " + nutrientData.getFood().getDescription() + " " + nutrientData.getNutrient().getDescription() + " " + nutrientData.getNutrientValue());
		Food food3 = foodDao.getFoodById(1001);
		Nutrient nutrient3 = nutrientDao.getNutrientById(203);
		
		System.out.println(food3.getFoodId() + " " + food3.getDescription() + food3.getFoodGroup().getFoodGroupDesc());
		System.out.println(nutrient3.getNutrientId() + " " + nutrient3.getDescription() + " " + nutrient3.getTagName());
		List<NutrientData> nutrientDataList = nutrientDataDao.getNutrientDataByFood(food3);
		System.out.println(nutrientDataList.size());
		//The following lines take a long time to run.
//		List<NutrientData> nutrientDataList2 = nutrientDataDao.getNutrientDataByNutrient(nutrient3);
//		System.out.println(nutrientDataList2.size());
		Food food4 = new Food(93601, "desc", 0.1,0.1,0.1,0.1,foodGroup2);
		Nutrient nutrient4 = new Nutrient(900, "unit", "nutrient1", "nutrient1");
		food4 = foodDao.create(food4);
		nutrient4 = nutrientDao.create(nutrient4);
		nutrientData = new NutrientData(10, food4, nutrient4);
		nutrientData = nutrientDataDao.create(nutrientData);
		System.out.println(nutrientData.getNutrientDataId() + " " + nutrientData.getNutrientValue());
		nutrientData = nutrientDataDao.updateValue(nutrientData, 1000);
		System.out.println(nutrientData.getNutrientDataId() + " " + nutrientData.getNutrientValue());
		nutrientDataDao.delete(nutrientData);
		foodDao.delete(food4);
		nutrientDao.delete(nutrient4);
		
		
		//footnoteDao tests
		Footnote footnote = footnoteDao.getFootnoteById(1);
		System.out.println(footnote.getFootnoteId() + " " + footnote.getFood().getDescription() + " " + footnote.getText());
		Food food5 = foodDao.getFoodById(3945);
		Nutrient nutrient5 = nutrientDao.getNutrientById(573);
		
		System.out.println(food5.getFoodId() + " " + food5.getDescription() + food5.getFoodGroup().getFoodGroupDesc());
		System.out.println(nutrient5.getNutrientId() + " " + nutrient5.getDescription() + " " + nutrient5.getTagName());
		List<Footnote> footnoteList = footnoteDao.getFootnoteByFood(food5);
		System.out.println(footnoteList.size());
		
		footnote = footnoteDao.getFootnoteByFoodAndNutrient(food5, nutrient5);
		System.out.println(footnote.getFootnoteId() + " " + footnote.getFood().getDescription() + " " + footnote.getText());
		Food food6 = new Food(93601, "desc", 0.1,0.1,0.1,0.1,foodGroup2);
		Nutrient nutrient6 = new Nutrient(900, "unit", "nutrient1", "nutrient1");
		food4 = foodDao.create(food6);
		nutrient4 = nutrientDao.create(nutrient6);
		footnote = new Footnote(food4, nutrient4, Footnote.FootnoteType.valueOf("D"), "text1");
		footnote = footnoteDao.create(footnote);
		System.out.println(footnote.getFootnoteId() + " " + footnote.getText());
		footnote = footnoteDao.updateText(footnote, "newtext");
		System.out.println(footnote.getFootnoteId() + " " + footnote.getText());
		footnoteDao.delete(footnote);
		foodDao.delete(food6);
		nutrientDao.delete(nutrient6);		

		//langualDao tests
		Langual langual = langualDao.getLangualById(1);
		System.out.println(langual.getLangualId() + " " + langual.getFood().getDescription() + " " + langual.getLangualDescription().getLangualFactor());
		Food food7 = foodDao.getFoodById(2001);
		
		System.out.println(food5.getFoodId() + " " + food5.getDescription() + food5.getFoodGroup().getFoodGroupDesc());
		List<Langual> langualList = langualDao.getLangualByFood(food7);
		System.out.println(langualList.size());
		
		
		Food food8 = new Food(93601, "desc", 0.1,0.1,0.1,0.1,foodGroup2);
		LangualDescription langualDescription8 = new LangualDescription("FFFFFF", "Desc");
		food8 = foodDao.create(food8);
		langualDescription8 = langualDescriptionDao.create(langualDescription8);
		langual = new Langual(food8, langualDescription8);
		langual = langualDao.create(langual);
		System.out.println(langual.getLangualId() + " " + langual.getLangualDescription().getLangualFactor());
		langualDao.delete(langual);
		foodDao.delete(food8);
		langualDescriptionDao.delete(langualDescription8);	
	}

}
