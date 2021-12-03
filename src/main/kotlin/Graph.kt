class Graph{
	val vertex = mutableListOf<String>()
	val edge = mutableMapOf<Pair<String, String>, Int>()

	fun checkEdges(): Boolean{
		edge.forEach {
			if(it.key.first !in vertex || it.key.second !in vertex){
				return false
			}
		}
		return true
	}

	fun getMinimumRecoverTree(startVertex: String): Node?{
		if(!checkEdges()){
			return null
		}
		val res = Node(startVertex)
		val vertexTree = mutableListOf(startVertex)
		val vertexTmp = vertex.toMutableList()
		var min: Int
		var vG: String
		var vT: String
		vertexTmp.remove(startVertex)
		while(vertexTmp.isNotEmpty()){
			min = Int.MAX_VALUE
			vG = ""
			vT = ""
			vertexTree.forEach { vTree ->
				vertexTmp.forEach { vGraph ->
					edge[Pair(vTree, vGraph)]?.let {
						if(it < min){
							vG = vGraph
							vT = vTree
							min = it
						}
					}
				}
			}
			vertexTmp.remove(vG)
			vertexTree.add(vG)
			res.addTo(vT, vG)
		}
		return res
	}
}