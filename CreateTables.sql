DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS machine_item;
DROP TABLE IF EXISTS machines;
DROP TABLE IF EXISTS items;

CREATE TABLE items (
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(30),
price DECIMAL(10, 2),
type VARCHAR(30),
calories INT,
sugar INT,
info VARCHAR(30),
picture VARCHAR(30),
lastmod DATETIME
);

CREATE TABLE machines (
ID INT NOT NULL PRIMARY KEY,
address VARCHAR(30),
startdate DATETIME,
lastsync DATETIME
);

CREATE TABLE machine_item (
machineid INT NOT NULL,
itemid INT NOT NULL, 
capacity INT,
quantity INT,
PRIMARY KEY (machineid, itemid),
FOREIGN KEY (machineid) REFERENCES machines(ID) ON DELETE CASCADE,
FOREIGN KEY (itemid) REFERENCES items(ID) ON DELETE CASCADE
);

CREATE TABLE sales (
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
machineid INT,
itemid INT,
profit DECIMAL(10, 2),
date DATETIME,
FOREIGN KEY (machineid) REFERENCES machines(ID) ON DELETE CASCADE,
FOREIGN KEY (itemid) REFERENCES items(ID) ON DELETE CASCADE
);

CREATE TABLE employees (
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(30),
password VARCHAR(15),
isManager INT
);

CREATE TABLE cards (
ID INT NOT NULL PRIMARY KEY,
balance DECIMAL(10, 2)
);

insert into items values (1, "Coke", 1, "drink", 182, 44, "itemsinfo/item_1.html", "itemspic/item_1.jpeg", "2015-07-30 00:00:00");
insert into items values (2, "Sprite", 1, "drink", 192, 44, "itemsinfo/item_2.html", "itemspic/item_2.jpeg", "2015-07-30 00:00:00");
insert into items values (3, "Lemonade", 1.5, "drink", 99, 25, "itemsinfo/item_3.html", "itemspic/item_3.jpeg", "2015-07-30 00:00:00");
insert into items values (4, "Orange juice", 1.5, "drink", 39, 7, "itemsinfo/item_4.html", "itemspic/item_4.jpeg", "2015-07-30 00:00:00");
insert into items values (5, "Water", 1, "drink", 0, 0, "itemsinfo/item_5.html", "itemspic/item_5.jpeg", "2015-07-30 00:00:00");
insert into items values (6, "Diet coke", 1, "drink", 1, 0, "itemsinfo/item_6.html", "itemspic/item_6.jpeg", "2015-07-30 00:00:00");
insert into items values (7, "Oreo", 1, "snack", 270, 23, "itemsinfo/item_7.html", "itemspic/item_7.jpeg", "2015-07-30 00:00:00");
insert into items values (8, "Candy", 1, "snack", 234, 24, "itemsinfo/item_8.html", "itemspic/item_8.jpeg", "2015-07-30 00:00:00");
insert into items values (9, "Chips", 1, "snack", 160, 1, "itemsinfo/item_9.html", "itemspic/item_9.jpeg", "2015-07-30 00:00:00");
insert into items values (10, "Energy bar", 1, "snack", 235, 22, "itemsinfo/item_10.html", "itemspic/item_10.jpeg", "2015-07-30 00:00:00");
insert into items values (11, "Chocolate", 1, "snack", 155, 14, "itemsinfo/item_11.html", "itemspic/item_11.jpeg", "2015-07-30 00:00:00");

insert into employees values (1, "Sam", "one", 1);
insert into employees values (2, "Tom", "two", 0);

insert into machines values(1, "San Jose", "2015-07-30 00:00:00", "2015-08-23 00:00:00");
insert into machines values(2, "San Jose", "2015-07-31 00:00:00", "2015-08-23 00:00:00");
insert into machines values(3, "San Jose", "2015-08-01 00:00:00", "2015-08-23 00:00:00");
insert into machines values(4, "San Jose", "2015-08-02 00:00:00", "2015-08-23 00:00:00");
insert into machines values(5, "San Jose", "2015-08-03 00:00:00", "2015-08-23 00:00:00");

insert into machine_item values(1, 1, 10, 5);
insert into machine_item values(1, 2, 10, 3);
insert into machine_item values(1, 3, 10, 4);
insert into machine_item values(1, 7, 10, 5);
insert into machine_item values(1, 8, 10, 2);
insert into machine_item values(1, 9, 10, 1);

insert into sales values(1, 1, 1, 20, "2015-07-30 00:00:00");
insert into sales values(2, 1, 2, 30, "2015-07-30 00:00:00");
insert into sales values(3, 1, 3, 45, "2015-07-30 00:00:00");
insert into sales values(4, 1, 7, 10, "2015-07-30 00:00:00");
insert into sales values(5, 1, 8, 14, "2015-07-30 00:00:00");
insert into sales values(6, 1, 9, 8, "2015-07-30 00:00:00");

insert into machine_item values(2, 2, 10, 4);
insert into machine_item values(2, 3, 10, 6);
insert into machine_item values(2, 4, 10, 9);
insert into machine_item values(2, 8, 10, 7);
insert into machine_item values(2, 9, 10, 8);
insert into machine_item values(2, 10, 10, 10);

insert into sales values(1, 2, 2, 10, "2015-07-30 00:00:00");
insert into sales values(2, 2, 3, 16, "2015-07-30 00:00:00");
insert into sales values(3, 2, 4, 6, "2015-07-30 00:00:00");
insert into sales values(4, 2, 8, 25, "2015-07-30 00:00:00");
insert into sales values(5, 2, 9, 8, "2015-07-30 00:00:00");
insert into sales values(5, 2, 10, 1, "2015-07-30 00:00:00");

insert into machine_item values(3, 1, 10, 6);
insert into machine_item values(3, 4, 10, 7);
insert into machine_item values(3, 5, 10, 3);
insert into machine_item values(3, 7, 10, 2);
insert into machine_item values(3, 8, 10, 8);
insert into machine_item values(3, 11, 10, 5);

insert into sales values(1, 3, 1, 40, "2015-07-30 00:00:00");
insert into sales values(2, 3, 4, 32, "2015-07-30 00:00:00");
insert into sales values(3, 3, 5, 50, "2015-07-30 00:00:00");
insert into sales values(4, 3, 7, 48, "2015-07-30 00:00:00");
insert into sales values(5, 3, 8, 20, "2015-07-30 00:00:00");
insert into sales values(6, 3, 11, 18, "2015-07-30 00:00:00");

insert into machine_item values(4, 3, 10, 3);
insert into machine_item values(4, 4, 10, 2);
insert into machine_item values(4, 5, 10, 1);
insert into machine_item values(4, 9, 10, 2);
insert into machine_item values(4, 10, 10, 1);
insert into machine_item values(4, 11, 10, 3);

insert into sales values(1, 4, 3, 60, "2015-07-30 00:00:00");
insert into sales values(2, 4, 4, 52, "2015-07-30 00:00:00");
insert into sales values(3, 4, 5, 70, "2015-07-30 00:00:00");
insert into sales values(4, 4, 9, 68, "2015-07-30 00:00:00");
insert into sales values(5, 4, 10, 40, "2015-07-30 00:00:00");
insert into sales values(6, 4, 11, 38, "2015-07-30 00:00:00");

insert into machine_item values(5, 2, 10, 8);
insert into machine_item values(5, 3, 10, 6);
insert into machine_item values(5, 5, 10, 5);
insert into machine_item values(5, 6, 10, 7);
insert into machine_item values(5, 8, 10, 4);
insert into machine_item values(5, 10, 10, 10);

insert into sales values(1, 5, 2, 20, "2015-07-30 00:00:00");
insert into sales values(2, 5, 3, 12, "2015-07-30 00:00:00");
insert into sales values(3, 5, 5, 30, "2015-07-30 00:00:00");
insert into sales values(4, 5, 6, 18, "2015-07-30 00:00:00");
insert into sales values(5, 5, 8, 8, "2015-07-30 00:00:00");
insert into sales values(6, 5, 10, 2, "2015-07-30 00:00:00");
