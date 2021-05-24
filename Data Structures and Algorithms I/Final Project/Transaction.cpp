#include "Transaction.h"

Transaction::Transaction()
{
}

Transaction::Transaction(vector<string> transaction)
{
	this->transactionInfo = transaction;
}

//For opening accounts
Transaction::Transaction(char transType, string accName, int accID)
{
	this->transactionType = transType;
	this->name = accName;
	this->id = accID;
	
}

Transaction::Transaction(char transType, int accID, int fundType, int amount)
{
	this->transactionType = transType;
	this->id = accID;
	this->fund = fundType;
	this->amount = amount;
}
Transaction::Transaction(char transType, int accID, int fundTypeFrom, int amount, int fundTypeTo)
{
	this->transactionType = transType;
	this->id = accID;
	this->fundFrom = fundTypeFrom;
	this->amount = amount;
	this->fundTo = fundTypeTo;
}

Transaction::Transaction(char transType, int accID)
{
	this->transactionType = transType;
	this->id = accID;
}


vector<string>& Transaction::getTransactionInfo()
{
	return this->transactionInfo;
}

char Transaction::getTransactionType()
{
	return this->transactionType;
}

string Transaction::getAccountName()
{
	return this->name;
}

int Transaction::getAccountID()
{
	return this->id;
}

int Transaction::getFund()
{
	return this->fund;
}

int Transaction::getFundFrom()
{
	return this->fundFrom;
}

int Transaction::getFundTo()
{
	return this->fundTo;
}

int Transaction::getAmount()
{
	return this->amount;
}

Transaction& Transaction::operator=(const Transaction& rhs)
{
	if (this == &rhs)
	{
		return *this;
	}
	this->name = rhs.name;
	this->id = rhs.id;
	this->fund = rhs.fund;
	this->fundFrom = rhs.fundFrom;
	this->fundTo = rhs.fundTo;
	this->amount = rhs.amount;
	return *this;

}

ostream& operator<<(ostream& theStream, const Transaction t)
{
	// TODO: insert return statement here
	return theStream;
}
