#pragma once
#include<string>
#include<iostream>
#include<fstream>
#include"Transaction.h"
#include"BSTree.h"
#include<queue>

using namespace std;

class Bank
{
	friend ostream& operator<<(ostream& theStream, const Bank &bank);
public:
	//have assignment overload
	//copy constructor
	Bank();
	Bank(string filename);

	void ReadInTransactions(string fileName);
	void ProcessTransactions();
	void Display();

private:
	string bankData;
	queue<Transaction> transQueue;
	BSTree bst;



};

