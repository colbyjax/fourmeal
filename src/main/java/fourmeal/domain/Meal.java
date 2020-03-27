package fourmeal.domain;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName="Meal")
public class Meal {

    private String id;
    private String name;
    private List<String> extras;
    private List<String> tags;
    private List<MealItem> mealitem;

    @DynamoDBHashKey(attributeName="id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @DynamoDBAttribute(attributeName="name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @DynamoDBAttribute(attributeName="extras")
    public List<String> getExtras() { return extras; }
    public void setExtras(List<String> extras) { this.extras = extras; }

    @DynamoDBAttribute(attributeName="tags")
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    @DynamoDBAttribute(attributeName="mealitem")
    public List<MealItem> getMealitem() { return mealitem; }
    public void setMealitem(List<MealItem> mealitem) { this.mealitem = mealitem; }
}