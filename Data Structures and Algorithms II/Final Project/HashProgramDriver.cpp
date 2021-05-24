/*
	Charlie Cox

	CSS 343  

	John Stager

	Final Program(Hashing): This program reads in a data file of numbers and develops a hash 
	table for the data with sizes 10, 11, 23 and 40. The HashTable uses a separate chaining approach
	with an array of linked lists. For each table, the program outputs the size of the array, the 
	minimum amount of of collisions with entries, the maximum amount of collisions with entries and the
	amount of empty entries. 


*/

#include<fstream>
#include"HashTable.h"
#include<string>
#include<iostream>
using namespace std;

int main()
{
	string dataFile;
	cout << "Enter a input file to load into the hash table: " << endl;
	cin >> dataFile;

	ifstream inFile;
	inFile.open(dataFile);


	HashTable tableOne(10);
	HashTable tableTwo(11);
	HashTable tableThree(23);
	HashTable tableFour(40);
	
	string data;

	while (inFile >> data)
	{


		int n = stoi(data);

		

		tableOne.add(n);
		tableTwo.add(n);
		tableThree.add(n);
		tableFour.add(n);


	}
	cout << "-------------------------------Table One--------------------------" << endl;
	tableOne.display();
	cout << endl;
	cout << "Table Size: " << tableOne.size() << endl;
	cout << endl;

	cout << "Minimum Collisions: " << tableOne.minimumCollisions() << endl;
	cout << endl;

	cout << "Maximum Collisions: " << tableOne.maximumCollisions() << endl;
	cout << endl;

	cout << "Amount of Empty Entries: " << tableOne.emptyBuckets();
	cout << endl;
	

	cout << "-------------------------------Table Two--------------------------" << endl;

	tableTwo.display();
	cout << endl;
	cout << "Table Size: " << tableTwo.size() << endl;
	cout << endl;

	cout << "Minimum Collisions: " << tableTwo.minimumCollisions() << endl;
	cout << endl;

	cout << "Maximum Collisions: " << tableTwo.maximumCollisions() << endl;
	cout << endl;

	cout << "Amount of Empty Entries: " << tableTwo.emptyBuckets();
	cout << endl;

	
	cout << "-------------------------------Table Three--------------------------" << endl;
	tableThree.display();
	cout << endl;

	cout << "Table Size: " << tableThree.size() << endl;
	cout << endl;

	cout << "Minimum Collisions: " << tableThree.minimumCollisions() << endl;
	cout << endl;

	cout << "Maximum Collisions: " << tableThree.maximumCollisions() << endl;
	cout << endl;

	cout << "Amount of Empty Entries: " << tableThree.emptyBuckets();
	cout << endl;
	
	
	cout << "-------------------------------Table Four--------------------------" << endl;
	tableFour.display();
	cout << endl;

	cout << "Table Size: " << tableFour.size() << endl;
	cout << endl;

	cout << "Minimum Collisions: " << tableFour.minimumCollisions() << endl;
	cout << endl;

	cout << "Maximum Collisions: " << tableFour.maximumCollisions() << endl;
	cout << endl;

	cout << "Amount of Empty Entries: " << tableFour.emptyBuckets();
	cout << endl;
	

}