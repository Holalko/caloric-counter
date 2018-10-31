package cz.vsb.jakhol.caloriccounter.stores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.vsb.jakhol.caloriccounter.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DataStore extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Caloric_Counter";
    private static final int VERSION = 1;


    // FOOD
    private static final String TABLE_FOOD_STORE = "Food_Store";

    private static final String FOOD_ID = "id";
    private static final String FOOD_NAME = "food_name";
    private static final String FOOD_BARCODE = "barcode";
    private static final String FOOD_NUTRITIONS_ID = "nutrition_id";

    private static final String TABLE_NUTRITIONS_STORE = "Nutritions";

    private static final String FOOD_PROTEINS = "proteins";
    private static final String FOOD_CARBS = "carbohydrates";
    private static final String FOOD_FATS = "fats";
    private static final String FOOD_FIBER = "fiber";
    private static final String FOOD_CALORIES = "calories";

    // USER
    private static final String TABLE_USER = "user";
    private static final String USER_WEIGHT = "weight";
    private static final String USER_NICKNAME = "nickname";
    private static final String USER_GOAL_WEIGHT = "goal_weight";
    private static final String USER_HEIGHT = "height";
    private static final String USER_AGE = "age";

    // DAY MENU
    private static final String TABLE_DAY_MENU = "Day_Menu";

    private static final String DAY_MENU_ID = "id";
    private static final String DAY_MENU_DATE = "date";
    private static final String DAY_MENU_USER = "user_nickname";
    private static final String DAY_MENU_EATED_FOOD = "eated_food";

    private static final String DAY_MENU_USER_PK = "nickname";


    private Context context;

    public DataStore(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

//    private static List<Food> foodList;


//    // TODO
//    public static List<Food> getFoodListByName(String name) {
//        return new ArrayList<Food>() {{
//            add(new Food("Tvaroh", new NutritionValuePer100g(20, 25, 30, 3)));
//            add(new Food("Cottage", new NutritionValuePer100g(20, 25, 30, 3)));
//        }};
//    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String scriptNutritions = "CREATE TABLE " + TABLE_NUTRITIONS_STORE + "("
                + FOOD_ID + " INTEGER PRIMARY KEY,"
                + FOOD_CALORIES + " INTEGER,"
                + FOOD_PROTEINS + " INTEGER,"
                + FOOD_CARBS + " INTEGER,"
                + FOOD_FATS + " INTEGER,"
                + FOOD_FIBER + " INTEGER"
                + ")";


        String scriptFoodStore = "CREATE TABLE " + TABLE_FOOD_STORE + "("
                + FOOD_ID + " INTEGER PRIMARY KEY,"
                + FOOD_NAME + " TEXT,"
                + FOOD_BARCODE + " INTEGER,"
                + FOOD_NUTRITIONS_ID + " INTEGER,"
                + " FOREIGN KEY (" + FOOD_NUTRITIONS_ID + ") REFERENCES " + TABLE_NUTRITIONS_STORE + "(" + FOOD_ID + ")"
                + ")";

        String scriptUser = "CREATE TABLE " + TABLE_USER + "("
                + USER_NICKNAME + " TEXT PRIMARY KEY,"
                + USER_WEIGHT + " REAL,"
                + USER_GOAL_WEIGHT + " REAL,"
                + USER_HEIGHT + " REAL,"
                + USER_AGE + " INTEGER"
                + ")";

        String scriptDayMenu = "CREATE TABLE " + TABLE_DAY_MENU + "("
                + DAY_MENU_ID + " INTEGER PRIMARY KEY,"
                + DAY_MENU_DATE + " TEXT,"
                + DAY_MENU_USER + " TEXT,"
                + DAY_MENU_EATED_FOOD + " TEXT,"
                + " FOREIGN KEY (" + DAY_MENU_USER + ") REFERENCES " + DAY_MENU_USER + "(" + DAY_MENU_USER_PK + ")"
                + ")";

        sqLiteDatabase.execSQL(scriptNutritions);
        sqLiteDatabase.execSQL(scriptFoodStore);
        sqLiteDatabase.execSQL(scriptUser);
        sqLiteDatabase.execSQL(scriptDayMenu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_STORE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NUTRITIONS_STORE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addFood(Food food) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesToNutrition = new ContentValues();
        NutritionValuePer100g nutritions = food.getNutritionValuePer100g();

        valuesToNutrition.put(FOOD_CALORIES, nutritions.getTotalCalories());
        valuesToNutrition.put(FOOD_PROTEINS, nutritions.getProteins());
        valuesToNutrition.put(FOOD_FATS, nutritions.getFats());
        valuesToNutrition.put(FOOD_CARBS, nutritions.getCarbohydrates());
        valuesToNutrition.put(FOOD_FIBER, nutritions.getFiber());

        // Inserting Row
        long nutritionId = db.insert(TABLE_NUTRITIONS_STORE, null, valuesToNutrition);

        ContentValues foodValues = new ContentValues();

        foodValues.put(FOOD_NAME, food.getName());
        foodValues.put(FOOD_BARCODE, food.getBarcodeNumber());
        foodValues.put(FOOD_NUTRITIONS_ID, nutritionId);

        db.insert(TABLE_FOOD_STORE, null, foodValues);
        // Closing database connection
        db.close();
    }

    public List<Food> getFoodList() {


        String selectQuery = "SELECT  * FROM " + TABLE_FOOD_STORE;

        // return note list
        return getFoodValues(selectQuery);
    }

    public List<Food> getFoodList(String nameOrBarcode) {

        String selectQuery = "SELECT  * FROM " + TABLE_FOOD_STORE +
                " WHERE " + FOOD_NAME + " LIKE '%" + nameOrBarcode + "%'" +
                " OR " + FOOD_BARCODE + " = '" + nameOrBarcode +"'";

        // return note list
        return getFoodValues(selectQuery);
    }

    private List<Food> getFoodValues(String selectQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Food> foodList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                String selectFromNutrition = "SELECT * FROM " + TABLE_NUTRITIONS_STORE + " WHERE id = " + cursor.getInt(3);
                Cursor nutritionCursor = db.rawQuery(selectFromNutrition, null);
                NutritionValuePer100g nutritions = null;
                if (nutritionCursor.moveToFirst()) {
                    do {
                        nutritions = new NutritionValuePer100g(
                                nutritionCursor.getInt(3),
                                nutritionCursor.getInt(2),
                                nutritionCursor.getInt(4),
                                nutritionCursor.getInt(5)
                        );
                        nutritions.setId(nutritionCursor.getInt(0));
                    } while (nutritionCursor.moveToNext());
                }
                nutritionCursor.close();
                food.setId(cursor.getInt(0));
                food.setName(cursor.getString(1));
                food.setBarcodeNumber(cursor.getString(3));
                food.setNutritionValuePer100g(nutritions);
                // Adding note to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return foodList;
    }

    public User getUser() {
        String select = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(select, null);

        if (cursor != null) {
            if (cursor.getCount() == 0) {
                this.addNewUser();
                cursor.close();
                return getUser();
            }
            cursor.moveToFirst();
            User user = new User(
                    cursor.getString(0),
                    cursor.getDouble(1),
                    cursor.getDouble(2),
                    cursor.getDouble(3),
                    cursor.getInt(4)
            );
            cursor.close();
            return user;
        }
        return null;
    }

    private void addNewUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NICKNAME, "Name");
        values.put(USER_AGE, 20);
        values.put(USER_GOAL_WEIGHT, 85);
        values.put(USER_WEIGHT, 80);
        values.put(USER_HEIGHT, 185);

        // Inserting Row
        db.insert(TABLE_USER, null, values);

        // Closing database connection
        db.close();
    }

    public int updateUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_HEIGHT, user.getHeightInCm());
        values.put(USER_WEIGHT, user.getWeight());
        values.put(USER_GOAL_WEIGHT, user.getGoalWeight());
        values.put(USER_AGE, user.getAge());
        values.put(USER_NICKNAME, user.getNickname());

        // updating row
        return db.update(TABLE_USER, values, "", new String[]{});
    }

    public DayMenu getDayMenu() {
        SQLiteDatabase db = this.getWritableDatabase();

        Date today = new Date();
        String date = today.getDate() + "." + (today.getMonth() + 1) + "." + today.getYear();
        String select = "SELECT * FROM " + TABLE_DAY_MENU + " WHERE " + DAY_MENU_DATE + "= '" + date + "'";

        Cursor cursor = db.rawQuery(select, null);
        int count = cursor.getCount();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            DayMenu dayMenu = new DayMenu(getUser());
            dayMenu.setDate(cursor.getString(1));
            dayMenu.setId(cursor.getInt(0));
            List<EatedFood> eatedFoodList = new Gson().fromJson(cursor.getString(3), new TypeToken<ArrayList<EatedFood>>() {
            }.getType());
            dayMenu.setFoodList(eatedFoodList);

            cursor.close();
            return dayMenu;
        } else {
            addNewDayMenu();
            cursor.close();
            return getDayMenu();
        }
    }

    public void removeFromDayMenu(int index){
        DayMenu dayMenu = getDayMenu();
        dayMenu.getFoodList().remove(index);
        updateDayMenu(dayMenu);
    }

    public void addToDayMenu(Food food, int grams) {
        DayMenu dayMenu = getDayMenu();
        dayMenu.addFood(food, grams);
        updateDayMenu(dayMenu);
    }

    private void addNewDayMenu() {
        SQLiteDatabase db = this.getWritableDatabase();

        Date today = new Date();
        String date = today.getDate() + "." + (today.getMonth() + 1) + "." + today.getYear();


        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put(DAY_MENU_DATE, date);
        values.put(DAY_MENU_EATED_FOOD, gson.toJson(new ArrayList<EatedFood>()));
        values.put(DAY_MENU_USER, getUser().getNickname());

        // Inserting Row
        db.insert(TABLE_DAY_MENU, null, values);
        // Closing database connection
        db.close();
    }

    private int updateDayMenu(DayMenu dayMenu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DAY_MENU_EATED_FOOD, new Gson().toJson(dayMenu.getFoodList()));

        // updating row
        int updatedRows = db.update(TABLE_DAY_MENU, values, DAY_MENU_ID + "= " + dayMenu.getId(), new String[]{});
        return updatedRows;
    }


}
