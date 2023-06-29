-- TODO: INITIAL DB MIGRATION SCRIPT GOES HERE.
create table "demo" (
    "demoId" varchar(255) not null,
    "createdAt" timestamp,
    "createdBy" varchar(255),
    "lastModifiedAt" timestamp,
    "lastModifiedBy" varchar(255),
    "status" varchar(255),
    name varchar(255),
    number integer,
    primary key ("demoId")
);
