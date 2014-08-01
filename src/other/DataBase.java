package other;

import java.io.File;

import org.andengine.util.debug.Debug;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	static final String dbName = "myDB";
//	static final String tLevels = "Levels";
//	static final String fLevelID = "levelNum";
//	static final String fLevelUnLocked = "levelLocked";
//	static final String fLevelBeat = "levelBeat";
//	static final String fLevelScore = "levelScore";
	static final String Object = "object";
	static final String valueX = "x";
	static final String valueY = "y";
	static final String type = "Type";
	static final String ID = "idNum";
	static final String houseLevel ="houseLevel";
	static final String string1 = "string1";
	static final String string2 = "string2";
	static final String string3 = "string3";
	public DataBase(Context context) {
// THE VALUE OF 1 ON THE NEXT LINE REPRESENTS THE VERSION NUMBER OF THE DATABASE
// IN THE FUTURE IF YOU MAKE CHANGES TO THE DATABASE, YOU NEED TO INCREMENT THIS NUMBER
// DOING SO WILL CAUSE THE METHOD onUpgrade() TO AUTOMATICALLY GET TRIGGERED
		super(context, dbName, null, 4);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
// ESTABLISH NEW DATABASE TABLES IF THEY DON'T ALREADY EXIST IN THE DATABASE
//		db.execSQL("CREATE TABLE IF NOT EXISTS "+tLevels+" (" +
//					fLevelID + " INTEGER PRIMARY KEY , " +
//					fLevelUnLocked + " TEXT, " +
//					fLevelBeat + " TEXT, " +
//					fLevelScore + " TEXT" + 
//					")");
		db.execSQL("CREATE TABLE IF NOT EXISTS "+Object+" (" +
				ID +" INTEGER PRIMARY KEY , " +
				type + " TEXT, " +
				valueX + " TEXT , " +
				valueY + " TEXT , " +
				houseLevel + " TEXT , " +
				string1 + " TEXT , " +
				string2 + " TEXT , " +
				string3 + " TEXT " +
				")");
	
//// OPTIONALLY PREPOPULATE THE TABLE WITH SOME VALUES	
//		 ContentValues cv = new ContentValues();
//			cv.put(fLevelID, 1);
//			cv.put(fLevelUnLocked, "true");
//			cv.put(fLevelBeat, "false");
//			cv.put(fLevelScore, "0");
//				db.insert(tLevels, null, cv);
//			cv.put(fLevelID, 2);
//			cv.put(fLevelUnLocked, "false");
//			cv.put(fLevelBeat, "false");
//			cv.put(fLevelScore, "0");
//				db.insert(tLevels, null, cv);
//			cv.put(fLevelID, 3);
//			cv.put(fLevelUnLocked, "false");
//			cv.put(fLevelBeat, "false");
//			cv.put(fLevelScore, "0");
//				db.insert(tLevels, null, cv);
					
/*		
 * MORE ADVANCED EXAMPLES OF USAGE
 * 
		db.execSQL("CREATE TRIGGER fk_empdept_deptid " +
				" BEFORE INSERT "+
				" ON "+employeeTable+				
				" FOR EACH ROW BEGIN"+
				" SELECT CASE WHEN ((SELECT "+colDeptID+" FROM "+deptTable+" WHERE "+colDeptID+"=new."+colDept+" ) IS NULL)"+
				" THEN RAISE (ABORT,'Foreign Key Violation') END;"+
				"  END;");
		
		db.execSQL("CREATE VIEW "+viewEmps+
				" AS SELECT "+employeeTable+"."+colID+" AS _id,"+
				" "+employeeTable+"."+colName+","+
				" "+employeeTable+"."+colAge+","+
				" "+deptTable+"."+colDeptName+""+
				" FROM "+employeeTable+" JOIN "+deptTable+
				" ON "+employeeTable+"."+colDept+" ="+deptTable+"."+colDeptID
				);
*/				
	}

	public void add(String name, float x, float y, int id) {
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(ID, id);
		cv.put(type, name);
		cv.put(valueX, x);
		cv.put(valueY, y);
		cv.put(houseLevel, -1);
		cv.put(string1, "-1");
		cv.put(string2, "-1");
		cv.put(string3, "-1");
		myDB.insert(Object, null, cv);
	}public void add(String name, int value, int id) {
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(ID, id);
		cv.put(type, name);
		cv.put(valueX, value);
		cv.put(valueY, -1);
		cv.put(houseLevel, -1);
		cv.put(string1, "-1");
		cv.put(string2, "-1");
		cv.put(string3, "-1");
		myDB.insert(Object, null, cv);
	}
	public void add(String name, float x, float y, int id, int houselevel) {
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(ID, id);
		cv.put(type, name);
		cv.put(valueX, x);
		cv.put(valueY, y);
		cv.put(houseLevel, houselevel);
		cv.put(string1, "-1");
		cv.put(string2, "-1");
		cv.put(string3, "-1");
		myDB.insert(Object, null, cv);
		Debug.e("ID:" + id + "Type" + name + "ValueX " + x +" valueY " + y);
	}
	public void add(String name, float x, float y, int id, String stringone, String stringtwo) {
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(ID, id);
		cv.put(type, name);
		cv.put(valueX, x);
		cv.put(valueY, y);
		cv.put(houseLevel, -1);
		cv.put(string1, stringone);
		cv.put(string2, stringtwo);
		cv.put(string3, "-1");
		myDB.insert(Object, null, cv);
	}
	public void add(String name, float x, float y, int id, String stringone, String stringtwo, String stringthree) {
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(ID, id);
		cv.put(type, name);
		cv.put(valueX, x);
		cv.put(valueY, y);
		cv.put(houseLevel, -1);
		cv.put(string1, stringone);
		cv.put(string2, stringtwo);
		cv.put(string3, stringthree);
		myDB.insert(Object, null, cv);
	}
	public void deleteDataBase(){
		SQLiteDatabase myDB = this.getReadableDatabase();
		myDB.delete(Object, null, null);
		
	}
	public String readDataBaseType(int IDs) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		String[] mySearch = new String[] { String.valueOf(IDs) };
		Cursor myCursor;
		myCursor = myDB.rawQuery("SELECT " + type + " FROM " + Object
				+ " WHERE " + ID + "=?", mySearch);
		myCursor.moveToFirst();
		if(myCursor.moveToFirst()){;
		int index = myCursor.getColumnIndex(type);
		
		String type = myCursor.getString(index);
		return type;
		}else
		return "-1";
	}
	public float readDataBaseX(int IDs) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		
		String[] mySearch = new String[] { String.valueOf(IDs) };
		Cursor myCursor;
		myCursor = myDB.rawQuery("SELECT " + valueX + " FROM " + Object
				+ " WHERE " + ID + "=?", mySearch);
		myCursor.moveToFirst();
		if(myCursor.moveToFirst()){;
		int index = myCursor.getColumnIndex(valueX);
		
		float x = myCursor.getFloat(index);
		return x;
		}else
		return -1;
	}
	public float readDataBaseY(int IDs) {
		SQLiteDatabase myDB = this.getReadableDatabase();
	
		String[] mySearch = new String[] { String.valueOf(IDs) };
		Cursor myCursor;
		myCursor = myDB.rawQuery("SELECT " + valueY + " FROM " + Object
				+ " WHERE " + ID + "=?", mySearch);
		if(myCursor.moveToFirst()){;
		int index = myCursor.getColumnIndex(valueY);
		
		float y = myCursor.getFloat(index);
		return y;
		}else
		return -1;
	}
	public int readDataBaseInt(int IDs) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		Debug.e(String.valueOf(IDs));
		String[] mySearch = new String[] { String.valueOf(IDs) };
		Cursor myCursor;
		myCursor = myDB.rawQuery("SELECT " + houseLevel + " FROM " + Object
				+ " WHERE " + ID + "=?", mySearch);
		myCursor.moveToFirst();
		if(myCursor.moveToFirst()){;
		int index = myCursor.getColumnIndex(houseLevel);
		
		int string1 = myCursor.getInt(index);
		return string1;
		}else
		return -1;
	}
	public boolean checkIfDone(int IDs){
		SQLiteDatabase myDB = this.getReadableDatabase();
		if (DatabaseUtils.queryNumEntries(myDB, Object)+2
				 < IDs) {
					return true;
				}
		else
			return false;
	}
	public String readDataBaseString1(int IDs) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		Debug.e(String.valueOf(IDs));
		String[] mySearch = new String[] { String.valueOf(IDs) };
		Cursor myCursor;
		myCursor = myDB.rawQuery("SELECT " + string1 + " FROM " + Object
				+ " WHERE " + ID + "=?", mySearch);
		myCursor.moveToFirst();
		if(myCursor.moveToFirst()){;
		int index = myCursor.getColumnIndex(string1);
		
		String string1 = myCursor.getString(index);
		return string1;
		}else
		return "-1";
	}	public String readDataBaseString2(int IDs) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		if (DatabaseUtils.queryNumEntries(myDB, Object)-1
				 < IDs) {
					return "-1";
				}
		Debug.e(String.valueOf(IDs));
		String[] mySearch = new String[] { String.valueOf(IDs) };
		Cursor myCursor;
		myCursor = myDB.rawQuery("SELECT " + string2 + " FROM " + Object
				+ " WHERE " + ID + "=?", mySearch);
		myCursor.moveToFirst();
		if(myCursor.moveToFirst()){;
		int index = myCursor.getColumnIndex(string2);
		
		String string2 = myCursor.getString(index);
		return string2;
		}else
		return "-1";	}
	public String readDataBaseString3(int IDs) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		if (DatabaseUtils.queryNumEntries(myDB, Object)-1
				 < IDs) {
					return "-1";
				}
		Debug.e(String.valueOf(IDs));
		String[] mySearch = new String[] { String.valueOf(IDs) };
		Cursor myCursor;
		myCursor = myDB.rawQuery("SELECT " + string3 + " FROM " + Object
				+ " WHERE " + ID + "=?", mySearch);
		myCursor.moveToFirst();
		if(myCursor.moveToFirst()){;
		int index = myCursor.getColumnIndex(string3);
		
		String string3 = myCursor.getString(index);
		return string3;
		}else
		return "-1";
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// THIS METHOD DELETES THE EXISTING TABLE AND THEN CALLS THE METHOD onCreate() AGAIN TO RECREATE A NEW TABLE
// THIS SERVES TO ESSENTIALLY RESET THE DATABASE
// INSTEAD YOU COULD MODIFY THE EXISTING TABLES BY ADDING/REMOVING COLUMNS/ROWS/VALUES THEN NO EXISTING DATA WOULD BE LOST
	//	db.execSQL("DROP TABLE IF EXISTS "+tLevels);
		onCreate(db);
	}

	public long getSize() {
		SQLiteDatabase myDB = this.getReadableDatabase();
	return DatabaseUtils.queryNumEntries(myDB, Object);
	}
	
//	 public String isLevelUnLocked(int ID) {
//// THIS METHOD IS CALLED BY YOUR MAIN ACTIVITY TO READ A VALUE FROM THE DATABASE		 
//		 SQLiteDatabase myDB = this.getReadableDatabase();
//		 String[] mySearch = new String[]{String.valueOf(ID)};
//		 Cursor myCursor = myDB.rawQuery("SELECT "+ fLevelUnLocked +" FROM "+ tLevels +" WHERE "+ fLevelID +"=?",mySearch);
//		 myCursor.moveToFirst();
//		 int index = myCursor.getColumnIndex(fLevelUnLocked);
//		 String myAnswer = myCursor.getString(index);
//		 myCursor.close();
//		 return myAnswer;
//	 }
////	 
//	 public int unLockLevel(int ID, String isUnLocked)
//	 {
//// THIS METHOD IS CALLED BY YOUR MAIN ACTIVITY TO WRITE A VALUE TO THE DATABASE		 
//		 SQLiteDatabase myDB = this.getWritableDatabase();
//		 ContentValues cv = new ContentValues();
//	//	 cv.put(fLevelUnLocked, isUnLocked);
//		 int numRowsAffected = myDB.update(tLevels, cv, fLevelID+"=?", new String []{String.valueOf(ID)});
//		 return numRowsAffected;
//	 }
	 	
	 	
/*	 
 * MORE ADVANCED EXAMPLES OF USAGE
 * 
	 void AddEmployee(String _name, int _age, int _dept) {
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
			cv.put(colName, _name);
			cv.put(colAge, _age);
			cv.put(colDept, _dept);
			//cv.put(colDept,2);
		db.insert(employeeTable, colName, cv);
		db.close();
	}
	
	 int getEmployeeCount()
	 {
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select * from "+employeeTable, null);
		int x= cur.getCount();
		cur.close();
		return x;
	 }
	 
	 Cursor getAllEmployees()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 //Cursor cur= db.rawQuery("Select "+colID+" as _id , "+colName+", "+colAge+" from "+employeeTable, new String [] {});
		 Cursor cur= db.rawQuery("SELECT * FROM "+viewEmps,null);
		 return cur;
	 }
	  
	 public int GetDeptID(String Dept)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.query(deptTable, new String[]{colDeptID+" as _id",colDeptName},colDeptName+"=?", new String[]{Dept}, null, null, null);
		 //Cursor c=db.rawQuery("SELECT "+colDeptID+" as _id FROM "+deptTable+" WHERE "+colDeptName+"=?", new String []{Dept});
		 c.moveToFirst();
		 return c.getInt(c.getColumnIndex("_id"));
	 }
	 
	 public String GetDept(int ID)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 String[] params=new String[]{String.valueOf(ID)};
		 Cursor c=db.rawQuery("SELECT "+colDeptName+" FROM"+ deptTable+" WHERE "+colDeptID+"=?",params);
		 c.moveToFirst();
		 int index= c.getColumnIndex(colDeptName);
		 return c.getString(index);
	 }
	 
	 public Cursor getEmpByDept(String Dept)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 String [] columns=new String[]{"_id",colName,colAge,colDeptName};
		 Cursor c=db.query(viewEmps, columns, colDeptName+"=?", new String[]{Dept}, null, null, null);
		 return c;
	 }
	 
	 public int UpdateEmp(String _name, int _age, int _dept, int _eid)
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colName, _name);
		 cv.put(colAge, _age);
		 cv.put(colDept, _dept);
		 return db.update(employeeTable, cv, colID+"=?", new String []{String.valueOf(_eid)});
	 }
	 
	 public void DeleteEmp(String _name, int _age, int _dept, int _eid)
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 db.delete(employeeTable,colID+"=?", new String [] {String.valueOf(_eid)});
		 db.close();		
	 }
*/	 

}
