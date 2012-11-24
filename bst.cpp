#include <fstream>
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
		void SetData(const int &item){data=item;}//�޸Ľ������ֵ
		void SetLeft(BinTreeNode*L){leftChild=L;}//�޸Ľ������Ůָ��ֵ
		void SetRight(BinTreeNode*R){rightChild=R;}//�޸Ľ������Ůָ��ֵ
	private:
};

class BinaryTree
{
	public:
		bool addLayer;//����Ƿ����Ӳ�
		int leftHeight;//BST������߶�
		int rightHeight;//BST�����Ҹ߶�
		int height;//BST�����ܸ߶�
		int currentHeight;//��ǰ�ĸ߶�
		BinTreeNode* root;//�������ĸ�ָ��
		BinaryTree():root(NULL){fout.open("output.txt");}//���캯��
		BinaryTree(int value):RefValue(value),root(NULL){fout.open("output.txt");}
		virtual ~BinaryTree(){destroy(root);fout.close();}
		
		
		virtual int IsEmpty(){return root==NULL ? 1:0;}//�ж������շ�
		
		virtual BinTreeNode*Parent(BinTreeNode*current)//����˫�׽���ַ
		{
			return root==NULL||root==current?NULL:Parent(current);
		}

		
		virtual BinTreeNode* LeftChild(BinTreeNode*current)//��������Ů����ַ
		{
			return root!=NULL? current->leftChild:NULL;
		}

		virtual BinTreeNode* RightChild(BinTreeNode*current)//��������Ů����ַ
		{	
			return root!=NULL?current->rightChild:NULL;
		}
		
		virtual void Insert(BinTreeNode *&current,const int &item);//������Ԫ��
		void Remove(int x,BinTreeNode* &ptr);//ɾ���ڵ�
		BinTreeNode* Min(BinTreeNode* ptr);//��ptr����������Ѱ��С���,����ָ��
		virtual int Find(const int& item)const{return 0;}//����Ԫ��
		
		
		const BinTreeNode* GetRoot()const {return root;}//ȡ��
		int PrintBSTHor(BinTreeNode*current,ostream&out);//��ӡ��BST����ͼ
		int PrintBSTVer(BinTreeNode* aa);
		void SetHeight(BinTreeNode* ptr,int height);//���õ�ǰ�ڵ�������ӽڵ�ĸ߶�
		friend istream &operator >> (istream &in,BinaryTree&Tree);
		friend ostream &operator << (ostream &out ,BinaryTree&Tree);
	private:
		int RefValue;//��������ֹͣ�ı�־
		BinTreeNode* parent(BinTreeNode*start,BinTreeNode* current);
		void Traverse(BinTreeNode* current,ostream& out)const;
		void destroy(BinTreeNode* current);
		ofstream fout;
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

int BinaryTree::PrintBSTHor(BinTreeNode*current,ostream&out)//��ӡ��BST����ͼ
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

BinTreeNode* BinaryTree::Min(BinTreeNode* ptr)//��ptr����������Ѱ��С���,����ָ��
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

void BinaryTree::SetHeight(BinTreeNode* ptr,int height)//���õ�ǰ�ڵ�������ӽڵ�ĸ߶�
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
			Remove(x,ptr->leftChild);//����������ִ��ɾ��
		}
		else if(x>ptr->data)
		{
			Remove(x,ptr->rightChild);//����������ִ��ɾ��
		}
		else if(ptr->leftChild!=NULL&&ptr->rightChild!=NULL)//ptrָʾ�ؼ���Ϊx�Ľ�㣬����������Ů
		{
			temp=Min(ptr->rightChild);//��ptr->rightChild����Ѱ��С���
			ptr->data=temp->data;//�øý�����ݴ�����������
			Remove(ptr->data,ptr->rightChild);//����������ɾ���ý��
		}
		else//ptrָʾ�ؼ���Ϊx�Ľ�㣬��ֻ��һ���������Ů
		{
			temp=ptr;
			if(ptr->leftChild==NULL)
			{
				ptr=ptr->rightChild;//ֻ������Ů
				SetHeight(ptr,temp->deep);
				
			}
			else if(ptr->rightChild==NULL)
			{
				ptr=ptr->leftChild;//ֻ������Ů
				SetHeight(ptr,temp->deep);
			}
			delete temp;
		}
	}
}

void BinaryTree::Insert(BinTreeNode *&current,const int &item)//����㷨��ָ��ķ�����д�úܺã�Ҫ�ú��о�
{
	if(current==NULL)//����currentָ���������ĸ����
	{
		current=new BinTreeNode(item);
		return ;
    }
	if(item<current->data)//������������
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
	//ɾ��45,ȱ������������Ů�
	
	/*tree.Insert(tree.root,53);
	tree.Insert(tree.root,17);
	tree.Insert(tree.root,78);
	tree.Insert(tree.root,9);
	tree.Insert(tree.root,45);
	tree.Insert(tree.root,65);
	tree.Insert(tree.root,87);
	tree.Insert(tree.root,23);*/
	

	//ɾ��78��ȱ������������Ů�
	/*tree.Insert(tree.root,53);
	tree.Insert(tree.root,17);
	tree.Insert(tree.root,78);
	tree.Insert(tree.root,9);
	tree.Insert(tree.root,23);
	tree.Insert(tree.root,94);
	tree.Insert(tree.root,88);*/

	//ɾ��78�������������������һ������
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