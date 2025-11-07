#include <iostream>
#include <vector>
#include <limits>
using namespace std;

// Матрица расстояний между городами
const int INF = numeric_limits<int>::max();
int graph[][4] = {
    {0, 10, 15, 20},
    {10, 0, 35, 25},
    {15, 35, 0, 30},
    {20, 25, 30, 0}
};

void nearestNeighbor(int startCity, vector<bool>& visited, vector<int>& path, int& totalDistance) {
    int n = sizeof(graph)/sizeof(graph[0]);
    visited[startCity] = true;
    path.push_back(startCity);
    
    for (int i = 0; i < n - 1; ++i) {
        int currentCity = path.back(); // Текущий город
        int nextCity = -1;
        int minDist = INF;
        
        // Найти ближайший непросмотренный город
        for (int j = 0; j < n; ++j) {
            if (!visited[j] && graph[currentCity][j] != 0 && graph[currentCity][j] < minDist) {
                nextCity = j;
                minDist = graph[currentCity][j];
            }
        }
        
        if (nextCity != -1) {
            visited[nextCity] = true;
            path.push_back(nextCity); // Добавляем следующий город в путь
            totalDistance += minDist; // Обновляем общую длину пути
        }
    }
    
    // Возвращаемся обратно в стартовый город
    totalDistance += graph[path.back()][startCity]; // Добавляем расстояние последнего города до старта
    path.push_back(startCity);
}

int main() {
    const int numCities = sizeof(graph)/sizeof(graph[0]); // Количество городов
    vector<bool> visited(numCities, false); // Массив пометок посещённых городов
    vector<int> path; // Маршрут движения
    int totalDistance = 0; // Общая длина маршрута
    
    cout << "Количество городов: " << numCities << endl;
    
    // Запускаем алгоритм с первого города
    nearestNeighbor(0, visited, path, totalDistance);
    
    cout << "Маршрут посещения городов: ";
    for (auto city : path) {
        cout << city << " ";
    }
    cout << "\nОбщая длина маршрута: " << totalDistance << endl;
    
    return 0;
}
