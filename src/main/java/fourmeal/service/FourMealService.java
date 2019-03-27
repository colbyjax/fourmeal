package fourmeal.service;

import fourmeal.domain.Meal;

public interface FourMealService {
     Meal newMeal(Meal newMeal);
     Meal getMeal(String id);
     Meal replaceMeal(String id, Meal replaceMeal);
     void deleteMeal(String id);
}
