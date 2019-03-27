package fourmeal.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import fourmeal.domain.Meal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FourMealService {
    public static final Logger logger = LoggerFactory.getLogger(FourMealService.class);

    public Meal getMeal(String id) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Meal retrievedMeal = mapper.load(Meal.class, id);

        return retrievedMeal;
    }

    public Meal newMeal(Meal newMeal) {
        logger.info("Creating new meal: " + newMeal.getId());
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Meal meal = new Meal();
        meal.setId(newMeal.getId());
        meal.setName(newMeal.getName());
        meal.setExtras(newMeal.getExtras());
        meal.setTags(newMeal.getTags());
        meal.setMealitem(newMeal.getMealitem());

        mapper.save(meal);
        return meal;
    }

    public Meal replaceMeal(String id, Meal replaceMeal) {
        logger.debug("Updating meal: " + id);
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Meal meal = new Meal();
        meal.setId(id);
        meal.setName(replaceMeal.getName());
        meal.setExtras(replaceMeal.getExtras());
        meal.setTags(replaceMeal.getTags());
        meal.setMealitem(replaceMeal.getMealitem());

        mapper.save(meal);
        return meal;
    }

    public boolean deleteMeal(String id) {
        logger.debug("Deleting Meal: " + id);
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Meal retrievedMeal = mapper.load(Meal.class, id);
        mapper.delete(retrievedMeal);

        return true;
    }
}
