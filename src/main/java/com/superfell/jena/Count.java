package com.superfell.jena;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Count {

  public static void main(String[] args) {
    Model jenaModel = ModelFactory.createDefaultModel();
    jenaModel.read("data.ttl");

    String qStar =
        "BASE <https://superfell.com/>\n"
            + "\n"
            + "SELECT (COUNT(DISTINCT *) as ?c)\n"
            + "WHERE { <child3> <p>+/<label> ?lbl }";
    run(jenaModel, qStar);

    String qVar =
        "BASE <https://superfell.com/>\n"
            + "\n"
            + "SELECT (COUNT(DISTINCT ?lbl) as ?c)\n"
            + "WHERE { <child3> <p>+/<label> ?lbl }";
    run(jenaModel, qVar);
  }

  private static void run(Model m, String q) {
    Query query = QueryFactory.create(q);
    try (QueryExecution qexec = QueryExecutionFactory.create(query, m)) {
      ResultSet results = qexec.execSelect();
      QuerySolution row = results.next();
      System.out.printf("?c has value %s\n", row.getLiteral("c"));
    }
  }
}
