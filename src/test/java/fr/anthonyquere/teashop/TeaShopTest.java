package fr.anthonyquere.teashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TeaShopTests {

    private Tea earlGrey;
    private Tea greenTea;
    private TeaShop shop;

    @BeforeEach
    void setUp() {

        earlGrey = new Tea("Earl Grey", 180, 95, false);
        greenTea = new Tea("Green Tea", 120, 80, true);


        shop = new TeaShop(90);


        shop.addTea(earlGrey);
        shop.addTea(greenTea);
    }



    @Test
    void should_test_tea_creation_with_correct_parameters() {
        assertEquals("Earl Grey", earlGrey.getName());
        assertEquals(180, earlGrey.getSteepingTimeSeconds());
        assertEquals(95, earlGrey.getIdealTemperatureCelsius());
        assertFalse(earlGrey.isLoose());

        assertEquals("Green Tea", greenTea.getName());
        assertEquals(120, greenTea.getSteepingTimeSeconds());
        assertEquals(80, greenTea.getIdealTemperatureCelsius());
        assertTrue(greenTea.isLoose());
    }

    @Test
    void should_test_tea_setters() {
        Tea customTea = new Tea("Custom", 150, 85, false);

        customTea.setName("New Name");
        assertEquals("New Name", customTea.getName());

        customTea.setSteepingTimeSeconds(200);
        assertEquals(200, customTea.getSteepingTimeSeconds());

        customTea.setIdealTemperatureCelsius(90);
        assertEquals(90, customTea.getIdealTemperatureCelsius());

        customTea.setLoose(true);
        assertTrue(customTea.isLoose());
    }



    @Test
    void should_test_add_tea_to_shop() {
        TeaShop newShop = new TeaShop(85);
        newShop.addTea(earlGrey);


        TeaCup cup = newShop.prepareTea("Earl Grey");
        assertNotNull(cup);
    }

    @Test
    void should_test_prepare_tea_with_available_tea() {
        TeaCup cup = shop.prepareTea("Earl Grey");
        assertNotNull(cup);
    }

    @Test
    void should_test_prepare_tea_with_case_insensitive_name() {
        TeaCup cup = shop.prepareTea("earl grey");
        assertNotNull(cup);
    }

    @Test
    void should_test_prepare_tea_with_unavailable_tea() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.prepareTea("Chamomile");
        });

        assertTrue(exception.getMessage().contains("Tea not available"));
    }

    @Test
    void should_test_set_water_temperature_with_valid_temperature() {
        shop.setWaterTemperature(75);

        TeaCup cup = shop.prepareTea("Green Tea");
        assertNotNull(cup);
    }

    @Test
    void should_test_set_water_temperature_with_invalid_temperature_below_zero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.setWaterTemperature(-10);
        });

        assertTrue(exception.getMessage().contains("Water temperature must be between 0 and 100"));
    }

    @Test
    void should_test_set_water_temperature_with_invalid_temperature_above_hundred() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.setWaterTemperature(110);
        });

        assertTrue(exception.getMessage().contains("Water temperature must be between 0 and 100"));
    }



    @Test
    void should_test_add_water_to_cup() {
        TeaCup cup = new TeaCup();
        cup.addWater(85);


        cup.addTea(greenTea);
    }



    @Test
    void should_test_add_tea_to_cup_with_water() {
        TeaCup cup = new TeaCup();
        cup.addWater(95);
        cup.addTea(earlGrey);


    }

    @Test
    void should_test_is_ready_to_drink_with_no_tea() {
        TeaCup cup = new TeaCup();
        cup.addWater(95);

        assertFalse(cup.isReadyToDrink());
    }

    @Test
    void should_test_is_ready_to_drink_with_perfect_conditions() {

        Tea quickTea = new Tea("Quick Tea", 1, 90, false);
        TeaCup cup = new TeaCup();
        cup.addWater(90);
        cup.addTea(quickTea);


        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            fail("Test interrompu");
        }


        assertTrue(cup.isReadyToDrink());
    }

    @Test
    void should_test_is_ready_to_drink_with_incorrect_temperature() {
        Tea sensitiveTea = new Tea("Sensitive Tea", 1, 80, false);
        TeaCup cup = new TeaCup();
        cup.addWater(95);
        cup.addTea(sensitiveTea);


        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            fail("Test interrompu");
        }


        assertFalse(cup.isReadyToDrink());
    }
}