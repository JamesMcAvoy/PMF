#include "DHT.h"
#include <ArduinoJson.h>

#define DHTPIN 2  //PIN DHT
#define DHTTYPE DHT22
#define DIODEPIN A1 //PIN DIODE
#define MOSFETPIN 4 //PIN MOSFET Module Peltier

DHT dht(DHTPIN, DHTTYPE);

//biaiser la val de la diode
const int t0 = 20.3;
const float vf0 = 641.5;
//vars calcul diode
int i;
float dtemp, dtemp_avg, tDiode;

//température de consigne
float consigne = 20;

void setup() {
  Serial.begin(9600);
  pinMode(DIODEPIN, INPUT_PULLUP); 
  pinMode(MOSFETPIN, OUTPUT);
  dht.begin();
}

void loop() {  
  //JSON
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& serialWrite = jsonBuffer.createObject();

  //température diode
  dtemp_avg = 0;
  for (i = 0; i < 1024; i++) {
    float vf = analogRead(DIODEPIN) * (4976.30 / 1023.000);
    //Serial.println(vf);
    dtemp = (vf - vf0) * 0.4545454;
    dtemp_avg = dtemp_avg + dtemp;
  }
  tDiode = t0 - dtemp_avg / 1024;

  //Lecture humidité et température DHT22
  float hDHT = dht.readHumidity();
  float tDHT = dht.readTemperature();

  //Si erreur affiche JSON erreur
  if (isnan(hDHT) || isnan(tDHT) || tDiode<-50) {
    serialWrite["error"] = 1;
    serialWrite.printTo(Serial); Serial.println("");
    return;
  }

  //Affiche JSON
  serialWrite["error"] = 0;
  serialWrite["dht22Temp"] = tDHT;
  serialWrite["dht22Hygro"] = hDHT;
  serialWrite["diodeTemp"] = tDiode;
  serialWrite.printTo(Serial); Serial.println("");

  //Température de consigne
  if(tDHT >= consigne) digitalWrite(MOSFETPIN, HIGH);
  else                 digitalWrite(MOSFETPIN, LOW);

  delay(1000);
}
