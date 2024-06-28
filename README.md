# regToDEA Project

## Overview
This project is a Deterministic Finite Automaton (DFA) simulator developed as part of my coursework at Hochschule Karlsruhe - Technik und Wirtschaft (HKA).
It provides a graphical user interface (GUI) and a backend logic to convert regular expressions into Deterministic Finite Automata (DFA). The project is structured into different packages, each responsible for various functionalities such as GUI management, finite state machine operations, and expression conversion.

## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [functionalities](#functionalities)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE such as IntelliJ IDEA or Eclipse

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/cyanlist/RegToDEA.git
    cd regToDEA
    ```
2. Open the project in your preferred IDE.

## Usage
- Launch the application by running the Main.java class.
- Enter a regular expression in the text field.
- Click the "Convert" button to view the process for creating a DFA

## Functionalities 

### Regular Expression Operations
This project supports several operations on regular expressions to facilitate pattern matching and language recognition. Below are the main operations implemented:
- Concatenation (,) : Matches patterns where the first regular expression is followed directly by the second, demonstrating sequential combination in regular expressions.
  <br> Example: Hello , Word -> "HelloWorld"
- Alternation (|) : Matches either pattern options provided, allowing choices between alternative patterns in regular expressions.
  <br>Example: Hello | World -> "Hello" or "World"
- Kleene Closure (\*) : Matches zero or more occurrences of the preceding regular expression.
  <br>Example: Hello* -> "" or "Hello" or "HelloHello" or ...
- Positive Closure (+) : Matches one or more occurrences of the preceding element in regular expressions.
  <br>Example: World+ -> "World" or "WorldWorld" or "WorldWorldWorld" or ...
- Parentheses for Grouping: Allows grouping of operations to control precedence and define subexpressions.

## Project Structure
The project is divided into the following main packages:
- `com.github.geje1017.regToDEA.main.gui`: Contains classes for the graphical user interface.
- `com.github.geje1017.regToDEA.main.logic.finiteStateMachine`: Contains classes for finite state machine (FSM) operations.
- `com.github.geje1017.regToDEA.main.logic.postfix`: Contains classes for converting and evaluating regular expressions.

## Future Enhancements

### 1. Graphical User Interface (GUI) Improvements
  - Graph Visualization: Implement graphical visualization capabilities to display DFAs as graphs, enhancing visual appeal, improving process comprehension, and facilitating better overall DFA analysis.
  - Impact: Presently, the application outputs DFAs solely in textual format. Introducing graphical visualization will enable users to visually interpret DFA structures, transitions, and states, significantly enhancing usability by providing a clearer and more intuitive representation of complex automata.
  
### 2. Regular Expression Validation and Error Handling
  - Input Validation: Implement robust validation for regular expressions to handle syntax errors, ambiguous patterns, or unsupported constructs.
  - Impact: Presently, incorrect or malformed regular expressions may lead to unpredictable results or application crashes, affecting user experience and reliability.

### 3. DFA Minimization
  - Automated Minimization: Integrate algorithms to automatically minimize the DFA generated from the regular expression.
  - Impact: Minimization reduces the size and complexity of DFAs, improving efficiency in pattern matching and reducing computational resources required.

### 4. Ensuring DFA Correctness
  - DFA Validation: Implement functionality to ensure the correctness of entered DFAs. Validate DFA properties such as completeness (all transitions defined), determinism (no more than one transition per symbol from each state), and minimality (no redundant states or transitions), providing users with feedback on DFA validity.
  - Impact: Currently, the application accepts user-defined DFAs without verifying their correctness, potentially leading to unintended behavior or incorrect results. By incorporating validation checks, users can confidently create and utilize DFAs knowing they adhere to fundamental automata principles, thereby improving the reliability and accuracy of DFA-based computations and analyses.

### 5. Export and Import Functionality
  - Export DFA: Allow users to export generated DFAs to common formats for further analysis or integration with other tools.
  -  Impact: Current inability to export limits collaboration and integration capabilities, hindering the application's usefulness in larger workflows.
  -  Import Regular Expressions: Enable users to import regular expressions from files or external sources, facilitating batch processing or integration with other systems.
  - Impact: Lack of import functionality restricts flexibility and usability, forcing manual entry of each regular expression.

### 6. Performance Optimization
  - Algorithm Optimization: Improve algorithms for regular expression parsing and DFA generation to handle large inputs efficiently.
  - Impact: Current implementations may struggle with complex or lengthy regular expressions, leading to slow processing times and potential resource exhaustion.
