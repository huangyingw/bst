
#include<iostream>
#include<math.h>
using namespace std;
#include "/media/myproject/git/c_c++/linux/data_structure/cirQueue/CirQueue.h"
class BinaryTree;
class BinTreeNode
{
	friend class BinaryTree;
	public:
		int deep;
		BinTreeNode *leftChild,*rightChild;
		int data;
        BinTreeNode():deep(0),leftChild(NULL),rightChild(NULL){}
		BinTreeNode(int item,BinTreeNode*left=NULL,
		BinTreeNode*right=NULL):data(item),leftChild(left),
		rightChild(right){}
		
		int GetData()const{return data;}
		
		BinTreeNode* GetLeft()const{return leftChild;}
		
		BinTreeNode* GetRight()const{return rightChild;}
		
		void SetData(const int &item){data=item;}//修改结点数据值
		
		void SetLeft(BinTreeNode*L){leftChild=L;}//修改结点左子女指针值
		
		void SetRight(BinTreeNode*R){rightChild=R;}//修改结点右子女指针值
	private:
		
		
};

class BinaryTree
{
	public:
		bool addLayer;//标记是否增加层
		int leftHeight;//BST树的左高度
		int rightHeight;//BST树的右高度
		int height;//BST树的总高度
		int currentHeight;//当前的高度
		BinTreeNode* root;//二叉树的根指针
		BinaryTree():root(NULL){}//构造函数
		BinaryTree(int value):RefValue(value),root(NULL){}
		virtual ~BinaryTree(){destroy(root);}
		
		
		virtual int IsEmpty(){return root==NULL ? 1:0;}//判二叉树空否
		
		virtual BinTreeNode*Parent(BinTreeNode*current)//返回双亲结点地址
		{
			return root==NULL||root==current?NULL:Parent(current);
		}

		
		virtual BinTreeNode* LeftChild(BinTreeNode*current)//返回左子女结点地址
		{
			return root!=NULL? current->leftChild:NULL;
		}

		virtual BinTreeNode* RightChild(BinTreeNode*current)//返回右子女结点地址
		{	
			return root!=NULL?current->rightChild:NULL;
		}
		
		virtual void Insert(BinTreeNode *&current,const int &item);//插入新元素
		void Remove(int x,BinTreeNode* &ptr);//删除节点
		BinTreeNode* Min(BinTreeNode* ptr);//在ptr的子树中搜寻最小结点,返回指针
		virtual int Find(const int& item)const{return 0;}//搜索元素
		
		
		const BinTreeNode* GetRoot()const {return root;}//取根
		int PrintBSTHor(BinTreeNode*current,ostream&out);//打印出BST的视图
		int PrintBSTVer(BinTreeNode* aa);
		void SetHeight(BinTreeNode* ptr,int height);//设置当前节点的所有子节点的高度
		friend istream &operator >> (istream &in,BinaryTree&Tree);
		friend ostream &operator << (ostream &out ,BinaryTree&Tree);
	private:
		
		int RefValue;//数据输入停止的标志
		BinTreeNode* parent(BinTreeNode*start,BinTreeNode* current);
		void Traverse(BinTreeNode* current,ostream& out)const;
		void destroy(BinTreeNode* current);
};

void BinaryTree::destroy(BinTreeNode* current)
{
}

void BinaryTree::Traverse(BinTreeNode*current,ostream&out)const
{
	if(current!=NULL)
	{
		Traverse(current->leftChild,out);
		out<<current->data<<",";
		Traverse(current->rightChild,out);
	}
}
int	sumDeep;   
int deep;   

int BinaryTree::PrintBSTVer(BinTreeNode* aa)   
{
	deep+=1;   
    sumDeep+=deep;   
    for(int i=0;i<deep;i++)
		cout<<"\t";
	cout<<aa->data<<endl;
	if(NULL!=aa->leftChild)   
    {
		PrintBSTVer(aa->leftChild);   
	}   
	if(NULL!=aa->rightChild)   
    {   
		PrintBSTVer(aa->rightChild);   
	}   
    deep-=1;   
    return deep;   
}   

int BinaryTree::PrintBSTHor(BinTreeNode*current,ostream&out)//打印出BST的视图
{
	CirQueue<BinTreeNode*> Q;
	Q.InitQueue();
	if(NULL!=current)
	{
		Q.EnQueue(current);
		while(!Q.QueueEmpty())
		{
			current=Q.DeQueue();
			if(currentHeight<current->deep)
			{
				currentHeight=current->deep;
				out<<endl;
			}
			out<<current->data<<",";
			if(NULL!=current->leftChild)
			{
				Q.EnQueue(current->leftChild);
			}
			if(NULL!=current->rightChild)
			{
				Q.EnQueue(current->rightChild);
			}
		}
	}
	return 0;
}

BinTreeNode* BinaryTree::Min(BinTreeNode* ptr)//在ptr的子树中搜寻最小结点,返回指针
{
	if(NULL!=ptr)
	{
		if(NULL!=ptr->leftChild)
		{
			Min(ptr->leftChild);
		}
		else
		{
			return ptr;
		}
	}
}

void BinaryTree::SetHeight(BinTreeNode* ptr,int height)//设置当前节点的所有子节点的高度
{
	if(NULL!=ptr)
	{
		ptr->deep=height;
		if(NULL!=ptr->leftChild)
		{
			SetHeight(ptr->leftChild,ptr->deep+1);
		}
		if(NULL!=ptr->rightChild)
		{
			SetHeight(ptr->rightChild,ptr->deep+1);
		}
	}
}
void BinaryTree::Remove(int x,BinTreeNode* &ptr)
{
	BinTreeNode* temp;
	if(NULL!=ptr)
	{
		if(x<ptr->data)
		{
			Remove(x,ptr->leftChild);//在左子树中执行删除
		}
		else if(x>ptr->data)
		{
			Remove(x,ptr->rightChild);//在右子树中执行删除
		}
		else if(ptr->leftChild!=NULL&&ptr->rightChild!=NULL)//ptr指示关键码为x的结点，它有两个子女
		{
			temp=Min(ptr->rightChild);//在ptr->rightChild中搜寻最小结点
			ptr->data=temp->data;//用该结点数据代替根结点数据
			Remove(ptr->data,ptr->rightChild);//在右子树中删除该结点
		}
		else//ptr指示关键码为x的结点，它只有一个或零个子女
		{
			temp=ptr;
			if(ptr->leftChild==NULL)
			{
				ptr=ptr->rightChild;//只有右子女
				SetHeight(ptr,temp->deep);
				
			}
			else if(ptr->rightChild==NULL)
			{
				ptr=ptr->leftChild;//只有左子女
				SetHeight(ptr,temp->deep);
			}
			delete temp;
		}
	}
}

void BinaryTree::Insert(BinTreeNode *&current,const int &item)//这个算法在指针的分配上写得很好，要好好研究
{
	if(current==NULL)//假设current指向现有树的根结点
	{
		current=new BinTreeNode(item);
		return ;
    }
	if(item<current->data)//向左子树插入
	{
		Insert(current->leftChild,item);
	}
	else
	{
		Insert(current->rightChild,item);
	}
}

istream &operator >>(istream& in ,BinaryTree& Tree)
{
	int item;
	cout<<"construct binary tree:"<<endl;
	cout<<"input data(end with"<<Tree.RefValue<<"):";
	in >>item;
	while(item!=Tree.RefValue)
	{
		Tree.Insert(Tree.root,item);
		cout<<"input data(end with"<<Tree.RefValue <<"):";
		in >>item;
    }
	return in;
}

ostream & operator<<(ostream &out,BinaryTree&Tree)
{
	out<<"preorder traversal of bianry tree"<<endl;
	Tree.Traverse(Tree.root,out);
	out<<endl;
	return out;
}

int main()
{
	BinaryTree tree(0);
	tree.leftHeight=tree.rightHeight=0;
	/*tree.Insert(tree.root,53);
	tree.Insert(tree.root,17);
	tree.Insert(tree.root,78);
	tree.Insert(tree.root,9);
	tree.Insert(tree.root,45);
	tree.Insert(tree.root,65);
	tree.Insert(tree.root,87);
	tree.Insert(tree.root,23);
	tree.Insert(tree.root,81);
*/
	//删除45,缺右子树用左子女填补
	
	/*tree.Insert(tree.root,53);
	tree.Insert(tree.root,17);
	tree.Insert(tree.root,78);
	tree.Insert(tree.root,9);
	tree.Insert(tree.root,45);
	tree.Insert(tree.root,65);
	tree.Insert(tree.root,87);
	tree.Insert(tree.root,23);*/
	

	//删除78，缺左子树用右子女填补
	/*tree.Insert(tree.root,53);
	tree.Insert(tree.root,17);
	tree.Insert(tree.root,78);
	tree.Insert(tree.root,9);
	tree.Insert(tree.root,23);
	tree.Insert(tree.root,94);
	tree.Insert(tree.root,88);*/

	//删除78，在右子树上找中序第一个结点填补
	tree.Insert(tree.root,53);
	tree.Insert(tree.root,17);
	tree.Insert(tree.root,78);
	tree.Insert(tree.root,9);
	tree.Insert(tree.root,45);
	tree.Insert(tree.root,65);
	tree.Insert(tree.root,94);
	tree.Insert(tree.root,23);
	tree.Insert(tree.root,81);
	tree.Insert(tree.root,88);

	tree.height=tree.leftHeight>tree.rightHeight? tree.leftHeight:tree.rightHeight;
	tree.currentHeight=0;
	tree.SetHeight(tree.root,0);
	cout<<"before remove:"<<endl;
	tree.PrintBSTHor(tree.root,cout);
	tree.Remove(78,tree.root);
	cout<<"after remove:"<<endl;
	tree.currentHeight=0;
	tree.SetHeight(tree.root,0);
	tree.PrintBSTHor(tree.root,cout);
	
	return 0;
}

