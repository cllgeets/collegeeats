#include <bits/stdc++.h>

using namespace std;

bool issafe(int **arr, int x, int y, int n)
{
    if (x < n && y < n && arr[x][y] == 1) // checking if x<n or y<n or if position is open or not.
    {
        return true; // returning true if above condition satisfy
    }
    return false; // returning false if above condition doesnt satisfy
}

bool ratmaze(int **arr, int x, int y, int n, int **solArr)
{
    if (x == n - 1 && y == n - 1) // checking if rat reached its position
    {                             // if yes then
        solArr[x][y] == 1;        // setting that position =1 to get solution array
        return true;              // and returning true
    }

    if (issafe(arr, x, y, n))                  // checking on a position if it is safe to move
    {                                          // if yes then
        solArr[x][y] = 1;                      // creating a dynamic array and storing in it the movment of rat
        if (ratmaze(arr, x + 1, y, n, solArr)) // calling function again for x+1 position and checking
        {
            return true; // returning true if movment is valid.
        }
        if (ratmaze(arr, x, y + 1, n, solArr)) // calling function again for y+1 position and checking
        {
            return true; // returning true of valid
        }
        solArr[x][y] = 0; // if above condition doesnt qualify then that element of array == 0 and rat will not move on that path again
        return false;     // and returning false if condition doesnt qualify
    }
    return false; // returning false if not safe to move.
}

int main()
{
    int n;
    cin >> n;

    int **arr = new int *[n];
    for (int i = 0; i < n; i++)
    {
        arr[i] = new int[n];
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; i < n; j++)
        {
            cin >> arr[i][j];
        }
    }

    int **solArr = new int *[n];

    for (int i = 0; i < n; i++)
    {
        solArr[i] = new int[n];
        for (int j = 0; j < n; j++)
        {
            solArr[i][j] = 0;
        }
    } 

    if (ratmaze(arr, 0, 0, n, solArr))
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                cout << solArr[i][j]<<"  ";
            }
        }
    }
}