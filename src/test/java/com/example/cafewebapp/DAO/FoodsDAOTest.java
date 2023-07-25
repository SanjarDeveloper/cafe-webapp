package com.example.cafewebapp.DAO;

import com.example.cafewebapp.Entity.Foods;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FoodsDAOTest {
    FoodsDAO foodsDAO = new FoodsDAO();
    @Test
    void createFoodTest() {
        Foods foods = new Foods(
                1,
                "pizza",
                "Something",
                "fastfood",
                BigDecimal.valueOf(12.3),
                true
        );
        assertTrue(foodsDAO.createFood(foods));
        foodsDAO.deleteFood(foods.getId());
    }

    @Test
    void getFoodByIdTest() {
        Foods foods = new Foods(
                2,
                "pizza",
                "Something",
                "fastfood",
                BigDecimal.valueOf(12.3),
                true
        );
        foodsDAO.createFood(foods);
        assertEquals(foods.getId(),foodsDAO.getFoodById(foods.getId()).getId());
        assertEquals(foods.getName(),foodsDAO.getFoodById(foods.getId()).getName());
        assertEquals(foods.getDetails(),foodsDAO.getFoodById(foods.getId()).getDetails());
        assertEquals(foods.getCategory(),foodsDAO.getFoodById(foods.getId()).getCategory());
        assertEquals(foods.getPrice(),foodsDAO.getFoodById(foods.getId()).getPrice());
        assertEquals(foods.isActive(),foodsDAO.getFoodById(foods.getId()).isActive());
        foodsDAO.deleteFood(foods.getId());
    }

    @Test
    void updateFoodTest() {
        Foods foods = new Foods(
                3,
                "pizza",
                "Something",
                "fastfood",
                BigDecimal.valueOf(12.3),
                true
        );
        foodsDAO.createFood(foods);
        foods.setActive(false);
        foods.setName("burger");
        assertTrue(foodsDAO.updateFood(foods));
        foodsDAO.deleteFood(foods.getId());
    }

    @Test
    void deleteFoodTest() {
        assertFalse(foodsDAO.deleteFood(1));
    }
}