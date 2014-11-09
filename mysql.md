Create database:

    CREATE DATABASE `db_name` CHARACTER SET utf8 COLLATE utf8_general_ci;

Create backup:

    mysqldump -udb_user -pdb_pass --single-transaction --disable-keys db_name > ./dump-$(date +%y.%m.%d).sql;

Whre: db_user and db_pass - credentials and db_name - target database
