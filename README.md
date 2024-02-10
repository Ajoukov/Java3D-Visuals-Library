Run:
```
javac Display.java
java Display
```

Manage your program through Display.java.

First, create your display class by using the ```new Display()``` constructor.

Your program will already begin dislaying 3D objects after running main. All you have to do to make modifications is edit the Game and EntityManager classes.

To add 3D objects, create objects through the included BasicEntityBuilder class and add them to your EntityManager class objects ArrayList.

For example, in EntityManager, run:
```
IEntity e = BasicEntityBuilder.createObstacle(1);
this.gamePieces.add(0, e);
```
This will create a prebuilt "Obstacle" object and add it to the EntityManager's contained objects list.

To create a custom object, create sub-entities using built-in constructors and combine them into one by using:
```
BasicEntityBuilder.combine(IEntity... entities)
```

Other built-in object constructors include:
```
BasicEntityBuilder.createPlayer(false) // facing away
BasicEntityBuilder.createPlayer(true) // facing forward
BasicEntityBuilder.createStadium()
BasicEntityBuilder.createCoin(int loc) // loc={0:left, 1:middle, 2:right}
BasicEntityBuilder.createLongRect(Color c, double s, double x, double y, double z, double m)
BasicEntityBuilder.createCube(Color c, double s, double x, double y, double z)
BasicEntityBuilder.createBall()
BasicEntityBuilder.createBall(int loc) //loc={0:left, 1:middle, 2:right}
BasicEntityBuilder.createPlane(int loc) //loc={0:left, 1:middle, 2:right}
BasicEntityBuilder.createDiamond(int loc) //loc={0:left, 1:middle, 2:right}
BasicEntityBuilder.createCloud(int loc)
BasicEntityBuilder.createBarrel(Color c, double s, double x, double y, double z)
```

To reset the entities list, run the following from your EntityManager object:
```
this.reset();
```

The cursor, click, and arrow key functions are set in the Display class.

<!--![alt text](https://github.com/Ajoukov/ChessAI/blob/main/Chess.png?raw=true)-->
