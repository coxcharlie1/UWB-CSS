#pragma once
#include "Account.h"
#include<iostream>
using namespace std;
class BSTree
{
	friend ostream& operator<<(ostream& theStream, const BSTree& tree);
public:
	//Constructors
	BSTree();
	BSTree(const BSTree& tree);
	~BSTree();

	//getters, setters
	int getCount() const;

	//Actions
	bool Insert(const int &key, Account *theAccount);
	


	// retrieve object, first parameter is the ID of the account
	// second parameter holds pointer to found object, NULL if not found
	bool Retrieve(const int &key, Account * &accountPtr) const;

	

	//Search(const int key) const;
	// displays the contents of a tree to cout
	bool Remove(const int key);

	//Inorder traversal
    void Display() const;
	void Empty(); 
	bool isEmpty() const; 

	//Overloads
	//BSTree& operator=(const BSTree & rhs);
private:
	
	struct Node{ 
		Account *pAcct;
		Node *right;
		Node* left;
	};
	int count;
	Node *root; 
	bool Insert(const int& key, Account *theAccount, Node* root);
	bool Search(const int &key, Account*& accountPtr, Node* root) const;
	void Display(Node* root) const;
	void Empty(Node* root);
};

