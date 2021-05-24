#include "Account.h"
#include "Fund.h"

Account::Account()
{
	this->name = "";
	this->id = 0;
	
}

Account::Account(string name, int id)
{
	this->name = name;
	this->id = id;
	
	for (int i = 0; i <= 9; i++)
	{
		Fund newFund = Fund(i);
		this->funds.push_back(newFund);
	}
	
}

string Account::getName()
{
	return this->name;
}

int Account::getID()
{
	return this->id;
}

void Account::setName(string newName)
{
	this->name = newName;
}

void Account::setID(int newID)
{
	this->id = newID;
}

//Gets amount within fund based on fund number
int Account::FundAmount(int fundNumber)
{
	
	return funds[fundNumber].getAmount();
}

void Account::Deposit(int fundType, int amount)
{
	this->funds[fundType].AddDepsit(amount);
	this->totalAmount += amount;
}

void Account::Withdraw(int fundType, int amount)
{
	//cout << "Fund: " << this->funds[fundType] << endl;
	//Case for withdrawing too much

	if (this->funds[fundType].getAmount() < amount)
	{
		//if it is not a linked account
		if (fundType > 3)
		{
			cerr << "ERROR: Not enough funds to withdraw " << amount << " from "
				<< this->getName() << " " << this->funds[fundType].getFund()<< endl;
		}
		//Money Market and Prime Money Market
		else if (fundType <= 1)
		{
			if (fundType == 0)
			{
				if (this->FundAmount(0) + this->FundAmount(1) > amount)
				{

					int a = this->FundAmount(0) + this->FundAmount(1);
					this->funds[0].setAmount(0);
					this->funds[1].AddWithdraw(a - amount);

				}
				if (this->FundAmount(1) >= amount)
				{

					this->funds[1].AddWithdraw(amount);
					this->totalAmount -= amount;
				}
			}


			else if (fundType == 1)
			{
				if (this->FundAmount(0) + this->FundAmount(1) > amount)
				{
					int a = this->FundAmount(0) + this->FundAmount(1);
					this->funds[0].setAmount(0);
					this->funds[1].AddWithdraw(a - amount);
					this->totalAmount -= amount;
				}
				if (this->FundAmount(0) >= amount)
				{

					this->funds[0].AddWithdraw(amount);
					this->totalAmount -= amount;
				}
			}
			else
			{
				cerr << "ERROR: Not enough funds to withdraw " << amount << " from "
					<< this->getName() << " " << this->funds[fundType].getFund() << endl;

			}
		}
		
		
			
		//Long-Term Bond and Short-Term Bond
		else if(fundType > 1 && fundType < 4)
		{
			if (fundType == 2)
			{
				if (this->FundAmount(2) + this->FundAmount(3) > amount)
				{

					int a = this->FundAmount(2) + this->FundAmount(3);
					this->funds[2].setAmount(0);
					this->funds[3].AddWithdraw(a - amount);

				}
				if (this->FundAmount(3) >= amount)
				{

					this->funds[3].AddWithdraw(amount);
					this->totalAmount -= amount;
				}
			}
			if (fundType == 3)
			{
				if (this->FundAmount(2) + this->FundAmount(3) > amount)
				{

					int a = this->FundAmount(2) + this->FundAmount(3);
					this->funds[3].setAmount(0);
					this->funds[2].AddWithdraw(a - amount);

				}
				if (this->FundAmount(2) >= amount)
				{

					this->funds[2].AddWithdraw(amount);
					this->totalAmount -= amount;
				}
			}
		
			else
			{
				cerr << "ERROR: Not enough funds to withdraw " << amount << " from "
					<< this->getName() << " " << this->funds[fundType].getFund() << endl;

			}

		}

	}
	else
	{
	this->funds[fundType].AddWithdraw(amount);
	this->totalAmount -= amount;
	}
}

void Account::Transfer(int fundTypeFrom, int amount, int fundTypeTo)
{
	//Add cases for linked accounts
	//If one is transferring between two linkedfunds (say, 0 and 1) 
	//then the transaction should only succeed if the fund being withdrawn
	//from has enough funds. 
	//No other types of accounts are handled in this manner.

	int amountInFundFrom = this->FundAmount(fundTypeFrom);
	int amountInFundTo = this->FundAmount(fundTypeTo);

	if (amountInFundFrom < amountInFundTo)
	{
		//if it is not a linked account
		if (fundTypeFrom >= 4 && fundTypeTo >= 4 || (fundTypeFrom == 0 && fundTypeTo == 1) || 
			(fundTypeFrom == 1 && fundTypeTo == 0) || (fundTypeFrom == 2 && fundTypeTo == 3) || 
			(fundTypeFrom == 3 && fundTypeTo == 2))
		{
			cerr << "ERROR: Not enough funds to transfer " << amount << " from "
				<< this->getName() << " " << this->funds[fundTypeFrom].getFund() << " to " 
				<< this->funds[fundTypeTo].getFund() << endl;
		}
		//If its the money market or prime account to another account
		else if (fundTypeFrom <= 1)
		{
			if (fundTypeFrom == 0 && this->FundAmount(1) >= amount)
			{
				this->funds[1].AddTransfer(amount, fundTypeTo);
				
			}

			if (fundTypeFrom == 1 && this->FundAmount(0) >= amount)
			{
				this->funds[0].AddTransfer(amount, fundTypeTo);
				
			}
			//If the amount in the linked fund is not enough
			else
			{
				cerr << "ERROR: Not enough funds to withdraw " << amount << " from "
					<< this->getName() << " " << this->funds[fundTypeFrom].getFund() << endl;

			}

		}
		//Long-Term Bond and Short-Term Bond
		else
		{
			if (fundTypeFrom == 2 && this->FundAmount(3) >= amount)
			{
				this->funds[3].AddTransfer(amount, fundTypeTo);

			}

			if (fundTypeFrom == 3 && this->FundAmount(2) >= amount)
			{
				this->funds[3].AddTransfer(amount, fundTypeTo);

			}
			//If the amount in the linked fund is not enough
			else
			{
				cerr << "ERROR: Not enough funds to withdraw " << amount << " from "
					<< this->getName() << " " << this->funds[fundTypeFrom].getFund() << endl;

			}

		}

	}
	else
	{
		this->funds[fundTypeFrom].AddTransfer(amount, fundTypeTo);
		this->funds[fundTypeTo].setAmount(this->funds[fundTypeTo].getAmount() + amount);
	}

}


void Account::History()
{
	cout << "Transaction History for " << this->getName() << " by fund." << endl;
	for (int i = 0; i <= 9; i++)
	{
		Fund theFund = this->funds[i];
		//If there is history within that fund
		cout << theFund << endl;
		if (theFund.HasHistoryDeposit())
		{
			
			//FundHistory()
			//cout << "Withdrawling" << endl;
			theFund.DepositHistory(this->getID());

		}
		if (theFund.HasHistoryWithdrawl())
		{
			//cout << this->funds[i] << endl;
			theFund.WithdrawlHistory(this->getID());
		}
		
		if (theFund.HasHistoryTransfers())
		{
			theFund.TransferHistory(this->getID());

		}
		
	
	}
}

void Account::History(int fund)
{
	cout << "Transaction History for " << this->getName() << 
		" " << this->funds[fund].getFund() << ":"<< endl;

	Fund theFund = this->funds[fund];
	//If there is history within that fund
	cout << theFund << endl;
	if (theFund.HasHistoryDeposit())
	{

		//FundHistory()
		//cout << "Withdrawling" << endl;
		theFund.DepositHistory(this->getID());

	}
	if (theFund.HasHistoryWithdrawl())
	{
		//cout << this->funds[i] << endl;
		theFund.WithdrawlHistory(this->getID());
	}

	if (theFund.HasHistoryTransfers())
	{
		theFund.TransferHistory(this->getID());

	}
}

Account& Account::operator=(Account& acc)
{
	// TODO: insert return statement here
	this->funds = acc.funds;
	this->totalAmount = acc.totalAmount;
	this->name = acc.name;
	this->id = acc.id;
	return *this;
}

ostream& operator<<(ostream& theStream, const Account &a)
{
	// TODO: insert return statement here
	theStream << a.name << " " << "Account ID: " << a.id << endl;
	for (int i = 0; i <= 9; i++)
	{
		Fund theFund = a.funds[i];
		//If there is history within that fund
		theStream << theFund << endl;

	}
	return theStream;
}
