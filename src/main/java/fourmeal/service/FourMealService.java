package fourmeal.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import fourmeal.domain.Meal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class FourMealService {
    private static final Logger logger = LoggerFactory.getLogger(FourMealService.class);

    public Meal getMeal(String id) {
        Meal retrievedMeal = FourMealService.getMapper().load(Meal.class, id);

        return retrievedMeal;
    }

    public Meal newMeal(Meal newMeal) {
        logger.info("Creating new meal: " + newMeal.getId());

        Meal meal = new Meal();
        meal.setId(newMeal.getId());
        meal.setName(newMeal.getName());
        meal.setExtras(newMeal.getExtras());
        meal.setTags(newMeal.getTags());
        meal.setMealitem(newMeal.getMealitem());

        FourMealService.getMapper().save(meal);
        return meal;
    }

    public Meal replaceMeal(String id, Meal replaceMeal) {
        logger.debug("Updating meal: " + id);

        Meal meal = new Meal();
        meal.setId(id);
        meal.setName(replaceMeal.getName());
        meal.setExtras(replaceMeal.getExtras());
        meal.setTags(replaceMeal.getTags());
        meal.setMealitem(replaceMeal.getMealitem());

        FourMealService.getMapper().save(meal);
        return meal;
    }

    public boolean deleteMeal(String id) {
        logger.debug("Deleting Meal: " + id);

        Meal retrievedMeal = FourMealService.getMapper().load(Meal.class, id);
        FourMealService.getMapper().delete(retrievedMeal);

        return true;
    }

    /***
     * Helper method to build connection and return Dyanamo Mapper
     * @return
     */
    private static DynamoDBMapper getMapper() {
        AmazonDynamoDB client =  AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        return mapper;
    }
}
