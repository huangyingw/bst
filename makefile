run : avltree.o
	g++ -o run avltree.o
avltree.o : avltree.cpp avltree.h
	g++ -c avltree.cpp
clean :
	rm run avltree.o
