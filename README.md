# l2s4-projet-2024

Vous devez *forker* ce projet dans votre espace de travail Gitlab (bouton `Fork`) et vidéo sur le [portail](https://www.fil.univ-lille.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)
Un unique fork doit être réalisé par équipe.

Une fois cela réalisé, supprimer ces premières lignes et remplissez les noms des membres de votre équipe.
N'oubliez pas d'ajouter les autres membres de votre équipe aux membres du projet, ainsi que votre enseignant·e (statut Maintainer).

# Equipe

- Evan Joly
- Gabriel Kreutser
- Theo Nave
- Wissal-Asma Harrat

# Sujet

[Le sujet 2024](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2024.pdf)

# Livrables

## Livrable 1

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 2

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

# Journal de bord

## Semaine 1

Objectif de la séance : Modéliser le plateau de jeu sous forme UML  

Remarque : Toutes les informations et caractéristiques de l'UML sont susceptibles de changer par la suite pour une meilleure implémentation du jeu    

Nous avons commencé par réfléchir avant toute chose aux éléments qui constituent le plateau de jeu (la ville).
Premièrement, le plateau est constitué de cases qui permettent d'accueillir les différents éléments du jeu. C'est pour cela qu'on a décidé d'établir la classe abstraite "Cell".  
Une case a pour propriétés :  

* Les joueurs, définis par une liste de joueurs
* Les zombies qui, comme pour les joueurs, sont définis par une liste de zombies
* La direction de la porte définie par une Map contenant une direction et une porte (on y reviendra plus tard)
* Sa position qu'on définit avec des coordonnées x, y
* Un type qui permettra d'identifier le rôle de la case

Pour les méthodes de la classe, on a pour l'instant créé ce qui nous semble le plus logique pour des cellules, comme addPlayer(), addZombie(), removePlayer(), removeZombie(), une méthode canLook() pour savoir s'il est possible de voir l'intérieur de la case et différents getters.  

Maintenant qu'on a l'élément principal, on peut s'intéresser aux éléments plus spécifiques.
Pour ce faire, on va utiliser l'héritage. Dans le jeu, nous avons deux éléments qui se distinguent pour le plateau : la rue et les salles. On crée donc deux classes qui héritent toutes les deux de Cell, la classe "Street" et la classe "Room".  

Pour la classe Room, un élément est différent des autres cellules, c'est la présence d'objets. Room aura donc pour propriété une liste d'objets (représentée par un objet Item) et aura des méthodes en lien avec cette propriété comme des add, remove, getter.   

La classe Street n'a rien de plus que Cell, excepté qu'elle héritera d'une classe "Sewer" qui permettra la production de zombies à chaque manche de jeu.  

Un élément important du jeu est l'existence de salles spéciales, le Continental et la pharmacie, qui ont des caractéristiques différentes des autres salles.  

Le Continental empêche l'attaque entre les acteurs et empêche les personnes de voir ce qu'il y a à l'intérieur. On crée donc une classe "Continental" qui hérite de Room.  

La pharmacie, quant à elle, permet aux joueurs de récupérer des fioles de guérison.
De plus, à chaque fois qu'un joueur entre dans cette pièce, une fiole est générée. On crée donc l'item "HealingVial" qu'on pourra utiliser pour la classe DrugStore qui hérite de Room. Elle a donc une liste de fioles en propriété avec les méthodes qui l'accompagnent : add, remove, getter.  

Revenons maintenant à la propriété direction de la classe Cell. Pour définir une porte à chaque direction, nous avons utilisé une Map qui regroupe les éléments d'une classe d'énumération "Direction" contenant les éléments : North, South, East, West, et une porte provenant d'une classe Door qui a des propriétés et des méthodes indiquant s'il est possible de "casser" la porte, c'est-à-dire si la porte est en bordure de plateau, on peut casser la porte menant vers le vide.  

Pour finir, nous avons commencé à réfléchir à la création de la classe "Board" possédant sa méthode initBoard() et de la classe "TestBoard".  

**Répartition du travail :**  
Evan Joly : Création et écriture de la classe astraite "Cell" + Exception  
Gabriel Kreutser : Création et écriture des classes Direction, Door, Board, TestBoard, Player, Zombie  
Théo Nave : Création diagramme UML, écriture des classes Street, Sewer et rédaction compte rendu Semaine 1  
Wissal-Asma Harrat : Création et écriture des classes Room, DrugStore, Continental, Item, HealingVial

Diagramme UML Semaine 1 : [https://nextcloud.univ-lille.fr/index.php/s/jBCi5HAjN92PnnY](https://nextcloud.univ-lille.fr/index.php/s/jBCi5HAjN92PnnY)

## Semaine 2

## Semaine 3

## Semaine 4

## Semaine 5

## Semaine 6

## Semaine 7

## Semaine 8

## Semaine 9

## Semaine 10

## Semaine 11

## Semaine 12
