package org.example;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class TrelloTest {

    String APIKey = "e7461252e13944c2fcaa1f6a4e83511b";
    String APIToken = "ATTA0561085aa39d4902dc61fd4be2860367b1cde0472365845edde8303257f392f2F7B9506F";
    String boardName = "{fromJavaBoard}";
    String boardId = "63f1766a424de3a310427c0d";
    String listName = "{fromJavaList}";
    String listId ="63f17730d00d0e529ddf6957";
    String cardName1 = "{fromJavaCard1}";
    String cardId2 ="63f17804bbbc7b2e1f5e2820";
    String cardName2 = "{fromJavaCard2}";
    String cardId1 ="63f1784cdeca3847104b23a3";

    @Test
    public void createTrelloBoard() throws UnirestException {

        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/")
                .queryString("name",boardName )
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asString();

        System.out.println(response.getBody());

    }

    @Test
    public void createTrelloListOnBoard() throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/lists")
                .queryString("name", listName)
                .queryString("idBoard", boardId)
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asString();

        System.out.println(response.getBody());

    }


    @Test
    public void createTrelloCard1OnList() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://api.trello.com/1/cards")
                .header("Accept", "application/json")
                .queryString("name", cardName1)
                .queryString("idList", listId)
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asJson();
        System.out.println(response.getBody());

    }
    @Test
    public void createTrelloCard2OnList() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://api.trello.com/1/cards")
                .header("Accept", "application/json")
                .queryString("name", cardName2)
                .queryString("idList", listId)
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asJson();
        System.out.println(response.getBody());

    }

    @Test
    public void updateTrelloCardOnListRandomly() throws UnirestException {
        String cardID;
        int tmp = (int) (Math.random() * 1) + 1;
        if (tmp == 1) {
            cardID =cardId1;
        } else {
            cardID = cardId2;
        }
        String urlUpdated = "https://api.trello.com/1/cards/"+cardID;

        HttpResponse<JsonNode> response = Unirest.put(urlUpdated)
                .header("Accept", "application/json")
                .queryString("id", cardID)
                .queryString("name", "{fromJavaUpdated}")
                .queryString("color", "yellow")
                .queryString("idList", listId)
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asJson();


        System.out.println(response.getBody());
    }

    @Test
    public void deleteTrelloCardOnList() throws UnirestException {
        HttpResponse<String> response = Unirest.delete("https://api.trello.com/1/cards/"+cardId1)
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asString();

        System.out.println(response.getBody());
    }
    public void deleteTrelloCard2OnList() throws UnirestException {
        HttpResponse<String> response = Unirest.delete("https://api.trello.com/1/cards/"+cardId2)
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asString();

        System.out.println(response.getBody());
    }

    @Test
    public void deleteTrelloBoard() throws UnirestException {
        HttpResponse<String> response = Unirest.delete("https://api.trello.com/1/boards/"+boardId)
                .queryString("key", APIKey)
                .queryString("token", APIToken)
                .asString();

        System.out.println(response.getBody());
    }
}