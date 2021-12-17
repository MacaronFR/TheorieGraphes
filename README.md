# Implementation en Kotlin d'algorithme de la théorie des graphes v0.2
## Détails

Ce repo réuni l'implémentation de plusieurs algorithmes de la théorie des graphes en Kotlin
À des fins de facilité, certaines classes représentant différentes structure de données sont implémenté tel que les Graph représentant des graphes orienté ou non ou les Node représentant des nœuds d'un arbre.

### Graph
```kotlin
val graph = Graph() //Création d'un graphe vide
graph.initVertex{
	('A'..'C').map{
		it.toString() //Initialise des sommets de A à C
    }
}
graph.vertex.add("Bonjour") // Ajoute un sommet "Bonjour"
graph.addEdge("From", "To", 3) // Ajoute l'arrête de From vers To avec un poids de 3
graph.addEdge(Edge("From", "To2", 5)) //Ajoute l'arrête de From vers To2 avec un poids de 5 via un Objet Edge
graph.edgeFromAdjacency() //Retourne la liste des arrêtes de graph sous forme d'une List<Edge>
graph connectedClassOf "From" //Retourne la classe de connexité du graph en partant de "From"
graph.isConnected() // Retourne un booléen en fonction de si le graphe est connexe ou non
```

### Node
```kotlin
val tree = Node("A") //Créer une Node de nom A sans parents donc c'est une racine
tree.isRoot() //Vrai
tree.addTo("A", "B") // Ajoute l'enfant B à A
tree.addTo("B", "C") // Ajoute l'enfant C à B via A
tree.print() //Affiche l'arbre
tree.searchChild("B") //Cherche l'enfant dans l'arbre partant de tree
```

## Jàrnik-Prim
Une implémentation en Kotlin de l'algorithme de recherche d'arbre recouvrant minimum sur un graphe pondéré non orienté de Jàrnik-Prim.  
Plus connu sous le nom algorithme de Prim, il fut découvert un certain temps avant Prim par Jàrnik.  
Il s'agit donc de trouver l'arbre couvrant d'un graphe pondéré non orienté via cet algorithme.  Celui ci prend un sommet de départ de l'arbre. Ensuite tant que l'arbre n'est pas recouvrant (tant que des points du graphes sont encore hors de l'arbre), l'algorithme cherche l'arrête possédant un sommet appartenant à l'arbre et un sommet ne lui appartenant pas ayant le poid minimum. Il ajoute cette arrrete à l'arbre.  
On se retrouve donc avec un arbre ayant récupérer les arrêtes minimum entre chaque point du graphe.

### Utilisation

```kotlin
//avec graph un graphe non orienté pondéré et connexe
val tree = graph.getMinimumRecoverTree("A") //tree est de type Node? car en cas d'échec (graphe non connexe) la fonction renvoie null
tree?.print() //Affichage de l'arbre
```

## Kurskal

Une implémentation en Kotlin de l'algorithme de Kruskal de recherche d'arbre recouvrant minimum dans un graphe non orienté pondéré.  
Il s'agit de trouver l'arbre recouvrant minimum en créant un graphe comportant tous les sommets du graphe d'origine, mais aucune arrête.  
Puis pour chaque arrête par ordre croissant, si cette arrête ne créer pas de cycle ou joint deux composantes connexes du graphe résultant, alors on l'ajoute au résultat
Lorsque le résultat est connexe ou le nombre d'arrêtes est égal à l'ordre du graphe - 1, le graphe résultant est un arbre recouvrant minimum.