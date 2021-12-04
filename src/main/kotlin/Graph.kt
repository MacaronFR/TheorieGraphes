/**
 * Class that represent a graph
 * You can access and modify [vertex] with [Graph].[vertex] attribute
 * You can initialize [vertex] with a [List] of [String]
 * To add an edge use [addEdge] method
 * If the graph is oriented specify value <b>true</b> to param <b>oriented</b> of [addEdge] method
 * @author MacaronFR
 */
class Graph{

	/**
	 * Represent an Edge of the Graph
	 * @author MacaronFR
	 */
	data class Edge(
		var weight: Int,
		var vertexFrom: String,
		var vertexTo: String
	){
		infix fun assign(newValue: Edge){
			weight = newValue.weight
			vertexFrom = newValue.vertexFrom
			vertexTo = newValue.vertexTo
		}
	}

	val vertex = mutableListOf<String>()
	private val adjacency = mutableMapOf<String, MutableMap<String, Int>>()

	/**
	 * Init the vertex with a lambda that return a [List]
	 * @param init The lambda to execute that return [List]
	 */
	fun initVertex(init: () -> List<String>){
		init().forEach {
			vertex.add(it)
		}
	}

	/**
	 * Add edge to the graph
	 * Must be between 2 existing vertex
	 * @param from The starting vertex of the edge (important if the graph is oriented)
	 * @param to The ending vertex of the edge (important if the graph is oriented)
	 * @param weight The weight of the edge
	 * @param oriented If false, make an edge from [from] to [to] AND from [to] to [from]
	 * @return false if the edge is invalid, true otherwise
	 */
	fun addEdge(from: String, to: String, weight: Int, oriented: Boolean = false): Boolean{
		if(from !in vertex || to !in vertex){
			return false
		}
		if(adjacency[from] == null){
			adjacency[from] = mutableMapOf()
		}
		adjacency[from]!![to] = weight
		if(!oriented){
			if(adjacency[to] == null){
				adjacency[to] = mutableMapOf()
			}
			adjacency[to]!![from] = weight
		}
		return true
	}

	fun addEdge(newEdge: Edge, oriented: Boolean = false): Boolean = addEdge(newEdge.vertexFrom, newEdge.vertexTo, newEdge.weight, oriented)

	/**
	 * Get the minimum recovering tree of the graph from [startVertex] using the Jàrnik-Prim algorithme
	 * @param startVertex The starting vertex of the tree
	 * @return The root node of the tree
	 */
	fun getMinimumRecoverTreeJarnikPrim(startVertex: String): Node{
		val res = Node(startVertex)
		val vertexTree = mutableListOf(startVertex)
		val vertexTmp = vertex.toMutableList()
		val save = Edge(Int.MAX_VALUE, "", "")
		vertexTmp.remove(startVertex)
		while(vertexTmp.isNotEmpty()){
			save assign Edge(Int.MAX_VALUE, "", "")
			vertexTree.forEach { vTree ->
				adjacency[vTree]?.forEach {vNear ->
					if(vNear.key in vertexTmp && vNear.value < save.weight){
						save assign Edge(vNear.value, vTree, vNear.key)
					}
				}
			}
			vertexTmp.remove(save.vertexTo)
			vertexTree.add(save.vertexTo)
			res.addTo(save.vertexFrom, save.vertexTo)
		}
		return res
	}
}