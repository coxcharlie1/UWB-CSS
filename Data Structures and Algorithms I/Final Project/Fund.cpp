#include "Fund.h"

Fund::Fund()
{

}
Fund::Fund(int fundType)
{
	switch (fundType)
	{
	case 0: 
		this->fund = "Money Market";
		break;

	case 1: this->fund = "Prime Money Market";
		break;

	case 2: this->fund = "Long-Term Bond";
		break;

	case 3: this->fund = "Short-Term Bond";
		break;

	case 4: this->fund = "500 Index Fund";
		break;

	case 5: this->fund = "Capital Value Fund";
		break;

	case 6: this->fund = "Growth Equity Fund";
		break;

	case 7: this->fund = "Growth Index Fund";
		break;

	case 8: this->fund = "Value Fund";
		break;

	case 9: this->fund = "Value Stock Fund";
		break;

	//default: this->fund = "";

	}
	this->amount = 0;
	this->fundNum = fundType;

}

//Getters, Setters
string Fund::getFund()
{
	return this->fund;
}

int Fund::getFundNum()
{
	return this->fundNum;
}

void Fund::setAmount(int newAmount)
{
	this->amount = newAmount;
}

int Fund::getAmount()
{
	return this->amount;
}

vector<int> Fund::getDeposits()
{
	return this->deposits;
}

vector<int> Fund::getWithdraws()
{
	return this->withdraws;
}

vector<vector<int>> Fund::getTransfers()
{
	return this->transfers;
}

bool Fund::HasHistoryDeposit()
{
	return this->getDeposits().size() != 0;
}

bool Fund::HasHistoryWithdrawl()
{
	return this->getDeposits().size() != 0;
}

bool Fund::HasHistoryTransfers()
{
	return this->getTransfers().size() != 0;
}

void Fund::DepositHistory(int id)
{
	vector<int> d = this->getDeposits();

	for (int i = 0; i < d.size(); i++)
	{
		cout << " D " << id << this->getFundNum() << " " << d[i] << endl;
	}
}

void Fund::WithdrawlHistory(int id)
{
	
	vector<int> w = this->getWithdraws();
	for (int i = 0; i < withdraws.size(); i++)
	{
		// << "Withdrawling" << endl;
		cout << " W " << id << this->getFundNum() << " " << withdraws[i] << endl;
	}
}

void Fund::TransferHistory(int id)
{
	vector<vector<int>> t = this->getTransfers();

	for (int i = 0; i < t.size(); i++)
	{
		cout << " T " << id << this->getFundNum() << " " << t[i][0] << " " << id << t[i][0] << endl;
	}
}

//adds deposits in Fund to a vector
void Fund::AddDepsit(int a)
{
	this->deposits.push_back(a);
	this->setAmount(this->getAmount() + a);
	
	
}

void Fund::AddWithdraw(int a)
{

	this->withdraws.push_back(a);
	this->setAmount(this->getAmount() - a);
	
}

void Fund::AddTransfer(int amount, int fundTo)
{
	vector<int> newTransfer = {fundTo, amount};
	this->transfers.push_back(newTransfer);
	this->setAmount(this->getAmount() - amount);
	

}

Fund& Fund::operator=(Fund& theFund)
{
	this->amount = theFund.amount;
	this->fund = theFund.fund;
	this->deposits = theFund.deposits;
	this->withdraws = theFund.withdraws;
	this->transfers = theFund.transfers;
	this->fundNum = theFund.fundNum;
	return *this;
}

ostream& operator<<(ostream& theStream, const Fund &theFund)
{
	theStream << theFund.fund << ": " << "$"<< theFund.amount;
	return theStream;
}
