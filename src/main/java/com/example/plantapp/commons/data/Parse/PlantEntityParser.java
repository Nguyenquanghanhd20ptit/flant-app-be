package com.example.plantapp.commons.data.Parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.PlantTypeEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlantEntityParser {

    public static List<PlantEntity> parseJsonToPlantEntities(String jsonData) throws IOException {
        List<PlantEntity> plantEntities = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonData);

        // Retrieve data from JSON and create PlantEntity objects
        int id = rootNode.path("id").asInt();
        String name = rootNode.path("common_name").asText();
        String description = rootNode.path("description").asText();
        String imageUrl = rootNode.path("default_image").path("regular_url").asText();
        String origin = String.join(", ", rootNode.path("origin").findValuesAsText("name"));
        String guide = rootNode.path("care-guides").asText();
        String water = rootNode.path("watering").asText();
        String light = String.join(", ", rootNode.path("sunlight").findValuesAsText(""));
        String pruningMonth = String.join(", ", rootNode.path("pruning_month").findValuesAsText(""));
        String growLevel = rootNode.path("growth_rate").asText();
        String cycle = rootNode.path("cycle").asText();
        String careLevel = rootNode.path("care_level").asText();

        // Create PlantEntity object
        PlantEntity plantEntity = new PlantEntity()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setImage_url(imageUrl)
                .setOrigin(origin)
                .setGuide(guide)
                .setWater(water)
                .setLight(light)
                .setPruning_month(pruningMonth)
                .setGrow_level(growLevel)
                .setCycle(cycle)
                .setCare_level(careLevel)
                .setCreatedAt(LocalDateTime.now());

        // Add PlantEntity to the list
        plantEntities.add(plantEntity);

        return plantEntities;
    }

    public static void main(String[] args) {
        String jsonData = ""; // Insert your JSON data here
        try {
            List<PlantEntity> plantEntities = parseJsonToPlantEntities(jsonData);
            for (PlantEntity entity : plantEntities) {
                System.out.println(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
