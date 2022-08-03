# Test Task Intellias

## Task submission instruction and requirements

### Task submission instruction:

1. Register on GitHub (if not registered yet)
2. Create a new **private** repository with name firstName_secondName (i.e. ivan_ivaniv)
3. On your local machine create a **Maven** project.
4. Init git repo in your root project directory: '*git init*'
5. Add remote origin: '*git remote add origin https://github.com/xyz/ivan_ivaniv.git*'
6. Make changes in your project
7. Check that your project is compiled (use maven commands for that)
8. Commit and push changed files
9. Go to repository **Settings -> Manage access -> Invite a collaborator**
and invite **imentor-review** user
10. Send an email with:
    - First name
    - Last name
    - Email address that was used when registered to the program
    - Link to the GitHub repository

### Test project requirements:

1. Project should be built with Java 1.8+ or scala 2.12
Hints: use ***maven.compiler.source*** and ***maven.compiler.target*** properties to set version
2. Code should be properly formatted according to the Java code conventions
3. Any other libraries and frameworks could be used
4. mvn test — should run all tests, if tests are presented in the project
Hint: do not forget about **maven-surefire-plugin** to run the tests
5. mvn exec:java — should execute project's Main method, if any exists
6. The project execution should be in two commands: 'git clone ...', 'mvn ...'
7. Any configurations and resources should be bundled in the git repository or maven 'pom.xml'

### From algorithm point of view:

1. For simplicity we can say there is no duplicated keys in each separate collection.
2. Hint: start with simple approach and then think about optimizations.

Create JUnit tests for all implementations.

Junit test should be executed by mvn test command.

### Additional hints:

1. You need simple understanding of Java Generics to complete this task.
2. You might notice that join operation is similar to SQL join operations. Yes, that's true, recall SQL it might help you to resolve the task!
3. Do not forget to test corner cases in your JUnit tests.
4. If you are stuck just make an assumption and implement the task based on it. Later we can discuss this on interview

### Brief description of the implementation of the task:

1. For the concept hash-join in RDBMS, which allows you to quickly join collections. 
2. In terms of time complexity it will be O(n), if you don't count the resources needed to create the hash table. 
3. The hash table is built next to the smaller table, to consume less resources. Depending on the type of join, the same concepts were created with different implementations. 
4. At some points it was possible to speed up a possible merge, provided the key implements the Comparable interface. After such modification there would be chances related to creation of an index or a possible merge-join, which has almost the same time complexity O(nlogn).
5. Also, by the condition you can notice that we have no duplicates and merge-join condition is equality, which means that nested loop connection will be quite unprofitable solution with time complexity O(n^2).

### Thank you for your attention!


