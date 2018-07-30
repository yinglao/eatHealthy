# Create the schema if necessary.
DROP SCHEMA IF EXISTs FoodApp;
CREATE SCHEMA IF NOT EXISTS FoodApp;
USE FoodApp;

DROP TABLE IF EXISTS Langual;
DROP TABLE IF EXISTS LangualDescription;
DROP TABLE IF EXISTS FootNote;
DROP TABLE IF EXISTS NutrientData;
DROP TABLE IF EXISTS Nutrient;
DROP TABLE IF EXISTS Weight;
DROP TABLE IF EXISTS Unit;
DROP TABLE IF EXISTS Junk;
DROP TABLE IF EXISTS DietFood;
DROP TABLE IF EXISTS AgeRecFood;
DROP TABLE IF EXISTS Food;
DROP TABLE IF EXISTS FoodGroup;

CREATE TABLE FoodGroup (
  FoodGroupId int not null,
  FoodGroupDesc text not null,
  CONSTRAINT pk_FoodGroup_FoodGroupId PRIMARY KEY (FoodGroupId)
);

create table Food(
  FoodId int not null,
  FoodGroupId int,
  Description text,
  N_Factor float(4,2),
  PRO_Factor float(4,2),
  FAT_Factor float(4,2), 
  CHO_Factor float(4,2),
  constraint pk_Food_FoodId primary key (FoodId),
  constraint fk_Food_FoodGroupId foreign key (FoodGroupId)
    references FoodGroup(FoodGroupId)
    on update cascade on delete set null
);

create table AgeRecFood (
FoodId int,
 RecType enum('Infant', 'Elderly', 'Pregnant'),
 constraint pk_AgeRecFood_FoodId primary key (FoodId),
 constraint fk_AgeRecFood_FoodId foreign key (FoodId)
 references Food(FoodId) ON UPDATE CASCADE ON DELETE CASCADE
);
create table DietFood (
FoodId int,
 RecAmount decimal,
 RecUnit varchar(255),
 constraint pk_DietFood_FoodId primary key (FoodId),
 constraint fk_DietFood_FoodId foreign key (FoodId)
 references Food(FoodId) ON UPDATE CASCADE ON DELETE CASCADE
);
create table JunkFood (
FoodId int,
 HarmfulDesc varchar(255),
 constraint pk_JunkFood_FoodId primary key (FoodId),
 constraint fk_JunkFood_FoodId foreign key (FoodId)
 references Food(FoodId) ON UPDATE CASCADE ON DELETE CASCADE
);

create table Weight(
  WeightId int auto_increment,
  FoodId int not null,

  Amount float(8,4) not null, 
  Unit text not null,
  WeightInGram float(8,4) not null,

  constraint pk_Weight_weightId primary key(WeightId),
  constraint fk_Weight_NDB_NO foreign key(FoodId)
    references Food(FoodId)
    on update cascade on delete cascade
);

create table Nutrient(
  NutrientId int,
  Unit varchar(255),
  TagName varchar(255),
  Description text,

  constraint pk_Nutrient_NutrientId primary key(NutrientId));
  
create table NutrientData(
  NutrientDataId int auto_increment,
  FoodId int not null,
  NutrientId int not null,
  NutrientValue float(15,8),
  constraint pk_NutrientData_NutrientDataId primary key(NutrientDataId),
  constraint fk_NutrientData_FoodId foreign key(FoodId)
    references Food(FoodId)
    on update cascade on delete cascade,
  constraint fk_NutrientData_NutrientId foreign key(NutrientId)
    references Nutrient(NutrientId)
    on update cascade on delete cascade
);

create table Footnote(
  FootnoteId int auto_increment,
  FoodId int,
  FootnoteType enum('D', 'M', 'N'),
  NutrientId int,
  FootnoteText text,
  constraint pk_Footnote_FootnoteId primary key(FootnoteId),
  constraint fk_Footnote_NutrientId foreign key(NutrientId)
    references Nutrient(Nutrientid)
    on update cascade on delete set null,
  constraint fk_Footnote_FoodId foreign key(FoodId)
    references Food(FoodId)
);
# D: adds information to the food description
# M: adds information to the measure description
# N: provides additional information on a nutrient value
  
create table LangualDescription(
  Factor varchar(255),
  Description text,
  constraint pk_LangualDescription_Factor primary key(Factor)
);

create table Langual(
  LangualId int auto_increment,
  FoodId int,
  Factor varchar(255),
  constraint pk_Langual_LangualId primary key (LangualId),
  constraint fk_Langual_Factor foreign key (Factor)
    references LangualDescription(Factor)
    on update cascade on delete cascade,
  constraint fk_Langual_FoodId foreign key (FoodId)
    references Food(FoodId)
    on update cascade on delete cascade
  );
  