#include <bits/stdc++.h>

using namespace std;

int main()
{
    stack<string> s;
    s.push("praveen");
    s.push("kumar");
    s.push("trillionaire"); //toppest element in stack

    cout << "top element : " << s.top() << endl;  //printing top element from stack
    s.pop(); // removing last element from stack
}