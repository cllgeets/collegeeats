#include <bits/stdc++.h>

using namespace std;

int countL(char s[], int n)
{
    int num = 0;
    for (int i = 0; i < n; i++)
    {
        if ((s[i] == 'L'))
        {
            for (int j = i + 1; j < n; j++)
            {
                if ((s[j] == 'R'))
                {
                    num++;
                    break;
                }
            }
        }
    }
    return num;
}

int countLine(char s[], int n)
{
    int num = 0;
    for (int i = 0; i < n; i++)
    {
        if (s[i] == 'L')
        {
            num += i + 1;
        }
        else if (s[i] == 'R')
        {
            num += n - i - 1;
        }
    }
    return num;
}

int main()
{
    int n;
    cin >> n;

    char s[n];

    for (int i = 0; i < n; i++)
    {
        cin >> s[i];
    }

    int num = countL(s, n);
    cout << "value of num : " << num << endl;

    int count = 0;
    for (int i = 0; i < n; i++)
    {
        if (count == num)
        {
            break;
        }

        if (s[i - 1] == 'L')
        {
            char first = s[i - 1];
            for (int j = i - 1; j < n - 1; j++)
            {
                s[j] = s[j + 1];
            }
            s[n - 1] = first;
            count++;
        }
        cout << countLine(s, n) << "   ";
        for (int k = 0; k < n; k++)
        {
            cout << s[k] << " ";
        }
        cout << endl;
    }
    for (int i = 0; i < n - num - 1; i++)
    {
        cout << countLine(s, n) << "   ";
    }
}