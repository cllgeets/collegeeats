#include <bits/stdc++.h>
#include <vector>

using namespace std;

int main()
{
    vector<int> a(5, 1); // creating default size vector initialized with 1
    vector<int> v;       // vector doubles its size whenever a new element is added
    v.push_back(1);      // adding new element in vector
    v.push_back(2);
    v.push_back(3);

    cout << "capacity ->  " << v.capacity() << endl; // printing capacity of vector of storing elements
    cout << "size -> " << v.size() << endl;          // printing no. of elements in vector

    cout << "front -> " << v.front() << endl; 
    cout << "back -> " << v.back() << endl;

    v.pop_back(); // remove last element of vector

    cout << "element -> " << v.at(1)<<endl;  //printing element at 1 index

    for (int i : v)
    {
        cout << i << "  " << endl;
    }

    v.clear(); // clear every element of vector
}