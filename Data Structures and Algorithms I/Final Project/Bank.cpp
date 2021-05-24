#include "Bank.h"
#include"BSTree.h"
#include"Account.h"
#include"Transaction.h"
#include<fstream>
#include <sstream>
#include <algorithm>
#include <iterator>
#include<string>
#include<iostream>
using namespace std;


Bank::Bank()
{
	this->bankData = " ";
}

Bank::Bank(string filename)
{
	this->bankData = filename;
	
}

void Bank::ReadInTransactions(string fileName)
{
	this->bankData = fileName;
	ifstream f1;
	f1.open(bankData);
	
	if (f1.is_open())
	{
		//cout << "File is open" << endl;
		while (!f1.eof())
		{
			
			//Read in transaction
			string transaction;
			getline(f1, transaction);

			//Split string
			vector<string> result;
			stringstream ss(transaction);
			string token;
			while (getline(ss, token, ' ')) {
				result.push_back(token);
			}
			//Create transactions out of vector
			Transaction t;
			
			char transType = result[0].at(0);
			if (transType == 'O')
			{
				//new open account transaction
				string accName = result[1] + " " + result[2];
				int accID = stoi(result[3]);
			
				Transaction t(transType, accName, accID);
				this->transQueue.push(t);
				
			}
			if (transType == 'D' || transType == 'W')
			{
				//new deposit or withdraw account transaction
				int accID = stoi(result[1].substr(0, 4));
			
				int fund = stoi(result[1]) % 10;
				int amount = stoi(result[2]);

				Transaction t(transType, accID, fund, amount);
				this->transQueue.push(t);
			}
			if (transType == 'T')
			{
				//new deposit or withdraw account transaction
				int accID = stoi(result[1].substr(0, 4));
			
				int fundFrom = stoi(result[1]) % 10;
				int amount = stoi(result[2]);
				int fundTo = stoi(result[3]) % 10;

				//Put history of this in both accounts
				Transaction t(transType, accID, fundFrom, amount, fundTo);
				this->transQueue.push(t);
			}
			if (transType == 'H')
			{
				//new open account transaction
				int accID = stoi(result[1]);

				Transaction t(transType, accID);
				this->transQueue.push(t);

			}
						
			result.clear();

		}
		
	}
	f1.close();
}
//Process queue, add to binary search tree
void Bank::ProcessTransactions()
{
	
	while (this->transQueue.size() != 0)
	{
		
		Transaction t = transQueue.front();	
		char transType = t.getTransactionType();
		//Account a;
		if (transType == 'O')
		{

			Account a(t.getAccountName(), t.getAccountID());

			//add to BSTree
			Account* pAcc = new Account(a);
			if (this->bst.Retrieve(t.getAccountID(), pAcc) == true)
			{

				cerr << "ERROR: Account " << t.getAccountID() << " is already open. " << endl;
			}


			else
			{
				bst.Insert(t.getAccountID(), pAcc);

				//delete pAcc;
			}
		}
				
		if (transType == 'D')
		{
		
			int fundType = t.getFund();
			
		    int amount = t.getAmount();
			Account* pAcc;
			if (this->bst.Retrieve(t.getAccountID(), pAcc) == true)
			{
				//Account &theAccount = *pAcc;
				
				pAcc->Deposit(fundType, amount);

			}
			else
			{
				cerr << "ERROR: Account " << t.getAccountID() << " not found. " << "Deposit refused." << endl;
			}
			
			

		}
		if (transType == 'W')
		{
			int fundType = t.getFund();
			int amount = t.getAmount();
			//Withdraw from to Account fund
			Account* pAcc;
			if (this->bst.Retrieve(t.getAccountID(), pAcc))
			{		

				pAcc->Withdraw(fundType, amount);

			}
			else
			{
				cerr << "ERROR: Account " << t.getAccountID() << " not found. " << "Withdrawl refused." << endl;
			}

		}
		if (transType == 'T')
		{
			int fundTypeFrom = t.getFundFrom();
			int fundTypeTo = t.getFundTo();
			int amount = t.getAmount();
			//Transfer from to Account fund
			Account* pAcc;
			if (this->bst.Retrieve(t.getAccountID(), pAcc))
			{
				//Account theAccount = *pAcc;

				pAcc->Transfer(fundTypeFrom, amount, fundTypeTo);

			}
			else
			{
				cerr << "ERROR: Account " << t.getAccountID() << " not found. " << "Transferal refused." << endl;
			}

		}
		if (transType == 'H')
		{
			//Display History of Account
			Account* pAcc;
			if (t.getAccountID() > 9999)
			{
				if (this->bst.Retrieve(t.getAccountID() / 10, pAcc))
				{

					pAcc->History(t.getAccountID() % 10);
				}
				else
				{
					cerr << "ERROR: Account " << t.getAccountID() << " history not found. " << endl;
				}

			}
			else
			{
				if (this->bst.Retrieve(t.getAccountID(), pAcc))
				{
					pAcc->History();

				}
				else
				{
					cerr << "ERROR: Account " << t.getAccountID() << " history not found. " << endl;
				}

			}
			

		}
		this->transQueue.pop();
		
	}
	cout << "Processing Done. " << endl;
}

void Bank::Display()
{
	this->bst.Display();
}