#define QueueSize 10   //应根据具体情况定义该值

template <class Type> struct CirQueue
{               
	int front;  //头指针，队非空时指向队头元素            
    int rear;  //尾指针，队非空时指向队尾元素的下一位置             
	int count;  //计数器，记录队中元素总数
    Type data[QueueSize];
};

template<class Type> int QueueEmpty(CirQueue<Type> *Q)
{
	return Q->count==0;  //队列无元素为空
}

void Error(char* message)
{
	cout<<message<<endl;
}

template<class Type> Type DeQueue(CirQueue<Type> *Q)
{
	Type temp;
    if(QueueEmpty(Q))
		Error("Queue underflow");     //队空下溢
	temp=Q->data[Q->front];
    Q->count--;                        //队列元素个数减1
    Q->front=(Q->front+1)%QueueSize;   //循环意义下的头指针加1
    return temp; 
}

//取得队列front数据
template<class Type> int QueueFront(CirQueue<Type> *Q)
{
	if(QueueEmpty(Q))
		Error("Queue is empty.");
	return Q->data[Q->front];
}

template<class Type> void InitQueue(CirQueue<Type> *Q)
{
	Q->front=Q->rear=0;
    Q->count=0;     //计数器置0
}

template<class Type> int QueueFull(CirQueue<Type> *Q)
{
	return Q->count==QueueSize;  //队中元素个数等于QueueSize时队满
}


template<class Type> void EnQueue(CirQueue<Type> *Q,Type x)//加入队列
{
	if(QueueFull(Q))                   
	{
		cout<<"when try to input data:"<<x;
		Error("Queue overflow so shrow out");     //队满上溢
		cout<<DeQueue(Q)<<endl;
	}
	Q->count++;                        //队列元素个数加1
	Q->data[Q->rear]=x;                 //新元素插入队尾
	Q->rear=(Q->rear+1)%QueueSize;      //循环意义下将尾指针加1
}
