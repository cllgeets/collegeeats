#include <bits/stdc++.h>

using namespace std;

int partition(int arr[], int low, int high)
{
    // pivot
    int pivot = arr[high];

    // Index of smaller element
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++)
    {
        // If current element is smaller
        // than or equal to pivot
        if (arr[j] <= pivot)
        {

            // increment index of
            // smaller element
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return (i + 1);
}

// Generates Random Pivot, swaps pivot with
// end element and calls the partition function
int partition_r(int arr[], int low, int high)
{
    // Generate a random number in between
    // low .. high
    srand(time(NULL));
    int random = low + rand() % (high - low);

    // Swap A[random] with A[high]
    swap(arr[random], arr[high]);

    return partition(arr, low, high);
}

/* The main function that implements
QuickSort
arr[] --> Array to be sorted,
low --> Starting index,
high --> Ending index */
void quickSort(int arr[], int low, int high)
{
    if (low < high)
    {

        /* pi is partitioning index,
        arr[p] is now
        at right place */
        int pi = partition_r(arr, low, high);

        // Separately sort elements before
        // partition and after partition
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

/* Function to print an array */
void printArray(int arr[], int size)
{
    int i;
    for (i = 0; i < size; i++)
        cout << arr[i] << " ";
}

void maindiff(int arr[], int n)
{
    int mindiff = INT_MAX;
    for (int i = 0; i < n - 1; i++)
        mindiff = min(mindiff, abs(arr[i] - arr[i + 1]));

    for (int i = 0; i < n - 1; i++)
    {
        if (abs(arr[i] - arr[i + 1]) == mindiff)
        {
            cout << "(";
            cout << arr[i] << " " << arr[i + 1];
            cout << ") ";
        }
    }
}

bool checksame(int arr[], int n)
{
    for (int i = 0; i < n; i++)
    {

        for (int j = i + 1; j < n; j++)
        {
            if (arr[i] == arr[j] || arr[j] > 1000)
            {
                return 0;
            }
        }
    }
    return 1;
}

int main()
{
    int n;
    cout << "n = ";
    cin >> n;
    int arr[n];
    cout << "InArr = ";
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    quickSort(arr, 0, n - 1);

    if (checksame(arr, n))
    {
        cout << "OutArr = ";
        maindiff(arr, n);
    }
    else
    {
        cout << "All elements in InArr are not unique. No element in InArr can be more than 100";
    }

    return 0;
}
