#pragma once
#include"Fund.h"
#include<string>
#include<vector>
#include<iostream>
using namespace std;
class Account
{
	friend ostream& operator<<(ostream&, const Account&);
public:
	//Constructors
	Account();
	Account(string name, int id);

	//getters, setters
	string getName();
	int getID();
	double getFundBalance(int fundNumber);

	void setName(string newName);
	void setID(int newID);
	

	//actions
	int FundAmount(int fundNumber);
	void Deposit(int fundType, int amount);
	void Withdraw(int fundType, int amount);
	void Transfer(int fundTypeFrom, int amount, int fundTypeTo);
	//errors should go here as well
	void History();
	void History(int fund);

	//overloads
	Account& operator=(Account& acc);
private:
	string name;
	int id;
	int totalAmount;
	vector<Fund> funds = {};


};

