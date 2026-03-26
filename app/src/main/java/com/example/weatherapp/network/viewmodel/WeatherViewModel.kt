import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val API_KEY = "351960a7da4f43a3bf7acb6564dd37e5"

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // 🔍 Manual search
    fun getWeather(city: String) {

        if (city.isBlank()) {
            _error.value = "Enter city name"
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true

                val result = RetrofitInstance.api.getWeather(
                    city.trim(),
                    API_KEY
                )

                _weather.value = result

            } catch (e: Exception) {
                _error.value = "City not found"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 📍 Location weather
    fun getWeatherByLocation(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                val result = RetrofitInstance.api.getWeatherByLocation(
                    lat,
                    lon,
                    API_KEY
                )

                _weather.value = result

            } catch (e: Exception) {
                _error.value = "Location weather failed"
            } finally {
                _isLoading.value = false
            }
        }
    }
}