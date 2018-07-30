use FoodApp;
set SQL_SAFE_UPDATES = 0;
delete from foodgroup;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\FD_GROUP.csv' 
INTO TABLE foodgroup
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(@col1, @col2)
set FoodGroupId = @col1, FoodGroupDesc = @col2;

delete from food;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\FOOD_DES.csv' 
INTO TABLE food
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(@col1, @col2, @col3, @col4, @col5, @col6, @col7, @col8,
  @col9, @col10, @col11, @col12, @col13, @col14)
set FoodId = @col1, FoodGroupId = @col2, Description = @col3,
  N_Factor = if(@col11 = '', null, @col11), 
  PRO_Factor = if(@col12 = '', null, @col12) , 
  FAT_Factor = if(@col13 = '', null, @col13) , 
  CHO_Factor = if(@col14 = '', null, @col14) ;
  
delete from weight;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\WEIGHT.csv' 
into table weight
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'   
IGNORE 1 LINES
(@col1, @col2, @col3, @col4, @col5, @col6, @col7)
set FoodId = @col1, Amount = @col3, Unit = @col4, WeightInGram = @col5;


  
delete from nutrient;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\NUTR_DEF.csv' 
into table nutrient
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'   
IGNORE 1 LINES
(@col1, @col2, @col3, @col4, @col5, @col6)
set NutrientId = @col1, Unit = @col2, TagName = @col3, Description = @col4;
;

delete from NutrientData;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\NUT_DATA.csv' 
INTO TABLE NutrientData
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(@col1, @col2, @col3, @col4, @col5, @col6, @col7, @col8,
  @col9, @col10, @col11, @col12, @col13, @col14, @col15, @col16, @col17)
set FoodId = @col1,
  NutrientId = @col2,
  NutrientValue = @col3;
  
delete from Footnote;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\FOOTNOTE.csv' 
INTO TABLE Footnote
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(@col1, @col2, @col3, @col4, @col5)
set FoodId = @col1,
  NutrientId = if(@col4 = '', null, @col4),
   FootnoteType= @col3,
   FootnoteText = @col5;
   
delete from LangualDescription;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\LANGDESC.csv' 
INTO TABLE LangualDescription
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

delete from Langual;
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\LANGUAL.csv' 
INTO TABLE Langual
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(@col1, @col2)
set FoodId = @col1, Factor = @col2;