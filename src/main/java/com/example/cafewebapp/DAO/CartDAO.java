package com.example.cafewebapp.DAO;

import com.example.cafewebapp.Entity.Foods;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CartDAO {
    public static LinkedHashMap<Foods, Integer> foodsList = new LinkedHashMap<>();
    FoodsDAO foodsDAO = new FoodsDAO();

    public boolean addFoodToCart(int amount, int foodId) {
        boolean isSuccess = false;
        boolean isSameFood = false;
        try {
            Foods food = foodsDAO.getFoodById(foodId);
            for (Foods foods : foodsList.keySet()) {
                if (foods.getId() == food.getId()){
                    Integer integer = foodsList.get(foods);
                    foodsList.replace(foods, integer, integer + amount);
                    isSameFood = true;
                }
            }
            if (!isSameFood){
                foodsList.put(food,amount);
            }
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean plusOneAmount(int foodId) {
        boolean isSuccess = false;
        try {
            for (Foods foods : foodsList.keySet()) {
                if (foodId == foods.getId()) {
                    Integer amount = foodsList.get(foods);
                    foodsList.replace(foods, amount, amount + 1);
                    isSuccess = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean minusOneAmount(int foodId) {
        boolean isSuccess = false;
        try {
            for (Foods food : foodsList.keySet()) {
                if (foodId == food.getId()) {
                    Integer amount = foodsList.get(food);
                    foodsList.replace(food, amount, amount - 1);
                }
                if (foodsList.get(food).equals(0)) {
                    foodsList.remove(food);
                }
            }
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean clearCart() {
        boolean isSuccess = false;
        try {
            foodsList.clear();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
