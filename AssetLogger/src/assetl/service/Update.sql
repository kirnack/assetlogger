1.0.0.1
create table temp (AssetID varchar(255) PRIMARY KEY, Make varchar(255), Model varchar(255), SerialNumber varchar(255), AssetType varchar(255), Description varchar(255), inMaintenan boolean);
insert into temp (AssetIDPRIMARYKEY,MakeModelSerialNumberAssetTypeDescription);
drop tableAssets;
alter table temp rename to Assets;
delete * from values;
insert into DatabaseInfo values (1, "1.0.0.1");
