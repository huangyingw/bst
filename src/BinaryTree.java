
		class BinaryTree
		{
			public:
				bool addLayer;
				int leftHeight;
				int rightHeight;
				int height;
				int currentHeight;
				BinTreeNode* root;
				BinaryTree():root(NULL){}
				BinaryTree(int value):RefValue(value),root(NULL){}
				virtual ~BinaryTree(){destroy(root);}
				
				
				virtual int IsEmpty(){return root==NULL ? 1:0;}
				
				virtual BinTreeNode*Parent(BinTreeNode*current)
				{
					return root==NULL||root==current?NULL:Parent(current);
				}

				
				virtual BinTreeNode* LeftChild(BinTreeNode*current)
				{
					return root!=NULL? current->leftChild:NULL;
				}

				virtual BinTreeNode* RightChild(BinTreeNode*current)
				{	
					return root!=NULL?current->rightChild:NULL;
				}
				
				virtual void Insert(BinTreeNode *&current,const int &item);
				void Remove(int x,BinTreeNode* &ptr);
				BinTreeNode* Min(BinTreeNode* ptr);
				virtual int Find(const int& item)const{return 0;}
				
				
				const BinTreeNode* GetRoot()const {return root;}
				int PrintBSTHor(BinTreeNode*current,ostream&out);
				int PrintBSTVer(BinTreeNode* aa);
				void SetHeight(BinTreeNode* ptr,int height);
				friend istream &operator >> (istream &in,BinaryTree&Tree);
				friend ostream &operator << (ostream &out ,BinaryTree&Tree);
			private:
				
				int RefValue;
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

		BinTreeNode* BinaryTree::Min(BinTreeNode* ptr)//��ptr����������Ѱ��С����,����ָ�
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

		void BinaryTree::SetHeight(BinTreeNode* ptr,int height)//���õ�ǰ�ڵ��������ӽڵ��ĸ߶
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
					Remove(x,ptr->leftChild);//����������ִ��ɾ�
				}
				else if(x>ptr->data)
				{
					Remove(x,ptr->rightChild);//����������ִ��ɾ�
				}
				else if(ptr->leftChild!=NULL&&ptr->rightChild!=NULL)//ptrָʾ�ؼ���Ϊx�Ľ��㣬����������Ů
				{
					temp=Min(ptr->rightChild);//��ptr->rightChild����Ѱ��С���
					ptr->data=temp->data;//�øý������ݴ������������
					Remove(ptr->data,ptr->rightChild);//����������ɾ���ý��
				}
				else//ptrָʾ�ؼ���Ϊx�Ľ��㣬��ֻ��һ����������Ů
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

		void BinaryTree::Insert(BinTreeNode *&current,const int &item)//�����㷨��ָ���ķ�����д�úܺã�Ҫ�ú��о�
		{
			if(current==NULL)//����currentָ���������ĸ����
			{
				current=new BinTreeNode(item);
				return ;
		    }
			if(item<current->data)//�����������
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

		public static void main(String[] args) {
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

			//ɾ��78��������������������һ�������
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


	}
