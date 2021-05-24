/*
	HashTable: The HashTable class provides all the functionality of a hash table data
	structure. It takes in a size for the hash table and creates the array of linked lists
	according to the size passed in. The class adds elements according to the hash the element
	is evaluated to. Also provides the size of the array, minimum amount of collisions, maximum amount
	of collisions and the amount of empty entries. 
*/
#pragma once
#include"HashedEntry.h"
#include<iostream>
using namespace std;
class HashTable
{
public:

	//Constructors
	HashTable();
	
	HashTable(int size);

	void add(int n);

	//Useful methods
	int hashIndex(int n);

	int size();

	int minimumCollisions();

	int maximumCollisions();

	int emptyBuckets();

	void display();

private:
	HashedEntry** table; //Array of linked list pointers

	int capacity; //size of array

	
};

