
public class BinTreeNode {
		public:
			int deep;//�ڵ����ڵĲ��
			BinTreeNode *leftChild,*rightChild;//����Ů������Ů���
			int data;//�����
	        BinTreeNode():deep(0),leftChild(NULL),rightChild(NULL){}
			BinTreeNode(int item,BinTreeNode*left=NULL,
			BinTreeNode*right=NULL):data(item),leftChild(left),
			rightChild(right){}
			
			int GetData()const{return data;}//ȡ�ý�������ֵ
			
			BinTreeNode* GetLeft()const{return leftChild;}//ȡ�ý�������Ůָ��ֵ
			
			BinTreeNode* GetRight()const{return rightChild;}//ȡ�ý�������Ůָ��ֵ
			
			void SetData(const int &item){data=item;}//�޸Ľ�������ֵ
			
			void SetLeft(BinTreeNode*L){leftChild=L;}//�޸Ľ�������Ůָ��ֵ
			
			void SetRight(BinTreeNode*R){rightChild=R;}//�޸Ľ�������Ůָ��ֵ
		private:
			
			
}
