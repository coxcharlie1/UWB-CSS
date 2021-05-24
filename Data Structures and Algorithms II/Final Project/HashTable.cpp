#include "HashTable.h"

HashTable::HashTable()
{
	this->capacity = 0;
	this->table = new HashedEntry * [0];
}

//Takes in a size and creates an empty hash table
HashTable::HashTable(int size)
{
	this->capacity = size;
	this->table = new HashedEntry * [this->capacity];


	for (int i = 0; i < capacity; i++)
	{
		this->table[i] = nullptr;

	}
}

//Adds data to array based on the hash index
void HashTable::add(int n)
{
	int key = hashIndex(n);

	//IF the entry is empty
	if (this->table[key] == nullptr)
	{

		this->table[key] = new HashedEntry;
		this->table[key]->add(n);


	}
	//Add to the end of the entry's linked list
	else
	{
		this->table[key]->add(n);

	}
}

//Gets the hash index 
int HashTable::hashIndex(int n)
{
	return n % capacity;
}

//Returns the size of the table
int HashTable::size()
{
	return this->capacity;
}

/*
	Gets the entry in the array with the least amount of collisions by
	going through each entry in the array.
*/
int HashTable::minimumCollisions()
{
	int min = this->table[0]->numCollisions();
	for (int i = 0; i < capacity; i++)
	{
		//check if its null and if it's less than the current minimum
		if (this->table[i] != nullptr && this->table[i]->numCollisions() < min)
		{

			min = this->table[i]->numCollisions();


		}

	}
	return min;
}

/*
	Gets the entry in the array with the most amount of collisions by
	going through each entry in the array.
*/
int HashTable::maximumCollisions()
{
	int max = this->table[0]->numCollisions();
	for (int i = 0; i < capacity; i++)
	{
		//check if its null and if it's more than the current maximum
		if (this->table[i] != nullptr && this->table[i]->numCollisions() > max)
		{
			max = this->table[i]->numCollisions();
		}
	}
	return max;
}

/*
	Gets the amount of empty entries in the array by going through 
	each entry and checking if it is null then counting it as a empty
	entry. 
*/
int HashTable::emptyBuckets()
{
	int empty = 0;
	for (int i = 0; i < this->capacity; i++)
	{
		if (table[i] == nullptr)
		{
			empty++;
		}
	}
	return empty;
}

//Display method to test the add method
void HashTable::display()
{
	if (this->table == nullptr)
	{
		cout << "Empty table" << endl;
	}
	else
	{
		for (int i = 0; i < this->capacity; i++)
		{
			cout << "[" << i << "] -> ";
			if (this->table[i] != nullptr)
			{

				this->table[i]->display();

			}


		}

	}
}
