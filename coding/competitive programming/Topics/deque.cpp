#include <bits/stdc++.h>

using namespace std;

int main()
{
    deque<int> d;
    d.push_back(1);  // adding an element in last of deque
    d.push_front(2); // adding an element in front of deque

    d.pop_back();  // removing last element from deque
    d.pop_front(); // removing first element from deque

    d.empty();                         // removing every element of deque
    d.erase(d.begin(), d.begin() + 1); // erasing a range of elements in deque(it erases size but not maxsize(means kind of capacity))
}