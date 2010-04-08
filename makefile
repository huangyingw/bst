run : bst.o
	g++ -o run bst.o
bst.o : bst.cpp
	g++ -c bst.cpp
clean :
	rm run bst.o
