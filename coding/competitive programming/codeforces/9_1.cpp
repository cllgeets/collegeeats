#include <bits/stdc++.h>
#define MAX_N 300
#define INF 987654321
using namespace std;
typedef long long lld;
int visited[1000];


int dfs(vector<int> ad[], int v, int src)
{

    visited[src] = 1;
    for (int i = 0; i < ad[src].size(); i++)
    {
        if (visited[ad[src][i]] == -1)
        {
            visited[ad[src][i]] = 1;
            dfs(ad, v, ad[src][i]);
        }
    }


}



int dist[MAX_N][MAX_N];

int main()
{
    for (int i = 0; i < MAX_N; i++)
    {
        for (int j = 0; j < MAX_N; j++)
        {
            if (i == j)
                dist[i][j] = 0;
            else
                dist[i][j] = INF;
        }

        
    }

    int v, e;
    cin >> v >> e;
    vector<pair<int, pair<int, int>>> adj;
    vector<int> potential_starting_nodes;
    vector<int> ad[v + 1];
    for (int i = 0; i < e; i++)
    {
        int x, y, w;
        cin >> x >> y >> w; // x---->y with weight w
        ad[x].push_back(y);
        adj.push_back({x, {y, w}});
        dist[x][y] = w;
    }

    for (int k = 1; k <= v; ++k)
    {
        for (int i = 1; i <= v; ++i)
        {
            for (int j = 1; j <= v; ++j)
            {
                if (dist[i][k] < INF && dist[k][j] < INF)
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
    for (int i = 1; i <= v; i++)
    {
        int check = 0;
        for (int j = 1; j <= v; j++)
        {
            visited[j] = -1;
        }
        dfs(ad, v, i);

        for (int j = 1; j <= v; j++)
        {
            if (visited[j] == 1)
            {
                check++;
            }
        }
        if (check == v)
        {
            potential_starting_nodes.push_back(i);
        }
    }
    if (potential_starting_nodes.size() == 0)
    {
        cout << "No Any Nodes Possible " << endl;
        return 0;
    }
    cout << "Potential Starting Node " << endl;
    for (int i = 0; i < potential_starting_nodes.size(); i++)
    {
        cout << potential_starting_nodes[i] << endl;
    }

    vector<pair<int, int>> optimal_starting;
    for (int i = 0; i <= potential_starting_nodes.size() - 1; i++)
    {
        int wt = INT_MIN;
        int p = potential_starting_nodes[i];
        for (int j = 1; j <= v; j++)
        {
            wt = max(wt, dist[p][j]);
        }
        optimal_starting.push_back({wt, p});
    }

    sort(optimal_starting.begin(), optimal_starting.end());
    cout << "Optimal Starting Node " << optimal_starting[0].second << endl;
    cout << "Optimal Time ";
    cout << optimal_starting[0].first << endl;

    return 0;
}