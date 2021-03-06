package fourmeal.rest;

import fourmeal.domain.Meal;
import fourmeal.service.FourMealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FourMealApi {
    private static final Logger logger = LoggerFactory.getLogger(FourMealApi.class);

    @Autowired
    private FourMealService fourMealService;

    /** Base Call to ensure API is responding -- Healthcheck **/
    @GetMapping("/")
    public ResponseEntity healthCheck() {
        logger.debug("HEALTHCHECK");
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity ping() {
       logger.info("FourMeal playing ping pong");
       return new ResponseEntity("pong", HttpStatus.OK);
    }

    /** Add a New Meal **/
    @PostMapping("/meal")
    @ResponseBody
    public Meal newMeal(@RequestBody Meal newMeal) {
        Meal meal = fourMealService.newMeal(newMeal);
        return meal;
    }

    /** Get a Meal **/
    @GetMapping("/meal")
    @ResponseBody
    public Meal getMeal(@RequestParam String id) {
        Meal meal = fourMealService.getMeal(id);
        return meal;
    }

    /** Update a Meal **/
    @PutMapping("/meal")
    @ResponseBody
    public Meal replaceMeal(@RequestParam String id, @RequestBody Meal replaceMeal) {
        Meal meal = fourMealService.replaceMeal(id, replaceMeal);
        return meal;
    }

    /** Delete Meal **/
    @DeleteMapping("/meal/{id}")
    public ResponseEntity deleteMeal(@PathVariable String id) {

        fourMealService.deleteMeal(id);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    /** Running a CPU Stress Test **/
    @GetMapping("/cpu-stress")
    public ResponseEntity getMealTest(@RequestParam long repeats) {
        logger.info("Running CPU Stress Test " + repeats + " times...");
        String result = fourMealService.cpuStress(repeats);
        return new ResponseEntity(result, HttpStatus.OK);
    }

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
