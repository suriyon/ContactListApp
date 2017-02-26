package cs.naosuke.contactlistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NAOSUKE on 2/26/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mycontactdb.sqlite";
    private static final String TABLE_NAME = "contact";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "(" +
                "id integer primary key autoincrement," +
                "name text, phone text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public long addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName() );
        values.put("phone", contact.getPhone());
        long row =  db.insert(TABLE_NAME, null, values);
        db.close();
        return row;
    }

    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));

                contacts.add(contact);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  contacts;
    }

    public ArrayList<Contact> getContactByName(String name){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + TABLE_NAME + " where name like '%" +
                name + "%' order by name;";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));

                contacts.add(contact);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  contacts;
    }

    public int updateContact(Contact contact){
        int row = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());

        row = db.update(TABLE_NAME, values, " where id = " + contact.getId(), null);

        db.close();
        return row;
    }
}
