#include <bits/stdc++.h>
using namespace std;

typedef struct S
{
    int x;
    int y;
} Op;

int n, m;
int Nthmin = 0, j = 0, i;

void printArr(int arr[], int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << "  ";
    }
}

int main()
{
    cout << "n = ";
    cin >> n;
    cout << "m = ";
    cin >> m;

    int A[n];

    S Opr[m];

    int O[m];
    int index[n];

    cout << "A: ";
    for (int i = 0; i < n; i++)
    {
        cin >> A[i];
    }

    cout << "Opr : ";
    for (int i = 0; i < m; i++)
    {
        cin >> Opr[i].x;
        cin >> Opr[i].y;
    }

    for (int i = 0; i < m; i++)
    {
        int A_temp[n];
        for (int j = 0; j < n; j++)
        {
            int power = pow(10, Opr[i].y);
            int hey = A[j] / power;
            A_temp[j] = hey;
        }

        for (int i = 0; i < n; i++)
        {
            int min = A_temp[0];
            int idxj = 0;
            for (int j = 1; j < n; j++)
            {
                if (A_temp[j] < min)
                {
                    min = A_temp[j];
                    idxj = j;
                }
            }
            index[i] = idxj;
            A_temp[idxj] = INT_MAX;
        }

        O[i] = index[Opr[i].x - 1];
    }

    cout << "O = [ ";
    for (int i = 0; i < m - 1; i++)
    {
        cout << O[i] << "  ";
    }
    cout << O[m - 1];
    cout << " ]";
}
