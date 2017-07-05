INSERT INTO Brands(title) VALUES ('Samsung');
INSERT INTO Brands(title) VALUES ('LG');
INSERT INTO Brands(title) VALUES ('Bosch');
INSERT INTO Brands(title) VALUES ('Hotpoint');
INSERT INTO Brands(title) VALUES ('Sony');

INSERT INTO Types(title) VALUES ('Телевизор');
INSERT INTO Types(title) VALUES ('Холодильник');
INSERT INTO Types(title) VALUES ('Стиральная машина');

INSERT INTO Features(title) VALUES ('Диагональ');
INSERT INTO Features(title) VALUES ('Толщина');
INSERT INTO Features(title) VALUES ('Тип экрана');
INSERT INTO Features(title) VALUES ('Наличие морозильной камеры');
INSERT INTO Features(title) VALUES ('Габариты');
INSERT INTO Features(title) VALUES ('Тип загрузки одежды');

INSERT INTO Products(type_id, brand_id, title, count, price) VALUES (1, 1, 'Q9F 4K Smart QLED TV', 12, 399990);
INSERT INTO Products(type_id, brand_id, title, count, price) VALUES (2, 2, 'GA-B409 UEQA', 28, 28469);
INSERT INTO Products(type_id, brand_id, title, count, price) VALUES (2, 1, 'RB-30 J3200SS', 3, 28940);
INSERT INTO Products(type_id, brand_id, title, count, price) VALUES (1, 5, 'KDL-49WD759', 8, 40290);
INSERT INTO Products(type_id, brand_id, title, count, price) VALUES (3, 3, 'WLG 20060', 19, 15005);

INSERT INTO Product_Features(product_id, feature_id, value) VALUES (1, 1, '65 дюймов');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (1, 2, '8см');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (1, 3, 'LED');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (2, 4, 'Есть');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (3, 4, 'Есть');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (4, 1, '55 дюймов');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (4, 2, '7см');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (4, 3, 'Плазменный (ЖК)');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (5, 5, '60x50x40');
INSERT INTO Product_Features(product_id, feature_id, value) VALUES (5, 6, 'Фронтальная загрузка');