#include "BSTree.h"

BSTree::BSTree()
{
	this->count = 0;
	
}

BSTree::BSTree(const BSTree& tree)
{
	*this = tree;

}

BSTree::~BSTree()
{
	this->Empty();
}

int BSTree::getCount() const
{
	return this->count;
}

bool BSTree::Insert(const int& key, Account *theAccount)
{
	
	if (this->root != nullptr)
	{
		Insert(key, theAccount, this->root);
	}
	else
	{
		
		this->root = new Node;
		this->root->pAcct = theAccount;
		this->root->left = nullptr;
		this->root->right = nullptr;
	}
	return true;
	
	
	
}
bool BSTree::Insert(const int& key, Account *theAccount, Node* root)
{
	//If the key is less than the current root node
	if (key < root->pAcct->getID())
	{
		//If the left node is empty
		if (root->left != nullptr)
		{
			Insert(key, theAccount, root->left);
			
		}
		else
		{
			root->left = new Node;
			root->left->pAcct = theAccount;
			root->left->left = nullptr;
			root->right->right = nullptr;
			
		}

	}
	//If the key is greater than the current root node
	else if (key > root->pAcct->getID())
	{
		//If the right node is empty
		if (root->right != nullptr)
		{
			
			Insert(key, theAccount, root->right);
		}
		else
		{
			root->right = new Node;
			root->right->pAcct = theAccount;
			root->right->left = nullptr;
			root->right->right = nullptr;
			
		}
	}

		return true;

	
	
}

bool BSTree::Retrieve(const int &key, Account*& accountPtr) const
{
	return Search(key, accountPtr, this->root);
}

//Retrieve helper
bool BSTree::Search(const int &key, Account* &accountPtr, Node* rt) const
{
	if (rt != nullptr)
	{
		Account a = *rt->pAcct;
		if (key == a.getID())
		{
			accountPtr = rt->pAcct;
			return true;
		}
		if (key < a.getID())
		{
			return Search(key, accountPtr, rt->left);
		}
		else
		{
			return Search(key, accountPtr, rt->right);
		}
	}
	return false;
}



bool BSTree::Remove(const int key)
{
	return false;
}

void BSTree::Display() const
{
	Display(this->root);

}
void BSTree::Display(Node* root) const
{
	if (root != nullptr)
	{
		Display(root->left);
		cout << *(root->pAcct) << endl;
		Display(root->right);
	}
}

void BSTree::Empty(Node* root)
{
	if (root != nullptr)
	{
		Empty(root->left);
		Empty(root->right);
		delete root->pAcct;
		delete root;
	}
}

void BSTree::Empty()
{
	this->Empty(this->root);
}

bool BSTree::isEmpty() const
{
	return this->root == nullptr;
}

