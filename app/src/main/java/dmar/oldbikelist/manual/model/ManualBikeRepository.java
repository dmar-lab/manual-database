package dmar.oldbikelist.manual.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dmar.oldbikelist.manual.ManualDatabaseHelper;

public class ManualBikeRepository {

    public static List<Bike> findAll(Context context) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        // change raw query to appropriate methods
        Cursor cursor = db.rawQuery("select * from bikes", null);

        List<Bike> bikeList = new ArrayList<>();
        // cursor contains many instances of Bike
        while (cursor.moveToNext()) {
            Bike bike = new Bike();
            bike.setId(cursor.getLong(0));
            bike.setBikeNo(cursor.getString(1));
            bike.setSecurityCode(cursor.getString(2));
            bike.setOther1(cursor.getString(3));
            bike.setOther2(cursor.getString(4));
            bikeList.add(bike);
        }
        db.close();
        return bikeList;
    }

    public static Bike findById(Context context, long bikeId) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from bikes where id = ?",
                new String[]{Long.toString(bikeId)});
        Bike bike = null;
        if (cursor.moveToNext()) {
            bike = new Bike();
            bike.setId(cursor.getLong(0));
            bike.setBikeNo(cursor.getString(1));
            bike.setSecurityCode(cursor.getString(2));
            bike.setOther1(cursor.getString(3));
            bike.setOther2(cursor.getString(4));
        }
        db.close();
        return bike;
    }

    public static void addBike(Context context, Bike bike) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql="insert into bikes(bike_no, security_code) values (?, ?)";
        db.execSQL(sql, new String[]{bike.getBikeNo(), bike.getSecurityCode()});
        db.close();
    }

    public static void updateBike(Context context, Bike bike) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bike_no", bike.getBikeNo());
        values.put("security_code", bike.getSecurityCode());
        values.put("other1", bike.getOther1());
        values.put("other2",bike.getOther2());
        int result = db.update("bikes", values, "id = ?",
                new String[]{Long.toString(bike.getId())});
        //see logs, what happened
        Log.d("update count", "update count: " + result);
        db.close();
    }

    public static void deleteBike(Context context, Bike bike) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int result = db.delete("bikes", "id = ?",
                new String[]{Long.toString(bike.getId())});
        Log.d("delete count", "delete count: " + result);
        db.close();
    }
}
