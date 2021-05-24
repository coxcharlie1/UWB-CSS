#pragma once
#include<string>
#include<queue>
#include<iostream>
#include <vector>
using namespace std;
class Fund
{
	//Prints out the fund and amount to console
	friend ostream& operator<<(ostream&, const Fund&);
public:
	//Constructors
	Fund();
	Fund(int fundType);


	//getters, setters
	//returns a string version of the fund
	string getFund();
	int getFundNum();
	void setAmount(int newAmount);
	int getAmount();

	vector<int> getDeposits();
	vector<int> getWithdraws();
	vector<vector<int>> getTransfers();

	//Actions
	//if there were actions regarding the fund
	bool HasHistoryDeposit();
	bool HasHistoryWithdrawl();
	bool HasHistoryTransfers();
	//Returns the history regarding the fund
	void DepositHistory(int id);
	void WithdrawlHistory(int id);
	void TransferHistory(int id);
	//Add a deposit to record
	void AddDepsit(int amount);
	void AddWithdraw(int amount);

	//adds transfer from this acount
	void AddTransfer(int amount, int fundTo);

	
	//overloads
	Fund& operator=(Fund& theFund);


private:
	string fund;
	int amount;
	int fundNum;
	//queue that keeps track of each transaction within 
	//the fund
	vector<int> deposits;
	vector<int> withdraws;
	vector<vector<int>> transfers;
};

