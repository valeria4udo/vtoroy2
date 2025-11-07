@@ -0,0 +1,59 @@
import java.util.*;

public class TSPNearestNeighbor {
    static final int INF = Integer.MAX_VALUE;
    static int[][] graph = new int[][]{
        {0, 10, 15, 20}, 
        {10, 0, 35, 25}, 
        {15, 35, 0, 30}, 
        {20, 25, 30, 0}};

    public static void nearestNeighbor(int startCity, boolean[] visited, List<Integer> path, int[] totalDistance) {
        int n = graph.length;
        visited[startCity] = true;
        path.add(startCity);
        
        for (int i = 0; i < n - 1; ++i) {
            int currentCity = path.get(path.size()-1); // Текущий город
            int nextCity = -1;
            int minDist = INF;
            
            // Поиск ближайшего непросмотренного города
            for (int j = 0; j < n; ++j) {
                if (!visited[j] && graph[currentCity][j] > 0 && graph[currentCity][j] < minDist) {
                    nextCity = j;
                    minDist = graph[currentCity][j];
                }
            }
            
            if (nextCity != -1) {
                visited[nextCity] = true;
                path.add(nextCity); // Добавляем следующий город в путь
                totalDistance[0] += minDist; // Обновляем общую длину пути
            }
        }
        
        // Возвращение обратно в стартовый город
        totalDistance[0] += graph[path.get(path.size()-1)][startCity]; // Добавляем расстояние последнего города до старта
        path.add(startCity);
    }

    public static void main(String[] args) {
        int numCities = graph.length; // Количество городов
        boolean[] visited = new boolean[numCities]; // Массив пометок посещённых городов
        Arrays.fill(visited, false);
        List<Integer> path = new ArrayList<>(); // Список городов в порядке посещения
        int[] totalDistance = new int[]{0}; // Общая длина маршрута
        
        System.out.println("Количество городов: " + numCities);
        
        // Запускаем алгоритм с первого города
        nearestNeighbor(0, visited, path, totalDistance);
        
        System.out.print("Маршрут посещения городов: ");
        for (Integer city : path) {
            System.out.print(city + " ");
        }
        System.out.println("\nОбщая длина маршрута: " + totalDistance[0]);
    }
}
