CREATE TABLE shopuser
(
  userid serial NOT NULL,
  email character varying(255),
  password character varying(20) NOT NULL,
  username character varying(20) NOT NULL,
  CONSTRAINT shopuser_pkey PRIMARY KEY (userid)
)

CREATE TABLE order_product
(
  orders_orderid integer NOT NULL,
  products_productid integer NOT NULL,
  CONSTRAINT fk_4896c64019a44a1f912ffe12f2f FOREIGN KEY (orders_orderid)
      REFERENCES orders (orderid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_4b38f454f8dd4ae5a73ae591684 FOREIGN KEY (products_productid)
      REFERENCES product (productid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE orders
(
  orderid serial NOT NULL,
  orderidentifier integer NOT NULL,
  productamount integer NOT NULL,
  productprice double precision NOT NULL,
  username integer,
  CONSTRAINT orders_pkey PRIMARY KEY (orderid),
  CONSTRAINT fk_bf81cb2bdf724d148de03ddaf17 FOREIGN KEY (username)
      REFERENCES employee (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE product
(
  productid serial NOT NULL,
  name character varying(255),
  price double precision NOT NULL,
  uniqueproductcode character varying(255),
  categoryid integer,
  CONSTRAINT product_pkey PRIMARY KEY (productid),
  CONSTRAINT fk_d62d7293756f4187b8ee724774f FOREIGN KEY (categoryid)
      REFERENCES category (categoryid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE category
(
  categoryid serial NOT NULL,
  categoryname character varying(255),
  CONSTRAINT category_pkey PRIMARY KEY (categoryid)
)
