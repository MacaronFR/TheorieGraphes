fun main(args: Array<String>) {
	val graph = getGraph()
	val tree = graph.getMinimumRecoverTree("A")
	tree.print()
}

fun getGraph(): Graph{
	val graph = Graph()
	graph.vertex.add("A")
	graph.vertex.add("B")
	graph.vertex.add("C")
	graph.vertex.add("D")
	graph.vertex.add("E")
	graph.vertex.add("F")
	graph.vertex.add("G")
	graph.edge[Pair("A", "B")] = 5
	graph.edge[Pair("A", "C")] = 4
	graph.edge[Pair("B", "C")] = 10
	graph.edge[Pair("B", "D")] = 2
	graph.edge[Pair("B", "E")] = 2
	graph.edge[Pair("C", "E")] = 8
	graph.edge[Pair("C", "F")] = 5
	graph.edge[Pair("D", "E")] = 1
	graph.edge[Pair("D", "G")] = 6
	graph.edge[Pair("E", "F")] = 3
	graph.edge[Pair("F", "G")] = 6
	return graph
}