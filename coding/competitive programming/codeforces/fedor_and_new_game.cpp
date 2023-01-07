#include <bits/stdc++.h>

using namespace std;

void binaryConvert(int n, vector<int> &v)
{
    if (n == 0)
    {
        return;
    }
    int rem = n % 2;
    binaryConvert(n / 2, v);
    v.push_back(rem);
}

int main()
{
    int n, m, k;
    cin >> n >> m >> k;

    int arr[m];
    for (int i = 0; i < m; i++)
        cin >> arr[i];

    int fedor;
    cin >> fedor;

    vector<int> vfedor;
    binaryConvert(fedor, vfedor);

    int maxdiff = INT16_MIN;
    int frds = 0;
    for (int i = 0; i < m; i++)
    {
        int player = arr[i];
        vector<int> vplayer;
        binaryConvert(player, vplayer);

        int diff = vfedor.size() - vplayer.size();

        if (diff > 0)
        {
            vector<int> vvplayer(diff);
            for (int j = 0; j < vplayer.size(); j++)
            {
                vvplayer.push_back(vplayer.at(j));
            }
            int num = 0;
            for (int j = 0; j < vfedor.size(); j++)
            {
                if (vfedor.at(j) != vvplayer.at(j))
                {
                    num++;
                }
            }
            if (num <= k)
            {
                frds++;
            }
        }
        else
        {
            vector<int> vvfedor(abs(diff));
            for (int j = 0; j < vfedor.size(); j++)
            {
                vvfedor.push_back(vfedor.at(j));
            }
            int num = 0;
            for (int j = 0; j < vplayer.size(); j++)
            {
                if (vvfedor.at(j) != vplayer.at(j))
                {
                    num++;
                }
            }
            if (num <= k)
            {
                frds++;
            }
        }
    }

    cout << frds;
}