#include "HashedEntry.h"

HashedEntry::HashedEntry()
{
	this->size = 0;
	
}

//Adds elements to the end of the list
bool HashedEntry::add(int n)
{
	//If the list is empty, create a new head
	if (this->head == nullptr)
	{
		this->head = new Node;
		this->head->data = n;
		this->head->next = nullptr;


	}
	//Go through the list till the end then add
	else
	{
		Node* current = this->head;
		while (current->next != nullptr)
		{
			current = current->next;

		}
		current->next = new Node;
		current->data = n;
		current->next->next = nullptr;



	}
	//Update the size after element has been added
	this->size++;
	return true;
}
//Display's elements in the entry
void HashedEntry::display()
{
	if (this->head == nullptr)
	{
		cout << "Empty" << endl;
	}
	else
	{

		Node* current = this->head;
		while (current->next != nullptr)
		{
			if (current->next->next == nullptr)
			{
				cout << current->data << endl;


			}
			else
			{
				cout << current->data << ", ";


			}
			current = current->next;

		}


	}
}
//Returns the amount of collisions in the entry which is the size - 1
int HashedEntry::numCollisions()
{
	return this->size - 1;
}
