
# Dynamic Analyzer for C

In this project we use [ANTLR](https://www.antlr.org/) to parse and analyze programs writen in C language and show which parts of code executed and which didn't.


## Project Idea and Algorithm
- First we use ANTLR to visit and walk through most possible type of codes that may be writen in C like: 
    * Compound or block statements, functions, struct and union.
    * Labeled statements `case SOME_CONSTANT:`, `SOME_LABEL :` etc.
    * Selection statements `if statements` or `switch statement`.
    * Jump statements `goto`,`break`, `continue`,`return`.
    * Iteration statements `for`,`while`.
    * Generally, we visit each block or line of code that may be executed or not based on some condition and give it an index.
- Then we count those visited nodes.
- Then we add an array of flags with the length of those nodes and inject this array at the beginning of the C code initialized with zeros.
- Then at each node we inject a line of code to set its index in the flag array to be true.
- Then we print the content of the flag array at the end of main function (specifically we print only the index of each true flag).
- Then we compile and run the C file with a subprocess and get the output into the stream buffer.
- Then we iterate over each line of the generated code and put this line in html `<code>` tag and delete our injected code.
- If the line belongs to an node with true flag then we set its color style with green, otherwise set it to red.


## Contributing

#### Thinking there are some errors, some cases or nodes that aren't handled ?

Feel free to open an issue in the issue tracker. Also pull requests are welcomed.

Actually, all contributions are always welcome!
## Installation and Running the project

All configurations are preset and its easy to run the project directly.

- Just download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) 
- Open project folder with IntelliJ.
- Put your C code in a file and call it main.c then put it inside `/Input C Code/` directory.
- Make sure you have [GCC Compiler](https://gcc.gnu.org/) installed.
- Run Test.main().
- Open `/Output HTML/main.html` to see the output.

#### We have put a C example code for you in case you don't have a c code and want only to run the project.
But note that this is a spaghetti C code only Allah knows what it is doing.



    
## Screenshots
**Input Code**

![input](https://github.com/OmarAli3/Dynamic-Analyzer-in-C/blob/master/screenshots/input.JPG)

**Output Html**

![output](https://github.com/OmarAli3/Dynamic-Analyzer-in-C/blob/master/screenshots/output.JPG)

  
## Acknowledgements

 We would like to thank **Eng. Ahmed Bakr** for his guidance and support through the project.

## Authors

>* Omar Ali : [@OmarAli3](https://github.com/OmarAli3)
>* Mohammad Massoud : [@massoudsalem](https://github.com/massoudsalem)
>* Mohammad Ashraf : [@elhedeq](https://github.com/elhedeq)
>* Khalid Mahmoud : [@Khalid-MahmouD](https://github.com/Khalid-MahmouD)
