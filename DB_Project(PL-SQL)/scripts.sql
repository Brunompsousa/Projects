/*==============================================================*/
/* DBMS name:      ORACLE Version 10gR2                         */
/* Created on:     13-12-2017 18:17:21                          */
/*==============================================================*/


alter table ANUNCIO
   drop constraint FK_ANUNCIO_CRIA_VENDEDOR;

alter table ANUNCIO
   drop constraint FK_ANUNCIO_E_REPRESE_PRODUTO;

alter table ANUNCIO
   drop constraint FK_ANUNCIO_FAZ_COMPRADO;

alter table ANUNCIO
   drop constraint FK_ANUNCIO_PODE_TER2_DESTAQUE;

alter table AVISO
   drop constraint FK_AVISO_INCLUI_LEILAO;

alter table COMPRA_ATRAVES
   drop constraint FK_COMPRA_A_RELATIONS_ANUNCIO;

alter table COMPRA_ATRAVES
   drop constraint FK_RELATION_RELATIONS_COMPRAD2;

alter table CONTRATO
   drop constraint FK_CONTRATO_RELATIONS_VENDEDOR;

alter table DESTAQUE
   drop constraint FK_DESTAQUE_PODE_TER_ANUNCIO;

alter table ENTREGA
   drop constraint FK_ENTREGA_TEM_PRODUTO;

alter table E_ENVIADO
   drop constraint FK_E_ENVIAD_E_ENVIADO_COMPRADO;

alter table HISTORICO
   drop constraint FK_HISTORIC_ORIGINA2_LEILAO;

alter table LEILAO
   drop constraint FK_LEILAO_RELATIONS_PRODUTO;

alter table LEILAO
   drop constraint FK_LEILAO_RELATIONS_VENDEDOR;

alter table LEILAO
   drop constraint FK_LEILAO_RELATIONS_COMPRADO;

alter table PODE_COMPRAR_ATRAVES
   drop constraint FK_PODE_COM_RELATIONS_COMPRADO;

alter table PODE_COMPRAR_ATRAVES
   drop constraint FK_PODE_COM_RELATIONS_LEILAO;

alter table VENDEDOR
   drop constraint FK_VENDEDOR_RELATIONS_CONTRATO;

drop index E_REPRESENTADO_FK;

drop index CRIA_FK;

drop index FAZ_FK;

drop index PODE_TER2_FK;

drop table ANUNCIO cascade constraints;

drop index INCLUI_FK;

drop table AVISO cascade constraints;

drop table COMPRADOR cascade constraints;

drop index RELATIONSHIP_11_FK;

drop index RELATIONSHIP_10_FK;

drop table COMPRA_ATRAVES cascade constraints;

drop table CONTRATO cascade constraints;

drop index PODE_TER_FK;

drop table DESTAQUE cascade constraints;

drop index TEM_FK;

drop table ENTREGA cascade constraints;

drop table E_ENVIADO cascade constraints;

drop index ORIGINA2_FK;

drop table HISTORICO cascade constraints;

drop index RELATIONSHIP_16_FK;

drop index RELATIONSHIP_15_FK;

drop index RELATIONSHIP_14_FK;

drop table LEILAO cascade constraints;

drop index RELATIONSHIP_18_FK;

drop index RELATIONSHIP_17_FK;

drop table PODE_COMPRAR_ATRAVES cascade constraints;

drop table PRODUTO cascade constraints;

drop index RELATIONSHIP_12_FK;

drop table VENDEDOR cascade constraints;

/*==============================================================*/
/* Table: ANUNCIO                                               */
/*==============================================================*/
create table ANUNCIO  (
   ID_ANUNCIO           NUMBER                          not null,
   ID_DESTAQUE          NUMBER,
   ID_VENDEDOR          NUMBER                          not null,
   ID_PRODUTO           NUMBER                          not null,
   ID_COMPRADOR         NUMBER                          not null,
   DATA_INICIAL         DATE                            not null,
   DATA_FINAL           DATE,
   QUANTIDADE           NUMBER                         default 1 not null,
   LOCALIZACAO          VARCHAR2(100)                   not null,
   PORTES               NUMBER                          not null,
   TAXAS                NUMBER,
   PRECO_CV             NUMBER                          not null,
   TIPO_ANUNCIO         NUMBER(1)                      default 0 not null,
   constraint PK_ANUNCIO primary key (ID_ANUNCIO)
);

/*==============================================================*/
/* Index: PODE_TER2_FK                                          */
/*==============================================================*/
create index PODE_TER2_FK on ANUNCIO (
   ID_DESTAQUE ASC
);

/*==============================================================*/
/* Index: FAZ_FK                                                */
/*==============================================================*/
create index FAZ_FK on ANUNCIO (
   ID_COMPRADOR ASC
);

/*==============================================================*/
/* Index: CRIA_FK                                               */
/*==============================================================*/
create index CRIA_FK on ANUNCIO (
   ID_VENDEDOR ASC
);

/*==============================================================*/
/* Index: E_REPRESENTADO_FK                                     */
/*==============================================================*/
create index E_REPRESENTADO_FK on ANUNCIO (
   ID_PRODUTO ASC
);

/*==============================================================*/
/* Table: AVISO                                                 */
/*==============================================================*/
create table AVISO  (
   ID_LEILAO            NUMBER                          not null
);

/*==============================================================*/
/* Index: INCLUI_FK                                             */
/*==============================================================*/
create index INCLUI_FK on AVISO (
   ID_LEILAO ASC
);

/*==============================================================*/
/* Table: COMPRADOR                                             */
/*==============================================================*/
create table COMPRADOR  (
   ID_COMPRADOR         NUMBER                          not null,
   NOME                 VARCHAR2(100)                   not null,
   MORADA               VARCHAR2(150)                   not null,
   CONTRIBUINTE         NUMBER(9)                       not null,
   ESTADO_EMPRESARIAL   NUMBER(1)                      default 0 not null,
   MAIL                 VARCHAR2(50)                    not null,
   CONTACTO             NUMBER(9)                       not null,
   COMISSAO_BASE        NUMBER                         default 0.2 not null,
   constraint PK_COMPRADOR primary key (ID_COMPRADOR)
);

/*==============================================================*/
/* Table: COMPRA_ATRAVES                                        */
/*==============================================================*/
create table COMPRA_ATRAVES  (
   ID_ANUNCIO           NUMBER                          not null,
   ID_COMPRADOR         NUMBER                          not null,
   constraint PK_COMPRA_ATRAVES primary key (ID_ANUNCIO, ID_COMPRADOR)
);

/*==============================================================*/
/* Index: RELATIONSHIP_10_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_10_FK on COMPRA_ATRAVES (
   ID_ANUNCIO ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_11_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_11_FK on COMPRA_ATRAVES (
   ID_COMPRADOR ASC
);

/*==============================================================*/
/* Table: CONTRATO                                              */
/*==============================================================*/
create table CONTRATO  (
   ID_CONTRATO          NUMBER                          not null,
   ID_VENDEDOR          NUMBER                          not null,
   COMISSAO_CONTRATO    NUMBER                          not null,
   constraint PK_CONTRATO primary key (ID_CONTRATO)
);

/*==============================================================*/
/* Table: DESTAQUE                                              */
/*==============================================================*/
create table DESTAQUE  (
   DATA_INICIAL         DATE                            not null,
   DATA_FINAL           DATE                            not null,
   ID_DESTAQUE          NUMBER                          not null,
   ID_ANUNCIO           NUMBER                          not null,
   constraint PK_DESTAQUE primary key (ID_DESTAQUE)
);

/*==============================================================*/
/* Index: PODE_TER_FK                                           */
/*==============================================================*/
create index PODE_TER_FK on DESTAQUE (
   ID_ANUNCIO ASC
);

/*==============================================================*/
/* Table: ENTREGA                                               */
/*==============================================================*/
create table ENTREGA  (
   ID_PRODUTO           NUMBER                          not null,
   DATA_PREVISTA        DATE                            not null,
   DATA_REAL            DATE                            not null,
   TIPO_ENVIO           VARCHAR2(50)                    not null
);

/*==============================================================*/
/* Index: TEM_FK                                                */
/*==============================================================*/
create index TEM_FK on ENTREGA (
   ID_PRODUTO ASC
);

/*==============================================================*/
/* Table: E_ENVIADO                                             */
/*==============================================================*/
create table E_ENVIADO  (
   ID_COMPRADOR         NUMBER                          not null,
   constraint PK_E_ENVIADO primary key (ID_COMPRADOR)
);

/*==============================================================*/
/* Table: HISTORICO                                             */
/*==============================================================*/
create table HISTORICO  (
   ID_LEILAO            NUMBER                          not null
);

/*==============================================================*/
/* Index: ORIGINA2_FK                                           */
/*==============================================================*/
create index ORIGINA2_FK on HISTORICO (
   ID_LEILAO ASC
);

/*==============================================================*/
/* Table: LEILAO                                                */
/*==============================================================*/
create table LEILAO  (
   PRECO_COMPRA         NUMBER                          not null,
   PRECO_INICIAL        NUMBER                          not null,
   PRECO_FINAL          NUMBER                          not null,
   TIPO_LEILAO          NUMBER(1)                      default 0 not null,
   ID_LEILAO            NUMBER                          not null,
   ID_VENDEDOR          NUMBER                          not null,
   ID_PRODUTO           NUMBER                          not null,
   QUANTIDADE           NUMBER                         default 1,
   TIPO_PORTES          VARCHAR2(100),
   DATA_INICIAL         DATE                            not null,
   DATA_FINAL           DATE,
   ID_COMPRADOR         NUMBER                          not null,
   constraint PK_LEILAO primary key (ID_LEILAO)
);

/*==============================================================*/
/* Index: RELATIONSHIP_14_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_14_FK on LEILAO (
   ID_PRODUTO ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_15_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_15_FK on LEILAO (
   ID_VENDEDOR ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_16_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_16_FK on LEILAO (
   ID_COMPRADOR ASC
);

/*==============================================================*/
/* Table: PODE_COMPRAR_ATRAVES                                  */
/*==============================================================*/
create table PODE_COMPRAR_ATRAVES  (
   ID_COMPRADOR         NUMBER                          not null,
   ID_LEILAO            NUMBER                          not null,
   constraint PK_PODE_COMPRAR_ATRAVES primary key (ID_COMPRADOR, ID_LEILAO)
);

/*==============================================================*/
/* Index: RELATIONSHIP_17_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_17_FK on PODE_COMPRAR_ATRAVES (
   ID_COMPRADOR ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_18_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_18_FK on PODE_COMPRAR_ATRAVES (
   ID_LEILAO ASC
);

/*==============================================================*/
/* Table: PRODUTO                                               */
/*==============================================================*/
create table PRODUTO  (
   ID_PRODUTO           NUMBER                          not null,
   NOME                 VARCHAR2(100)                   not null,
   DESCRICAO            VARCHAR2(500)                   not null,
   STOCK                NUMBER                          not null,
   constraint PK_PRODUTO primary key (ID_PRODUTO)
);

/*==============================================================*/
/* Table: VENDEDOR                                              */
/*==============================================================*/
create table VENDEDOR  (
   ID_VENDEDOR          NUMBER                          not null,
   ID_CONTRATO          NUMBER,
   NOME                 VARCHAR2(100)                   not null,
   MORADA               VARCHAR2(150)                   not null,
   MAIL                 VARCHAR2(50)                    not null,
   CONTACTO             NUMBER(9)                       not null,
   CONTRIBUINTE         NUMBER(9)                       not null,
   CERTIFICADO          NUMBER(1)                      default 0,
   COMISSAO_BASE        NUMBER                         default 0.2,
   constraint PK_VENDEDOR primary key (ID_VENDEDOR)
);

/*==============================================================*/
/* Index: RELATIONSHIP_12_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_12_FK on VENDEDOR (
   ID_CONTRATO ASC
);

alter table ANUNCIO
   add constraint FK_ANUNCIO_CRIA_VENDEDOR foreign key (ID_VENDEDOR)
      references VENDEDOR (ID_VENDEDOR);

alter table ANUNCIO
   add constraint FK_ANUNCIO_E_REPRESE_PRODUTO foreign key (ID_PRODUTO)
      references PRODUTO (ID_PRODUTO);

alter table ANUNCIO
   add constraint FK_ANUNCIO_FAZ_COMPRADO foreign key (ID_COMPRADOR)
      references COMPRADOR (ID_COMPRADOR);

alter table ANUNCIO
   add constraint FK_ANUNCIO_PODE_TER2_DESTAQUE foreign key (ID_DESTAQUE)
      references DESTAQUE (ID_DESTAQUE);

alter table AVISO
   add constraint FK_AVISO_INCLUI_LEILAO foreign key (ID_LEILAO)
      references LEILAO (ID_LEILAO);

alter table COMPRA_ATRAVES
   add constraint FK_COMPRA_A_RELATIONS_ANUNCIO foreign key (ID_ANUNCIO)
      references ANUNCIO (ID_ANUNCIO);

alter table COMPRA_ATRAVES
   add constraint FK_RELATION_RELATIONS_COMPRAD2 foreign key (ID_COMPRADOR)
      references COMPRADOR (ID_COMPRADOR);

alter table CONTRATO
   add constraint FK_CONTRATO_RELATIONS_VENDEDOR foreign key (ID_VENDEDOR)
      references VENDEDOR (ID_VENDEDOR);

alter table DESTAQUE
   add constraint FK_DESTAQUE_PODE_TER_ANUNCIO foreign key (ID_ANUNCIO)
      references ANUNCIO (ID_ANUNCIO);

alter table ENTREGA
   add constraint FK_ENTREGA_TEM_PRODUTO foreign key (ID_PRODUTO)
      references PRODUTO (ID_PRODUTO);

alter table E_ENVIADO
   add constraint FK_E_ENVIAD_E_ENVIADO_COMPRADO foreign key (ID_COMPRADOR)
      references COMPRADOR (ID_COMPRADOR);

alter table HISTORICO
   add constraint FK_HISTORIC_ORIGINA2_LEILAO foreign key (ID_LEILAO)
      references LEILAO (ID_LEILAO);

alter table LEILAO
   add constraint FK_LEILAO_RELATIONS_PRODUTO foreign key (ID_PRODUTO)
      references PRODUTO (ID_PRODUTO);

alter table LEILAO
   add constraint FK_LEILAO_RELATIONS_VENDEDOR foreign key (ID_VENDEDOR)
      references VENDEDOR (ID_VENDEDOR);

alter table LEILAO
   add constraint FK_LEILAO_RELATIONS_COMPRADO foreign key (ID_COMPRADOR)
      references COMPRADOR (ID_COMPRADOR);

alter table PODE_COMPRAR_ATRAVES
   add constraint FK_PODE_COM_RELATIONS_COMPRADO foreign key (ID_COMPRADOR)
      references COMPRADOR (ID_COMPRADOR);

alter table PODE_COMPRAR_ATRAVES
   add constraint FK_PODE_COM_RELATIONS_LEILAO foreign key (ID_LEILAO)
      references LEILAO (ID_LEILAO);

alter table VENDEDOR
   add constraint FK_VENDEDOR_RELATIONS_CONTRATO foreign key (ID_CONTRATO)
      references CONTRATO (ID_CONTRATO);

