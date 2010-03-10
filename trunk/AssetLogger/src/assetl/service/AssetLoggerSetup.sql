create table Requestors (RequestorID varchar(255) PRIMARY KEY, FirstName varchar(255), MI varchar(255), LastName varchar(255), Phone varchar(255), Email varchar(255));
create table Users (UserID varchar(255) PRIMARY KEY, Password varchar(255), isAdmin BOOLEAN);
create table Assets (AssetID varchar(255) PRIMARY KEY, Make varchar(255), Model varchar(255), SerialNumber varchar(255), AssetType varchar(255), Description varchar(255));
create table Requests (RequestID INTEGER PRIMARY KEY, RequestedMadeDate Date, RequestedPickupDate Date, RequestedType varchar(255), RequestorID varchar(255), UserID varchar(255));
create table Checkouts (CheckoutID INTEGER PRIMARY KEY, RequestID INTEGER, RecipeantID varchar(255), AssetID varchar(255), RequestedStartDate Date, RequestedEndDate Date, PickupDate Date, ReturnDate Date, UserID varchar(255));
create table CheckoutLog (CheckLogID INTEGER PRIMARY KEY, CheckoutID INTEGER, CheckoutLogDate DATE, UserID varchar(255), Comments varchar(255));
insert into Users values ("Doctor", "Buffalo", 1);
insert into Users values ("user", "password", 0);
