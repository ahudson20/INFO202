/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  anaruhudson
 * Created: 7/08/2018
 */

/*create table Product (
Product_ID integer,
Product_Name varchar(50) not null,
Description varchar(50) not null,
Category varchar(20) not null,
List_Price decimal(5, 2) not null,
Quantity_In_Stock decimal not null,
constraint Product_PK primary key (Product_ID)
);
insert into Product (Product_ID, Product_Name, Description, Category, List_Price, Quantity_In_Stock)
values (1, 'Bottle', 'Grey', 'Misc', 35, 10);
values (1, 'Bag', 'Green', 'Misc', 60, 10);
values (1, 'Chair', 'Red', 'Furniture', 50, 10);
values (1, 'Table', 'Black', 'Furniture', 100.0, 10);
values (1, 'Pen', 'Orange', 'Misc', 35, 10);*/

create table Customer(
Customer_ID int not null AUTO_INCREMENT,
Customer_UserName varchar(50) not null unique,
First_Name varchar(30) not null,
Last_Name varchar(30) not null,
Email varchar(30) not null,
Address varchar(50) not null,
Credit_Card_Details varchar(50) not null,
Password varchar(20) not null,
constraint Customer_PK primary key (Customer_ID)
);

insert into Customer (Customer_UserName, First_Name, Last_Name, Email, Address, Credit_Card_Details ,Password)
values ('testuser', 'john', 'smith', 'johnsmith@email.com', '123 john street', '123456789 123' ,'password');
values ('testseconduser', 'boris', 'johnson', 'borisjonhson@email.com', '123 boris street', '987654321 123' ,'password');

create table Sale(
Sale_ID int not null AUTO_INCREMENT,
Date date not null,
Status varchar(20) not null,
Customer_ID int references Customer(Customer_ID),
-- contraint Sale_Customer foreign key (Customer_ID) references Customer,
constraint Sale_PK primary key (Sale_ID)
);

insert into Sale (Date, Status, Customer_ID)
values ('2018-11-11', 'Done', 2);

create table Sale_Item(
Quantity_Purchased int not null,
Sale_Price int not null,
Product_ID int references Product(Product_ID),
Sale_ID int references Sale(Sale_ID)
-- contraint Sale_Item_Product foreign key (Product_ID) references Product,
-- constraint Sale foreign key (Sale_ID) references Sale
);

insert into Sale_Item(Quantity_Purchased, Sale_Price, Product_ID, Sale_ID)
values (10, 10, 1, 1);



