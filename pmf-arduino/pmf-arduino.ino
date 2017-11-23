 /**
 * Bibliothèques
 */
#include "DHT.h"
#include <ArduinoJson.h>
#include <Wire.h>
#include "Adafruit_LiquidCrystal.h"

/**
 * DIODEPIN = capteur température module Peltier
 * DHT = DHT intérieur température + hulidité
 * MOSFET = module Peltier
 * VENTIPIN = ventilateur interne high/low
 * DIODEEXT = diode température extérieure
 */

#define DHTPIN 2  //PIN DHT
#define DHTTYPE DHT22
#define DIODEPIN A1 //PIN DIODE
#define MOSFETPIN 4 //PIN MOSFET Module Peltier
#define VENTIPIN 5
#define DIODEEXT A3

//DHT setup
DHT dht(DHTPIN, DHTTYPE);

//étalonnage diode
const int t0 = 20.3;
const float vf0 = 641.5;
//vars calcul diode
int i;
float dtemp, dtemp_avg, tDiode, tDiodeExt;

//valeurs de consigne par défaut
float consigne = 20;
int ventilateur = 1;

//LCD
Adafruit_LiquidCrystal lcd(13, 12, 11, 10, 9, 8);

/**
 * Setup
 */
void setup() {
  Serial.begin(9600);
  pinMode(DIODEPIN, INPUT_PULLUP); 
  pinMode(DIODEEXT, INPUT_PULLUP); 
  pinMode(MOSFETPIN, OUTPUT);
  pinMode(VENTIPIN, OUTPUT);
  dht.begin();

  //lcd start
  lcd.begin(16, 2);
  lcd.setCursor(0, 0);
  for(int i=0; i<10; i++) {
    lcd.clear();
    delay(250);
    lcd.print("^_^ \xC2\xB3\xC1\xBE\xC8\xBF\xAC\xC7\xD8");  //KAWAIIIII
    delay(250);
  }
}

/**
 * Loop
 */
void loop() {  
  //JSON
  String readJSON;
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& serialWrite = jsonBuffer.createObject();
  //Lecture JSON
  if(Serial.available() > 0) {
    readJSON = Serial.readStringUntil('\n');
    JsonObject& serialRead = jsonBuffer.parseObject(readJSON);
    consigne = serialRead["tempConsigne"];
    ventilateur = serialRead["ventiloConsigne"];
  }

  //Température diodes intérieur (module) //// extérieur (sur le circuit) ; steinhart-hart
  dtemp_avg = 0;
  for (i = 0; i < 1024; i++) {
    float vf = analogRead(DIODEPIN) * (4976.30 / 1023.000);
    dtemp = (vf - vf0) * 0.4545454;
    dtemp_avg = dtemp_avg + dtemp;
  }
  tDiode = t0 - dtemp_avg / 1024;

  dtemp_avg = 0;
  for (i = 0; i < 1024; i++) {
    float vf = analogRead(DIODEEXT) * (4976.30 / 1023.000);
    dtemp = (vf - vf0) * 0.4545454;
    dtemp_avg = dtemp_avg + dtemp;
  }
  tDiodeExt = t0 - dtemp_avg / 1024;

  //Lecture humidité et température DHT22
  float hDHT = dht.readHumidity();
  float tDHT = dht.readTemperature();

  //Si erreur affiche JSON erreur et return
  if (isnan(hDHT) || isnan(tDHT) || tDiode<-50 || tDiodeExt<-50) {
    serialWrite["error"] = 1;
    serialWrite.printTo(Serial); Serial.println("");
    return;
  }

  //Affiche JSON Serial
  serialWrite["error"] = 0;
  serialWrite["dht22Temp"] = tDHT;
  serialWrite["dht22Hygro"] = hDHT;
  serialWrite["diodeTemp"] = tDiode;
  serialWrite["diodeExt"] = tDiodeExt;
  serialWrite.printTo(Serial); Serial.println("");

  //Température de consigne
  if(tDHT >= consigne) digitalWrite(MOSFETPIN, HIGH);
  else                 digitalWrite(MOSFETPIN, LOW);

  //Ventilateur (consigne)
  if(ventilateur) digitalWrite(VENTIPIN, HIGH);
  else            digitalWrite(VENTIPIN, LOW);

  //Affiche température diode intérieure sur LCD
  lcd.setCursor(4, 0);
  lcd.print("Temp surface");
  lcd.setCursor(3, 1);
  lcd.print(tDiode);
  lcd.setCursor(9, 1);
  lcd.print("deg C");

  //sleep
  delay(1000);
}
