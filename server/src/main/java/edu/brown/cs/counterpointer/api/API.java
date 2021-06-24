package edu.brown.cs.counterpointer.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.brown.cs.counterpointer.analysis.Score;
import edu.brown.cs.counterpointer.analysis.checkers.Checker;
import edu.brown.cs.counterpointer.analysis.checkers.Checkers;
import edu.brown.cs.counterpointer.analysis.checkers.RuleViolation;
import edu.brown.cs.counterpointer.analysis.generators.Generator;
import edu.brown.cs.counterpointer.analysis.generators.Generators;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import spark.Request;
import spark.Route;

import java.util.Collection;
import java.util.List;

/**
 * Class to provide API handlers.
 */
public class API {
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON

  private Score parseScore(Request request) {
    try {
      return om.readValue(request.body(), Score.class);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Invalid score: " + e.getMessage());
    }
  }

  private int parseSpecies(Request request) {
    String species = request.queryParams("species");
    if (species == null) {
      throw new IllegalArgumentException("Parameter species must be set to an integer");
    }
    try {
      return Integer.parseInt(species);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Parameter species must be set to an integer");
    }
  }

  private Checker selectChecker(Request request) {
    int species = parseSpecies(request);
    switch (species) {
      case 1:
        return Checkers.species1;
      case 2:
        return Checkers.species2;
      case 3:
        return Checkers.species3;
      case 4:
        return Checkers.species4;
      default:
        throw new IllegalArgumentException("Parameter species must be between 1 and 4");
    }
  }

  private Generator selectGenerator(Request request) {
    int species = parseSpecies(request);
    switch (species) {
      case 1:
        return Generators.species1;
      case 2:
        return Generators.species2;
      case 3:
        return Generators.species3;
      case 4:
        return Generators.species4;
      default:
        throw new IllegalArgumentException("Parameter species must be between 1 and 4");
    }
  }

  private final Route generate = (request, response) -> {
    Generator generator = selectGenerator(request);
    Score score = parseScore(request);
    try {
      Melody cm = generator.generateCounterMelody(score);
      ObjectNode rootNode = om.createObjectNode();
      if (cm != null) {
        score = new Score(score.getMelody(), cm, score.getMelodyIsUpper());
        rootNode.put("status", "success");
        rootNode.set("score", om.valueToTree(score));
      } else {
        rootNode.put("status", "failure");
      }

      return om.writeValueAsString(rootNode);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "{\"status\": \"failure\"}";
  };

  /**
   * Handles get request to the /generate endpoint, generating a countermelody for the current
   * score.
   * @return /generate handler
   */
  public Route postGenerate() {
    return generate;
  }

  private final Route suggest = (request, response) -> {
    Generator generator = selectGenerator(request);
    Score score = parseScore(request);
    try {
      Collection<Note> notes = generator.generatePossibleNext(score);
      return om.writeValueAsString(notes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "[]";
  };

  /**
   * Handles get request to the /suggest endpoint, suggesting a list of possible next notes for
   * a given score.
   * @return /suggest handler
   */
  public Route postSuggest() {
    return suggest;
  }

  private final Route check = (request, response) -> {
    Checker checker = selectChecker(request);
    Score score = parseScore(request);
    try {
      List<RuleViolation> violations = checker.check(score);
      return om.writeValueAsString(violations);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "[]";
  };

  /**
   * Handles get request to the /check endpoint, checking a score for rule violations.
   * @return /check handler
   */
  public Route postCheck() {
    return check;
  }
}
