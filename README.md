# JavaGen
Java Genetic Project

# Goal
The goal of this project was to get more knowledge on how Genetic Algorithms are working, how to optimise them and to learn a little bit of the Java concurrency module.

# What does it do?
This program tries to solve problems by running randomly created solution through a process akin to biological evolution. That means that each solution is treated as an individual in a simulated world and only the fitest will survive. Fitest here means the solution that is as close as possible as the solution we want to find.

Take for instance the N-queen problem. You'd like to fill a NXN chess board with N queens in such a way that none of them attack each other. Sure you can try to place them by trial and error or even running a program that tries to place them in the good spot if N isn't too big, but once you increase the number of queens, you can see how ridiculous that might get. This is where Genetic Algorithm excels.

It starts by creating thousands of random solutions, it then tests them all (that is quick) and asigns a fitness score to each of them. Then, just like in biology, the organism start to reproduce, in technical terms we pass the solutions through genetic operators to determine the next generation of solutions.

For instance, the algorithm will select at random the parents using Tournament Selection of t=2 (see footnote A), then it will "mate" these parents together to produce a child solution. Just like in real genomics, the child chromosome is constructed from copying parts of the parents genome. It is exactly what this algorithm does, it takes parts of the solution of parent 1 and of parent 2. After that, we introduce genetic mutation to keep the population of solution diversified to prevent local minimum.

And it keeps reproducing parents until a maximum amount of child solutions are produced and the child population is then filled with random solutions to keep diversification.

The process starts over until we have reached our solution or we have exceeded the amount of generations.

#Data representation
Each individual can be seen a chromosome. Each chromosome is then composed of genes. These genes are represented by binary numbers(easier to perform genetic mutation). The sequence of these numbers form a solution to a given problem. A collection of these individual is called a pool (sometime refered to as a Population). A pool contains a full generation. That is the architecture of the solutions.

#Helper classes
##Breeder
The breeder class is where all the genetic manipulation is performed. It contains the genetic operators and the selection process. The genetic operators are Crossover and Mutation 
