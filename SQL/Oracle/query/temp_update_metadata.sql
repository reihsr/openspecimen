DELETE FROM DYEXTN_STRING_TYPE_INFO WHERE identifier IN (308,321,315);

INSERT INTO DYEXTN_FILE_TYPE_INFO (Identifier) VALUES(308);

INSERT INTO DYEXTN_FILE_TYPE_INFO (Identifier) VALUES(321);

INSERT INTO DYEXTN_FILE_TYPE_INFO (Identifier) VALUES(315);

INSERT INTO DYEXTN_TAGGED_VALUE VALUES ((SELECT MAX(identifier) FROM DYEXTN_TAGGED_VALUE)+1,'MetadataEntityGroup', 'MetadataEntityGroup', 1);

 
INSERT INTO DYEXTN_TAGGED_VALUE VALUES ((SELECT MAX(identifier) FROM DYEXTN_TAGGED_VALUE)+1,'MetadataEntityGroup', 'MetadataEntityGroup', 1208);

 
INSERT INTO DYEXTN_TAGGED_VALUE VALUES ((SELECT MAX(identifier) FROM DYEXTN_TAGGED_VALUE)+1,'MetadataEntityGroup', 'MetadataEntityGroup', 1226);

 
INSERT INTO DYEXTN_TAGGED_VALUE VALUES ((SELECT MAX(identifier) FROM DYEXTN_TAGGED_VALUE)+1,'MetadataEntityGroup', 'MetadataEntityGroup', 1317);

