/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     16/08/15 22:49:39                            */
/*==============================================================*/


drop table if exists buyer;

drop table if exists order_product;

drop table if exists product;

drop table if exists seller;

drop table if exists tb_order;

drop table if exists wechat;

/*==============================================================*/
/* Table: buyer                                                 */
/*==============================================================*/
create table buyer
(
   buyer_id             bigint not null auto_increment,
   buyer_name           varchar(50),
   phone                varchar(20),
   email                varchar(50),
   gender               bool,
   age                  smallint,
   birthday             date,
   state                smallint,
   add_time             datetime,
   wechat_id            bigint,
   primary key (buyer_id)
);

alter table buyer comment '购买者信息';



/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product
(
   product_id           bigint not null auto_increment,
   product_name         varchar(200),
   serial_num           varchar(20),
   type                 smallint,
   discount             smallint,
   price                bigint,
   add_time             datetime,
   expire_time          datetime,
   comment              varchar(1024),
   product_url          varchar(1024),
   primary key (product_id)
);

alter table product comment '产品信息';

/*==============================================================*/
/* Table: seller                                                */
/*==============================================================*/
create table seller
(
   seller_id            bigint not null auto_increment,
   wechat_id            bigint,
   seller_name          varchar(100),
   phone                varchar(20),
   email                varchar(50),
   state                smallint,
   seller_url           varchar(1024),
   primary key (seller_id)
);

alter table seller comment '微商或供应商信息';

/*==============================================================*/
/* Table: tb_order                                              */
/*==============================================================*/
create table tb_order
(
   order_id             bigint not null auto_increment,
   order_time           datetime,
   money                int,
   state                smallint,
   purchase_time        datetime,
   send_time            datetime,
   buyer_id             bigint,
   seller_id            bigint,
   primary key (order_id)
);

alter table tb_order comment '订单信息';

/*==============================================================*/
/* Table: wechat                                                */
/*==============================================================*/
create table wechat
(
   wechat_id            bigint not null auto_increment,
   wechat_account       varchar(50),
   wechat_name          varchar(50),
   wechat_url           varchar(1024),
   wechat_pic           blob,
   add_time             datetime,
   state                smallint,
   primary key (wechat_id)
);

/*==============================================================*/
/* Table: order_product                                         */
/*==============================================================*/
create table order_product
(
   product_id           bigint not null,
   order_id             bigint not null,
   add_time             datetime,
   state                smallint,
   primary key (product_id, order_id)
);

alter table order_product comment '订单 商品 关联信息表';

alter table wechat comment '微信号信息';

alter table buyer add constraint FK_Relationship_2 foreign key (wechat_id)
      references wechat (wechat_id) on delete restrict on update restrict;

alter table order_product add constraint FK_order_product foreign key (product_id)
      references product (product_id) on delete restrict on update restrict;

alter table order_product add constraint FK_order_product2 foreign key (order_id)
      references tb_order (order_id) on delete restrict on update restrict;

alter table seller add constraint FK_Relationship_1 foreign key (wechat_id)
      references wechat (wechat_id) on delete restrict on update restrict;

alter table tb_order add constraint FK_Reference_6 foreign key (buyer_id)
      references buyer (buyer_id) on delete restrict on update restrict;

alter table tb_order add constraint FK_Reference_7 foreign key (seller_id)
      references seller (seller_id) on delete restrict on update restrict;

