# Dijkstra’s Shortest Path Algorithm in Java

This project implements **Dijkstra’s algorithm** to compute shortest paths in a **weighted graph** using Java. The graph is represented with an **adjacency matrix**, and the program supports both interactive graph traversal and automated shortest-path computation.

The implementation demonstrates graph modeling, path cost accumulation, and classic shortest-path algorithm design.

---

## ✨ Features
- Weighted graph representation using an adjacency matrix
- Interactive traversal with cumulative path cost tracking
- Dijkstra’s algorithm for shortest paths from a chosen source
- Neighbor and edge-cost inspection during traversal
- Console-based interface for controlled testing

---

## 🛠️ Technologies Used
- Java
- Graph algorithms
- Dijkstra’s shortest path algorithm
- Adjacency matrix representation

---

## 📁 Project Structure
```bash
.
├── DijkstrasAlgorithmApp.java    # Main driver program
├── Neighbor.java                 # Graph neighbor / edge representation
├── Sort.java                     # Supporting utilities (if applicable)
└── README.md
```

---

## ⚙️ How It Works
- A graph is loaded from an input file and stored as an **adjacency matrix**
- Users may traverse the graph manually, selecting neighboring nodes and accumulating total path cost
- During traversal:
  - Entering `-1` exits traversal mode
  - Entering `-2` triggers **Dijkstra’s algorithm**, computing the shortest paths from the current node to all other vertices
- The algorithm outputs shortest path costs and routes for comparison against manually chosen paths

This design allows direct comparison between **user-chosen paths** and **optimal shortest paths**.

---

## 🧪 Build & Run
Compile all files:
```bash
javac *.java
```

Run the program:
```bash
java DijkstrasAlgorithmApp
```

---

## 📜 License
This project is licensed under the MIT License. See the LICENSE file for details.

---

## 👤 Author
Hannah G. Simon
