#pragma once
#include"Account.h"
#include<iostream>
using namespace std;
class Transaction
{
	//Prints out the transaction information to console
	friend ostream& operator<<(ostream&, const Transaction);
public:
	//Constructors
	Transaction();
	Transaction(vector<string> transaction);
	//For opening accounts
	Transaction(char transType, string accName, int accID);
	//For deposits and withdraws
	Transaction(char transType, int accID, int fundType, int amount);
	//For transfers
	Transaction(char transType, int accID, int fundTypeFrom, int amount, int fundTypeTo);
	Transaction(char transType, int accID);
	

	//getters, setters
	vector<string>& getTransactionInfo();
	char getTransactionType();
	string getAccountName();
	int getAccountID();
	int getFund();
	int getFundFrom();
	int getFundTo();
	int getAmount();
	

	

	//Actions

	

	//overloads
	Transaction& operator=(const Transaction& rhs);
	
private:
	vector<string> transactionInfo; 
	char transactionType;
	string name;
	int id;
	int amount;
	int fund;
	int fundFrom;
	int fundTo;
	
	


};

