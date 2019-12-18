// When we get a command to report the joystick position (a 'j' character on the serial line),
// send a package telling the X and Y values for the joystick, in approximately the range 0 to 1023.
void setup() {
  Serial.begin(9600);
  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  pinMode(10, INPUT);
}


void loop() {
  if (Serial.available() > 0 && Serial.read() == 'j') {
    long x = analogRead(A0);
    long y = analogRead(A1);
    // $123,-56\n
    Serial.print("$");
    Serial.print(x);
    Serial.print(",");
    Serial.print(y);
    Serial.print("\n");
  }
}
