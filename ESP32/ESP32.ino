#include <HTTPClient.h>
#include <WiFi.h>
#include <FastLED.h>

#define LED_PIN 13
#define NUM_LEDS 8

CRGB leds[NUM_LEDS];
CRGBPalette16 maPal;

// WiFi
const char *ssid = "";
const char *password = "";

unsigned int Actual_Millis, Previous_Millis;
int refresh_time = 200;

String response_body;
int response_code;

bool power;
int r, g, b, brightness, effect;
byte hue;

void setup()
{
  delay(10);
  pinMode(2, OUTPUT);

  FastLED.addLeds<WS2812B, LED_PIN, GRB>(leds, NUM_LEDS);

  WiFi.begin(ssid, password);
  int i = 0;
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    i++;
    if (i > 10)
    {
      ESP.restart();
    }
  }

  Actual_Millis = millis();
  Previous_Millis = Actual_Millis;
}

void loop()
{
  String ary[6];
  Actual_Millis = millis();
  if (Actual_Millis - Previous_Millis > refresh_time)
  {
    Previous_Millis = Actual_Millis;
    if (WiFi.status() == WL_CONNECTED)
    {
      digitalWrite(2, HIGH);
      HTTPClient http;

      http.begin("https://karlito.website/esp32_update.php");

      http.addHeader("Content-Type", "application/x-www-form-urlencoded");

      response_code = http.POST("check_LED_status");

      if (response_code > 0)
      {

        if (response_code == 200)
        {
          response_body = http.getString();

          int start, endIndex;

          ary[0] = response_body[0]; // status on/off, always 1 bit

          start = endIndex = 2;
          while (response_body[endIndex] != ' ')
          {
            endIndex++;
          }
          ary[1] = response_body.substring(start, endIndex);

          endIndex = start = endIndex + 1;
          while (response_body[endIndex] != ' ')
          {
            endIndex++;
          }
          ary[2] = response_body.substring(start, endIndex);

          endIndex = start = endIndex + 1;
          while (response_body[endIndex] != ' ')
          {
            endIndex++;
          }
          ary[3] = response_body.substring(start, endIndex);

          endIndex = start = endIndex + 1;
          while (response_body[endIndex] != ' ')
          {
            endIndex++;
          }
          ary[4] = response_body.substring(start, endIndex);

          endIndex = start = endIndex + 1;
          while (response_body[endIndex] != '\0')
          {
            endIndex++;
          }
          ary[5] = response_body.substring(start, endIndex);

          // convert from string to numbers
          if (ary[0] == "1")
          {
            power = true;
          }
          else
          {
            power = false;
          }

          r = ary[1].toInt();
          g = ary[2].toInt();
          b = ary[3].toInt();
          brightness = ary[4].toInt();
          // effect = ary[5];
        }
      }

      if (power)
      {
        if (ary[5] == "0")
        {
          fill_solid(leds, NUM_LEDS, CRGB(r, g, b));
          FastLED.setBrightness(brightness);
          FastLED.show();
        }
        else
        {
          // efekti
          switch (ary[5].toInt())
          {
          case 7:
            fill_rainbow(leds, NUM_LEDS, 0, 20);
            FastLED.setBrightness(brightness);
            FastLED.show();
            break;
          case 5:
            FillLEDsFromPaletteColors(PartyColors_p);
            // snake = 1;
            break;
          }
        }
      }
      else
      {
        FastLED.clear();
        FastLED.show();
      }

      http.end();
    } // END of WIFI connected
    else
    {
      digitalWrite(2, LOW);
      ESP.restart();
    }
  }
  long prev, cur = millis();
}

void FillLEDsFromPaletteColors(CRGBPalette16 Palette)
{
  int colorIndex = 1;
  for (int i = 0; i < NUM_LEDS; i++)
  {
    leds[i] = ColorFromPalette(Palette, colorIndex, brightness, NOBLEND);
    colorIndex += 30;
  }
  FastLED.show();
}