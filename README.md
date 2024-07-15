# Search-Visualization

Search-Visualization is a framework that can be used to implement and visualize AI algorithms. The Search-Visualization uses two perspectives to represent the algorithmic behavior. On the left hand side a search tree is drawn that shows which search node was expanded by the algorithm. On the right hand side the problem with a representation of the state is shown.
AI algorithms and search threads are independent of each other. The visualization is done via an entity component system.


## Features:

* Provides Classes for AI and search algorithms
* Representation and implementation of two games for AI to work with
* Construction and representation of search trees according to the algorithm data structure
* GUI with control elements for visualization control 
* Administrators can view all pictures on one page with the user inserted data
* Pausing and resuming algorithms
* Representation and implementation of two MapColoringProblems (Australia and General)
* Abstracted classes for CSP problems and CSP search algorithms


## Dependencies:

* Java 17
* JavaFX 21.0.2


## Installation:

* Install Java17
	* https://www.java.com/de/download/manual.jsp
* Install JDK 
	* https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
* Install an IDE (e.g. IntelliJ Community Edition)
	* https://www.jetbrains.com/idea/download/?section=windows
* Install JavaFX libary into IDE
	* https://www.jetbrains.com/help/idea/javafx.html#create-project
	* https://openjfx.io/openjfx-docs/
* Import src folder into your IDE
* Run the `Main.java` class in the `application` package

You should see the following:

![empty search-visualization winow](https://1drv.ms/i/s!AtIGvMOY6z5JjoQAo3szOKchZX04pw?e=sx5qOT)
*Picture 1:  Empty window*

Select a problem and a search-algorithm and press the START-Button. Press the STEP-Button to proceed the visualization.
![proceeding search-visualization winow](https://snirps.ddns.net/public/search-visualization/proceed.PNG)
*Picture 2: Depth first search*


## Implement your own AI-Algorithm:

Navigate to the `ai_algorithm` package inside go to `search` create a new class with your own algorithm name e.g. `DepthFirstSearchExplored `. 

![ai algorithm package](https://snirps.ddns.net/public/search-visualization/ai_algorithm-package.png)
*Picture 3: Package organization*

```java
package ai_algorithm.search;

import ai_algorithm.ExploredSet;
import ai_algorithm.Frontier;
import ai_algorithm.Path;
import ai_algorithm.SearchNode;
import ai_algorithm.problems.State;
import application.debugger.Debugger;

public class DepthFirstSearchExplored extends SearchAlgorithm {
	@Override
	public Path search() {
		SearchNode start = new SearchNode(null, problem.getInitialState(), 0, null);
		Frontier frontier = new Frontier();
		ExploredSet explored = new ExploredSet();
		explored.add(start);
		Debugger.pause();
		if (this.problem.isGoalState(start.getState())) {
			return start.getPath();
		}
		frontier.add(start);
		Debugger.pause();
		while (!frontier.isEmpty()) {
			SearchNode node = frontier.removeLast();
			Debugger.pause();
			System.out.println(node);
			for (SearchNode child : node.expand()) {
				State state = child.getState();
				if (problem.isGoalState(state)) {
					Debugger.pause("Finished");
					return child.getPath();
				}
				if (!explored.contains(state)) {
					Debugger.pause();
					explored.add(child);
					frontier.add(child);
				}
			}
		}
		return null;
	}
}
```
As a last step you have to announce your algorithm to the framework by adding the algorithms name to the algorithm `SearchAndProblemRegister`.
```java
public class SearchAndProblemRegister {
...
	public static String[] searchAlgorithms = { //
			DepthFirstSearch.class.getName(), //
			DepthFirstSearchExplored.class.getName(), //
			RecursiveDepthSearch.class.getName(), //
			BreadthFirstSearch.class.getName(), // <<<your new algorithm
			BidirectionalBreadthFirstSearch.class.getName(), //
			ManualSearch.class.getName()//
	};
...
}
```
Start the framework and select your new algorithm.
The result of the algorithm is shown in picture 2.


## How does it work:

Like many Game Engines the Search-Visualization framework is based on an **Entity-Component-Sytem**. Therefore it uses **Game-Objects** to represent all Objects that are part of a search algorithm. The components can be give to a Game-Object to add functionality.
![EntityComponentSystem](https://snirps.ddns.net/public/search-visualization/EntityComponentSystem.svg)
*Picture 3: Components are assigned to Game-Objects*

The visualization is seperated into two threads the **Search-Thread** and the **Visualization-Thread**.  The connecting element between them are **visitors** that are managed by the **Game-Object-Registry** to be applied on the Game-Objects.
![FrameworkeArchitecture](https://snirps.ddns.net/public/search-visualization/GameObjekt-Lebenszyklus_klein.svg)
*Picture 4: Broad architecture of the framework*

For more information on this topic read the full documentation: [full java doc](https://snirps.ddns.net/public/search-visualization/JavaDoc/).


## Results

Here are some pictures generated by the Search-Visualization:

### Depth First Search
![DepthFirstSearch](https://snirps.ddns.net/public/search-visualization/Algorithmen/Tiefensuche.PNG)

### Depth First Search with Explored Set
![DepthFirstSearchExploredSet](https://snirps.ddns.net/public/search-visualization/Algorithmen/Tiefensuche%20mit%20ExploredSet.PNG)

### Recursive Depth Search
![FrameworkeArchitecture](https://snirps.ddns.net/public/search-visualization/Algorithmen/Rekursive%20Tiefensuche.PNG)

### Breadth First Search
![FrameworkeArchitecture](https://snirps.ddns.net/public/search-visualization/Algorithmen/Breitensuche.PNG)

### Bidirectional Breadth First Search
![FrameworkeArchitecture](https://snirps.ddns.net/public/search-visualization/Algorithmen/Bidirektional.PNG)

### Manual Search
![FrameworkeArchitecture](https://snirps.ddns.net/public/search-visualization/Algorithmen/Manuelle-suche.PNG)



## To-Do's

* [ ] FIX: Sliding Tile Problem Visitors
* [ ] FIX: Overlapping search nodes in some trees 
* [ ] CHANGE: CSS Theme
* [ ] CHANGE: Userinterface
* [ ] ADD: More problems/games
* [ ] ADD: More search algorithms 

