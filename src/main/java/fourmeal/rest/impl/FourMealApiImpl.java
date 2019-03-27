package fourmeal.rest.impl;

import fourmeal.domain.Meal;
import fourmeal.rest.FourMealApi;
import fourmeal.service.FourMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Component
public class FourMealApiImpl implements FourMealApi {
    @Autowired
    private FourMealService fourMealService;

    @Override
    public String index() {
        return "Greetings from Fourmeal!";
    }

    @Override
    public String getMealTest() {
       Meal meal = fourMealService.getMeal("colbywar:pancake-breakfast");
       return meal.getName();
    }

    @Override
    @PostMapping(path = "/meal", consumes = "application/json", produces = "application/json" )
    public Meal newMeal(Meal newMeal) {
        Meal meal = fourMealService.newMeal(newMeal);
        return meal;
    }

    @GetMapping("/meal/{id}")
    @Override
    public Meal getMeal(String id) {
        Meal meal = fourMealService.getMeal(id);
        return meal;
    }

    @Override
    public Meal replaceMeal(String id, Meal replaceMeal) {
        Meal meal = fourMealService.replaceMeal(id, replaceMeal);
        return meal;
    }

    @Override
    public void deleteMeal(String id) {
        fourMealService.deleteMeal(id);
    }
}
