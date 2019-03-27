package fourmeal.service.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import fourmeal.domain.Meal;
import org.springframework.stereotype.Service;
import fourmeal.service.FourMealService;

import java.util.List;

@Service
public class FourMealServiceImpl implements FourMealService {

    @Override
    public Meal getMeal(String id) {
        System.out.println("Running getMeals...");
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Meal partitionKey = new Meal();

        partitionKey.setId(id);
        DynamoDBQueryExpression<Meal> queryExpression = new DynamoDBQueryExpression<Meal>()
                .withHashKeyValues(partitionKey);

        List<Meal> meals = mapper.query(Meal.class, queryExpression);

       return meals.get(0);
    }

    @Override
    public Meal newMeal(Meal newMeal) {
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

    @Override
    public Meal replaceMeal(String id, Meal replaceMeal) {
        return null;
    }

    @Override
    public void deleteMeal(String id) {

    }
}
