
TEMPLATE FOR RETROSPECTIVE (Team 12)
=====================================
- [process measures](#process-measures)
- [quality measures](#quality-measures)
- [general assessment](#assessment)

## PROCESS MEASURES

### Macro statistics

- 6 stories committed vs 6 stories done
- 28 points committed vs 28 points done
- 6d 4h 30m ( = 52h 30m) Nr of hours planned vs spent (as a team) 5d 6h 55m (= 46h 55m)


**Remember**  a story is done ONLY if it fits the Definition of Done:

- Unit Tests passing *(with a little tollerance)*
- Code review completed
- Code present on VCS
- End-to-End tests performed

### Detailed statistics

| Story       | # Tasks     | Points | Hours est. | Hours actual |
| ----------- | ----------- | ------ | ---------- | ------------ |
| _#0_        | 5           | -      | 4d 1h 30m  | 4d 1h 40m    |
| -           | S2112OQM-18 | -      | 1d 3h      | 1d 4h 5m     |
| -           | S2112OQM-15 | -      | 0d 6h      | 0d 5h 15m    |
| -           | S2112OQM-14 | -      | 0d 3h      | 0d 1h 15m    |
| -           | S2112OQM-28 | -      | 0d 3h      | 0d 5h 20m    |
| -           | S2112OQM-27 | -      | 1d 1h      | 0d 7h        |
| -           | S2112OQM-26 | -      | 0d 1h 30m  | 0d 2h 45m    |
| -           | -           | -      | -          | -            |
| S2112OQM-3  | S2112OQM-17 | 5      | 0d 1h      | 0d 1h        |
| S2112OQM-4  | S2112OQM-23 | 8      | 0d 5h      | 0d 3h        |
| S2112OQM-8  | S2112OQM-22 | 2      | 0d 1h      | 0h 30m       |
| S2112OQM-10 | S2112OQM-25 | 3      | 0d 4h      | 0d 2h 15m    |
| S2112OQM-11 | S2112OQM-19 | 8      | 0d 6h      | 0d 4h 30m    |
| S2112OQM-6  | S2112OQM-29 | 2      | 0d 2h      | 0d 2h        |

Total est: 6d 4h 30m
Total actual: 5d 6h 55m

> place technical tasks corresponding to story `#0` and leave out story points (not applicable in this case)

**Note: story _#0_ contains also the transversal tasks**

- Hours per task (average, standard deviation)
    - Average: 3 h 55m
    - Standard deviation: 3h 7m
- Total task estimation error ratio: sum of total hours estimation / sum of total hours spent from previous table
    - 52.5/46.91= 1.12


## QUALITY MEASURES

- Unit Testing:
    - Total hours estimated:
        - 3h
    - Total hours spent:
        - 4h 30m
    - Nr of automated unit test cases
        -  5
    - Coverage (if available):
        - BE only: 67% (lines of code)
- E2E testing:
    - Total hours estimated:
        - 4h
    - Total hours spent
        - 3h
- Code review
    - Total hours estimated:
        -  4h
    - Total hours spent:
        - 2h 15m

**ignore for this spring**
- Technical Debt management:
    - Total hours estimated
    - Total hours spent
    - Hours estimated for remediation by SonarQube
    - Hours estimated for remediation by SonarQube only for the selected and planned issues
    - Hours spent on remediation
    - debt ratio (as reported by SonarQube under "Measures-Maintainability")
    - rating for each quality characteristic reported in SonarQube under "Measures" (namely reliability, security, maintainability )



## ASSESSMENT

- What caused your errors in estimation (if any)?
    1. Ide configuration
    2. Gitignore conflicts
    3. Bugs related to route path syntax
    4. Lack of api documentation and coordination between FE and BE

- What lessons did you learn (both positive and negative) in this sprint?
    1. Docs are important
    2. Handle gitignore with more care
    3. Code review is essential to catch bugs
    4. Define better tasks

- Which improvement goals set in the previous retrospective were you able to achieve?
  This is our first retrospective

- Which ones you were not able to achieve? Why?
  This is our first retrospective

- Improvement goals for the next sprint and how to achieve them (technical tasks, team coordination, etc.)
    1. Write better documentation to align all members of the team.
       **solutions:**
        - We can try to use [tools that generates API in automatic way](https://swagger.io/)
        - Do more meetings between BE - FE
        - Separate the FE API usage in another file like in the [Professor Corno's example](https://github.com/polito-WA1-AW1-2021/BigLab2-solution/blob/main/client/src/API.js)
        - Write some basic UML schemas to get a general idea of the software structure
    2. Try to have more readable code
       **solutions**:
        - Avoid using unnecessary libraries (such as the json mapper in the controller class of the server)
        - Improve the organization of the code (using correctly the spring design pattern: controller/repository/service/entity)
        - Spend more time on code review. The author can try to explain what he has done to 1 or 2 other members of the team

- One thing you are proud of as a Team!!
    - We equally split the amount of work
    - Everyone learnt a new technology (Spring framework) very quickly
    - The presentation really brought us together