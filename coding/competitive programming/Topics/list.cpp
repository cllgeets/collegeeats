#include <bits/stdc++.h>
#include <list>

using namespace std;

int main()
{
    list<int> l;

    list<int> n(l);  //copying previous list in the new list

    l.push_back(1);
    l.push_front(2);

    for (int i : l)
    {
        cout << i << " ";
    }

    cout << endl;

    l.erase(l.begin());
}