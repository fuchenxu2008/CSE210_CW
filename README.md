# Researcher Statistics Utility

This program is a tool to read and analyze researcher information on ResearchGate in Excel format.

It is capable of computing the following information:

1. Total number of distinct researchers
2. Total number of distinct interests
3. A researcher's information by given name
4. Total number of distinct researchers who have a given interest
5. Total number of distinct researchers who share two given interests
6. Top 5 similar researchers recommendation based on given researcher name

## Environment Setup

- Requirement of Java 8
- Excel dataset put in `/dataset`
- POI packages put in `/lib` (along with `xmlbeans-2.6.0.jar`)

## Get Started

1. Use CommandLine Tool to change directory into the root folder `/`

2. For Mac and Linux user

    ``` Bash
    javac -cp ".:lib/*" src/CSE210/Main.java -Xlint
    java -cp ".:lib/*" src/CSE210/Main
    ```

3. For Windows user

    ``` Bash
    javac -cp ".;lib/*" src/CSE210/Main.java -Xlint
    java -cp ".;lib/*" src/CSE210/Main
    ```

4. On startup, a test will be runned automatically.

5. Type in number 1 - 6 to run differnt task as instructed and 7 to quit.

6. Researcher name and interest should be entered accurately.