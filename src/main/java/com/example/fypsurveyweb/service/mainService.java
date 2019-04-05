package com.example.fypsurveyweb.service;

import com.example.fypsurveyweb.FypSurveyWebApplication;
import com.example.fypsurveyweb.dao.mongoDB;
import com.example.fypsurveyweb.domain.UserAnswer;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoCollection;

import java.util.*;
import java.util.stream.Collectors;

/*
@author Qinyuan Zhang
@date 01/04/2019
*/
@Service
public class mainService {

   private mongoDB mDB = new mongoDB();

   public void initDB() {
      mDB.initDB();
   }

   public void addResult(UserAnswer userAnswer) {

      int picIndex = 1;
      for (String pic : userAnswer.getPicNum()) {
         switch (picIndex) {
            case 1:
               if (userAnswer.getQ1().equals("q1Real"))
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Real",1)));
               else
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Fake",1)));
               mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("1Involve",1)));
               break;
            case 2:
               if (userAnswer.getQ2().equals("q2Real"))
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Real",1)));
               else
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Fake",1)));
               mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("1Involve",1)));
               break;
            case 3:
               if (userAnswer.getQ3().equals("q3Real"))
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Real",1)));
               else
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Fake",1)));
               mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("1Involve",1)));
               break;
            case 4:
               if (userAnswer.getQ4().equals("q4Real"))
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Real",1)));
               else
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Fake",1)));
               mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("1Involve",1)));
               break;
            case 5:
               if (userAnswer.getQ5().equals("q5Real"))
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Real",1)));
               else
                  mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Fake",1)));
               mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("1Involve",1)));
               break;
            case 6:
               addRate(userAnswer.getQ6(), pic);
               break;
            case 7:
               addRate(userAnswer.getQ7(), pic);
               break;
            case 8:
               addRate(userAnswer.getQ8(), pic);
               break;
            case 9:
               addRate(userAnswer.getQ9(), pic);
               break;
            case 10:
               addRate(userAnswer.getQ10(), pic);
               break;
      }
         picIndex++;
      }
   }

   public void addRate(String result, String pic) {
      switch (result) {
         case "One":
            mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Rate",1)));
            break;
         case "Two":
            mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Rate",2)));
            break;
         case "Three":
            mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Rate",3)));
            break;
         case "Four":
            mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("Rate",4)));
            break;
      }
      mDB.getTable().updateOne(Filters.eq("pic", pic), new Document("$inc",new Document("2Involve",1)));
   }

   public Map<String, Map<String, Integer>> getResults() {

      Map<String, Map<String, Integer>> results = new HashMap<>();
      Map<String, Integer> Ori = new HashMap<>();
      Map<String, Integer> Ord = new HashMap<>();
      Map<String, Integer> Gan = new HashMap<>();
      Map<String, Integer> Res = new HashMap<>();

      FindIterable<Document> findIterable = mDB.getTable().find();
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      while(mongoCursor.hasNext()){
         Document currentCursor = mongoCursor.next();
         switch (((String) currentCursor.get("pic")).split("-")[1]) {
            case "0":
               Ori.put("Real", Ori.containsKey("Real") ? Ori.get("Real") +
                    (Integer) currentCursor.get("Real") :  (Integer) currentCursor.get("Real"));
               Ori.put("Fake", Ori.containsKey("Fake") ? Ori.get("Fake") +
                       (Integer) currentCursor.get("Fake") :  (Integer) currentCursor.get("Fake"));
               Ori.put("Rate", Ori.containsKey("Rate") ? Ori.get("Rate") +
                       (Integer) currentCursor.get("Rate") :  (Integer) currentCursor.get("Rate"));
               Ori.put("1Involve", Ori.containsKey("1Involve") ? Ori.get("1Involve") +
                       (Integer) currentCursor.get("1Involve") :  (Integer) currentCursor.get("1Involve"));
               Ori.put("2Involve", Ori.containsKey("2Involve") ? Ori.get("2Involve") +
                       (Integer) currentCursor.get("2Involve") :  (Integer) currentCursor.get("2Involve"));
               break;
            case "1":
               Ord.put("Real", Ord.containsKey("Real") ? Ord.get("Real") +
                       (Integer) currentCursor.get("Real") :  (Integer) currentCursor.get("Real"));
               Ord.put("Fake", Ord.containsKey("Fake") ? Ord.get("Fake") +
                       (Integer) currentCursor.get("Fake") :  (Integer) currentCursor.get("Fake"));
               Ord.put("Rate", Ord.containsKey("Rate") ? Ord.get("Rate") +
                       (Integer) currentCursor.get("Rate") :  (Integer) currentCursor.get("Rate"));
               Ord.put("1Involve", Ord.containsKey("1Involve") ? Ord.get("1Involve") +
                       (Integer) currentCursor.get("1Involve") :  (Integer) currentCursor.get("1Involve"));
               Ord.put("2Involve", Ord.containsKey("2Involve") ? Ord.get("2Involve") +
                       (Integer) currentCursor.get("2Involve") :  (Integer) currentCursor.get("2Involve"));
               break;
            case "2":
               Gan.put("Real", Gan.containsKey("Real") ? Gan.get("Real") +
                       (Integer) currentCursor.get("Real") :  (Integer) currentCursor.get("Real"));
               Gan.put("Fake", Gan.containsKey("Fake") ? Gan.get("Fake") +
                       (Integer) currentCursor.get("Fake") :  (Integer) currentCursor.get("Fake"));
               Gan.put("Rate", Gan.containsKey("Rate") ? Gan.get("Rate") +
                       (Integer) currentCursor.get("Rate") :  (Integer) currentCursor.get("Rate"));
               Gan.put("1Involve", Gan.containsKey("1Involve") ? Gan.get("1Involve") +
                       (Integer) currentCursor.get("1Involve") :  (Integer) currentCursor.get("1Involve"));
               Gan.put("2Involve", Gan.containsKey("2Involve") ? Gan.get("2Involve") +
                       (Integer) currentCursor.get("2Involve") :  (Integer) currentCursor.get("2Involve"));
               break;
            case "3":
               Res.put("Real", Res.containsKey("Real") ? Res.get("Real") +
                       (Integer) currentCursor.get("Real") :  (Integer) currentCursor.get("Real"));
               Res.put("Fake", Res.containsKey("Fake") ? Res.get("Fake") +
                       (Integer) currentCursor.get("Fake") :  (Integer) currentCursor.get("Fake"));
               Res.put("Rate", Res.containsKey("Rate") ? Res.get("Rate") +
                       (Integer) currentCursor.get("Rate") :  (Integer) currentCursor.get("Rate"));
               Res.put("1Involve", Res.containsKey("1Involve") ? Res.get("1Involve") +
                       (Integer) currentCursor.get("1Involve") :  (Integer) currentCursor.get("1Involve"));
               Res.put("2Involve", Res.containsKey("2Involve") ? Res.get("2Involve") +
                       (Integer) currentCursor.get("2Involve") :  (Integer) currentCursor.get("2Involve"));
               break;
         }
         results.put("Ori", Ori);
         results.put("Ord", Ord);
         results.put("Gan", Gan);
         results.put("Res", Res);
      }
      return results;
   }

}