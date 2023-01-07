
#include <bits/stdc++.h>

using namespace std;


class Graph {
    int A;

    list<int> *adj;

    bool isCyclicUtil(int mem, bool visting[], int par);

public:
    Graph(int A);        


        void addEdge(int mem, int n); 

    bool isTree();
};


Graph::Graph(int A){
    
    this->A = A;

    adj = new list<int>[A];
}

void Graph::addEdge(int mem, int n)
{
   
    adj[mem].push_back(n);


    adj[n].push_back(mem);
}




bool Graph::isCyclicUtil(int mem, bool visting[], int par){

    visting[mem] = true;

    list<int>::iterator i;
    for (i = adj[mem].begin(); i != adj[mem].end(); ++i)
    {

        if (!visting[*i])
        {
            if (isCyclicUtil(*i, visting, mem))
                return true;
        }
        else if (*i != par)

            return true;
    }

    return false;
}



bool Graph::isTree(){
    bool *visting = new bool[A];
    for (int i = 0; i < A; i++)
        visting[i] = false;

    if (isCyclicUtil(0, visting, -1))
        return false;

    for (int u = 0; u < A; u++)
        if (!visting[u])
            return false;

    return true;
}

int main()
{

    int n;

    cin >> n;

    Graph k1(n);

    for (int j = 0; j < n - 1; j++)
    {
        int x, y;

        cin >> x >> y;

        k1.addEdge(x, y);
    }

    k1.isTree() ? cout << "Yes, tree is possible" << endl : cout << "Tree is not possible" << endl;

    return 0;
}