Feature: Weather API Testing
  # v.1
  @weather_test_positive
  Scenario: Get weather information
    When send request to get weather information
      | city   |
      | London |
      | Moscow |
      | Rio    |
      | Berlin |
    Then response should includes the following expected data
      | location name  | location country | current temp_c | condition text |
      | London         | United Kingdom   | 11.0           | Partly cloudy  |
      | Moscow         | Russia           | 16.0           | Sunny          |
      | Rio De Janeiro | Brazil           | 22.0           | Clear          |
      | Berlin         | Germany          | 18.0           | Sunny          |

  # v.2
  @weather_test_positive
  Scenario: Get weather information 1
    When send request to get weather information
      | city   |
      | London |
      | Moscow |
      | Rio    |
      | Berlin |
    Then response should includes the following expected json data
      | expected json data |
      | weatherLondon.json |
      | weatherMoscow.json |
      | weatherRio.json |
      | weatherBerlin.json |

  @weather_test_negative
  Scenario: Get weather information incorrect
    When send bad request to get weather information
      | url            | city | key                            |
      | /current.json  | QQQ  | 397d69371dd74bbebe363432242305 |
      | /current1.json | Rome | 397d69371dd74bbebe363432242305 |
      | /current.json  | Rome | 250                            |
      | /current.json  | Rome |                                |
    Then response has following error code and message
      | code | message                             |
      | 1006 | No matching location found.         |
      | 1005 | API URL is invalid.                 |
      | 2008 | API key has been disabled.          |
      | 1002 | API key is invalid or not provided. |