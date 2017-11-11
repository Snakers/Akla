package com.shatterplay.snakers.akla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by snakers on 10/18/2017.
 */

public class DatabaseForAkla extends SQLiteOpenHelper {
    public  int idl;
   private static DatabaseForAkla databaseForAkla;
private static Context context;
    public Users users;

    public static DatabaseForAkla databaseForAkla(){
if(databaseForAkla ==null){
    databaseForAkla = new DatabaseForAkla(context);
}

 return databaseForAkla;
    }

    public static long rawid;
private static ArrayList<Users> userses;

    public DatabaseForAkla(Context context) {

        super(context, "MyDatabase.db", null, 1);
        DatabaseForAkla.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//String userTable = "CREATE TABLE customer( id INTEGER, firstName TEXT, middleName TEXT, lastName   TEXT, address TEXT,contactNum TEXT)";
String users ="CREATE TABLE users ( idusers INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,address TEXT NOT NULL,phone TEXT NOT NULL, Date TEXT NOT NULL, email TEXT unique NOT NULL,  imageUrl TEXT NULL)";
        //String order =" CREATE TABLE order (idorder LONG NOT NULL, idusers LONG NOT NULL )";

  //      String order = "CREATE TABLE IF NOT EXISTS `MyDatabase`.`order` (`idtable2` INT NOT NULL,`date` DATETIME NULL,`users_idusers` INT NOT NULL, PRIMARY KEY (`idtable2`), INDEX `fk_order_users_idx` (`users_idusers` ASC),CONSTRAINT `fk_order_users`FOREIGN KEY (`users_idusers`)REFERENCES `MyDatabase`.`users` (`idusers`)ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB";
//String items = "CREATE TABLE IF NOT EXISTS `MyDatabase`.`items` (`iditems` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`price` DOUBLE NOT NULL, PRIMARY KEY (`iditems`))ENGINE = InnoDB";
//String orderdetails = "CREATE TABLE IF NOT EXISTS `MyDatabase`.`orderdetails` (`order_idtable2` INT NOT NULL,`items_iditems` INT NOT NULL,`quantity` INT NOT NULL, PRIMARY KEY (`order_idtable2`, `items_iditems`), INDEX `fk_order_has_items_items1_idx` (`items_iditems` ASC),INDEX `fk_order_has_items_order1_idx` (`order_idtable2` ASC),CONSTRAINT `fk_order_has_items_order1`FOREIGN KEY (`order_idtable2`)REFERENCES `MyDatabase`.`order` (`idtable2`)ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT `fk_order_has_items_items1`FOREIGN KEY (`items_iditems`)REFERENCES `MyDatabase`.`items` (`iditems`)ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB";
//db.execSQL(order);
db.execSQL(users);
//        db.execSQL(order);
//        db.execSQL(items);
//db.execSQL(orderdetails);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
       // db.execSQL("DROP TABLE IF EXISTS order");

        //   db.execSQL("DROP TABLE IF EXISTS order");
   //     db.execSQL("DROP TABLE IF EXISTS items");
 //       db.execSQL("DROP TABLE IF EXISTS orderdetails");
        onCreate(db);


    }
    public void insertUsers(Users users){
        ContentValues contentValues = new ContentValues();
      //contentValues.put("idusers",1);
        contentValues.put("name",users.getName());
        contentValues.put("imageUrl",users.getImageurl());
        contentValues.put("phone",users.getPhone());
        contentValues.put("address",users.getAddress());
        contentValues.put("email",users.getEmail());
        contentValues.put("Date",new Date().getTime());
        SQLiteDatabase db = getWritableDatabase();
       rawid= db.insert("users",null,contentValues);
        db.close();

    }

    public  ArrayList<Users> insertUsers(){
ArrayList<Users> usersList = new ArrayList<>();
        String query = "SELECT * FROM users";
 SQLiteDatabase   db = getWritableDatabase();

        Cursor cursor = db.rawQuery(query,null);
 try {
     if (cursor.moveToFirst()) {
         do {
            int id = cursor.getInt(0);
             String name = cursor.getString(1);
             String address = cursor.getString(2);
             String phone = cursor.getString(3);
             String date = cursor.getString(4);
             String email = cursor.getString(5);
             String imageUrl = cursor.getString(6);

            users= new Users(id,name, phone, address, email, imageUrl, date);
//users.setId(cursor.getInt(cursor.getColumnIndex("idusers")));
//users.setName(cursor.getString(cursor.getColumnIndex("name")));
//        users.setAddress(cursor.getString(cursor.getColumnIndex("address")));
//        users.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
//        users.setPhone(cursor.getString(cursor.getColumnIndex("email")));
//        users.setDate(cursor.getString(cursor.getColumnIndex("Date")));
//users.setImageurl(cursor.getString(cursor.getColumnIndex("imageUrl")));

             usersList.add(users);
             App.getInstance().setUsers(users);
             idl = users.getId();
             Log.v("HELLO", String.valueOf(idl));
         } while (cursor.moveToNext());
     }
 }finally {
     db.close();
     cursor.close();
 }

//    public void insertOrders(Users users) {
//    Conte}
userses=usersList;
return usersList;


    }
}