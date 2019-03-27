package fourmeal.rest;

import fourmeal.domain.Meal;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public interface FourMealApi {

    @GetMapping("/")
    String index();

    @GetMapping("/test")
    String getMealTest();

    /** Add a New Meal **/
    @PostMapping(path = "/meal", consumes = "application/json", produces = "application/json" )
    Meal newMeal(@RequestBody Meal newMeal);

    /** Get a Meal **/
    @GetMapping("/meal/{id}")
    Meal getMeal(@PathVariable String id);


    /** Update a Meal **/
    @PutMapping("/meal/{id}")
    Meal replaceMeal(@PathVariable String id, Meal replaceMeal);

    /** Delete Meal **/
    @DeleteMapping("/meal/{id}")
    void deleteMeal(@PathVariable String id);

    // Create List CRUD


    // Create User CRUD

    /*
        * addMealToUser - Add Meal to the user document
        * addListToUser - Add List to the user document
        * getLists - get User Lists
        * getMeals - get User Meals
        *
        * addMealToList - add one of the meals to the List, have a number to indicate how many times
        * addItemToList - Add an item that is not a meal to the list
        *
        * How to link to online shopping services
        * How to solve for brands
        * Maybe create a list scrubber that will review preferences from list (generic/brand)
     */

}
