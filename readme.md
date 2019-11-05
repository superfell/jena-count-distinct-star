# Count(Distinct *) in Jena

Possible bug in count(distint *) in Jena.

Given that ?lbl is the only variable in the BGP, count(distinct *) and count(distinct ?lbl) should return the same results.

Running these queries returns 3 for the first one, and 2 for the second one.

```
BASE <https://superfell.com/>
SELECT (COUNT(DISTINCT *) as ?c)
WHERE { <child3> <p>+/<label> ?lbl }
```

```
BASE <https://superfell.com/>
SELECT (COUNT(DISTINCT ?lbl) as ?c)
WHERE { <child3> <p>+/<label> ?lbl }
```