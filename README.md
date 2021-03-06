# NBA_Scraping

This application takes one command-line argument and extracts 3 point average per season of a given player from the website https://www.basketball-reference.com/.

Example:

Input
```bash
Luka Dončić
```

Output
```bash
Season       3PA      Team
2018-19      7.1      (DAL)
2019-20      8.9      (DAL)
```








## Testing

The project also includes parameterized test with JUnit framework. For testing purposes there are 2 files in /src/test/resources/:

inputNames.txt and
expectedResults.txt

inputNames.txt should contain names of current NBA basketball players, one in each line.
expectedResults.txt should contain expected 3 point averages per season of the NBA players in inputNames.txt file. Expected results for one player should be written in this format:
```bash
"data | data | data | ... | data | "
```
where "data" is written in a format:
```bash
"20XX-YY, ZZ, QQQ"
```
where 20XX-YY is the season, ZZ is the 3 point average of the player in this season and QQQ is the NBA team in which player has played that season.

Note that input names and expected results must be written in the same lines (e.g. if "Luka Dončić" is written in the third line in inputNames.txt, his expected results must also be written in the third line in expectedResults.txt).
