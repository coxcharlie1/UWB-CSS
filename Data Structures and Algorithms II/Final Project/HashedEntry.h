/*
	HashedEntry: This class provides the linked list for each entry in the hash table because 
	it uses separate chaining. This class adds elements to the end if each list entry, and gets the
	ammount of collisions by keeping track of the size of the list. 
*/
#pragma once
#include<iostream>
using namespace std;
class HashedEntry
{
public: 
	
	//Constructor
	HashedEntry();

	//Useful methods
	bool add(int n);

	void display();

	int numCollisions();

private:

	//List nodes for elements in the list
	struct Node
	{
		int data;
		Node* next;


	};

	Node* head;
	int size;


};

