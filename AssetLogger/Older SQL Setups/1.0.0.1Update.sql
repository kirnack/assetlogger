vr1.0.0.1
vr1.0.0.0
create table temp (AssetID varchar(255) PRIMARY KEY, Make varchar(255), Model varchar(255), SerialNumber varchar(255), AssetType varchar(255), Description varchar(255), inMaintenance boolean);
insert into temp (AssetID, Make, Model, SerialNumber, AssetType, Description) select * from Assets;
drop table Assets;
alter table temp rename to Assets;
delete from DatabaseInfo;
insert into DatabaseInfo values (1, "1.0.0.1");
