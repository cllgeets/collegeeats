#include <bits/stdc++.h>

using namespace std;

int main()
{

    // METHOD 1

    // int r, c;
    // cin >> r >> c;

    // char s[r][c];

    // int num = 0;
    // for (int i = 0; i < r; i++)
    // {
    //     for (int j = 0; j < c; j++)
    //     {
    //         cin >> s[i][j];
    //         if (s[i][j] == 'S')
    //         {
    //             num++;
    //         }
    //     }
    // }

    // int rows = 0;
    // int col = 0;

    // for (int i = 0; i < r; i++)
    // {
    //     int dot = 0;
    //     for (int j = 0; j < c; j++)
    //     {
    //         if (s[i][j] == 'S')
    //         {
    //             dot = 1;
    //             break;
    //         }
    //     }
    //     if (dot == 0)
    //     {
    //         rows++;
    //     }
    // }

    // for (int i = 0; i < c; i++)
    // {
    //     int dot = 0;
    //     for (int j = 0; j < r; j++)
    //     {
    //         if (s[j][i] == 'S')
    //         {
    //             dot = 1;
    //             break;
    //         }
    //     }
    //     if (dot == 0)
    //     {
    //         col++;
    //     }
    // }

    // cout << rows * c + col * r - rows*col;

    // METHOD 2

    // int dot = 0;

    // for (int i = 0; i < r; i++)
    // {
    //     for (int j = 0; j < c; j++)
    //     {
    //         if (s[i][j] == 'S')
    //         {
    //             rows++;
    //             break;
    //         }
    //     }
    // }

    // for (int j = 0; j < c; j++)
    // {
    //     for (int i = 0; i < r; i++)
    //     {
    //         if (s[i][j] == 'S')
    //         {
    //             col++;
    //             dot -= i;
    //             break;
    //         }
    //         else
    //         {
    //             dot++;
    //         }
    //     }
    // }

    // cout << r * c - rows * col;

    // almost completed.
    // only catch is that non eatable cells are multiply of rows and column with strawberry
}